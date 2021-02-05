package com.example.letscube.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.letscube.R
import com.example.letscube.model.AccessToken
import com.example.letscube.model.CurrentUser
import com.example.letscube.retrofit.APIClass
import com.example.letscube.retrofit.NetworkLayer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity()
{
    lateinit var toolbar: Toolbar
    lateinit var container: FrameLayout
    lateinit var networkLayer: NetworkLayer
    lateinit var user: CurrentUser
    lateinit var sharedPref: SharedPreferences
    var loggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.fragment_container)
        toolbar = findViewById(R.id.toolbar)
        networkLayer = NetworkLayer()
        sharedPref = getPreferences(Context.MODE_PRIVATE)

        setSupportActionBar(toolbar)

        inflateFragment()
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data

        if (uri != null && !loggedIn)
        {
            if (uri.toString().startsWith(NetworkLayer.callbackUri))
            {
                val code = uri.getQueryParameter("code")
                if (code != null) {
                    doLogin(code)
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.room_list_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean
    {
        super.onPrepareOptionsMenu(menu)
        menu?.let {
            it.clear()
            if (sharedPref.contains(getString(R.string.access_token)))
            {
                menu.add(0, R.id.profile, Menu.NONE, getString(R.string.profile))
                menu.add(0, R.id.logout, Menu.NONE, getString(R.string.sign_out))
                getStoredUser()
            }
            else
            {
                menu.add(0, R.id.login, Menu.NONE, getString(R.string.login))
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when(item.itemId) {
            R.id.login -> {
                login()
                true
            }

            R.id.logout -> {
                logOut()
                true
            }

            R.id.profile -> {
                viewProfile()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun doLogin(code: String)
    {
        networkLayer.getRetrofitBuilder().create(APIClass::class.java).getAccessToken(
            "authorization_code",
            NetworkLayer.applicationId,
            NetworkLayer.clientSecret,
            code,
            NetworkLayer.callbackUri).enqueue(object : Callback<AccessToken>
            {
                override fun onFailure(call: Call<AccessToken>, t: Throwable)
                {
                    Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>)
                {
                    response.body()?.let {
                        loggedIn = true
                        saveAccessToken(it)
                        getUser(it.token)
                    } ?: run {
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    fun getUser(token: String)
    {
        networkLayer.getRetrofitBuilder().create(APIClass::class.java).getUser("Bearer $token").enqueue(
            object : Callback<CurrentUser> {
                override fun onFailure(call: Call<CurrentUser>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed to get user", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<CurrentUser>, response: Response<CurrentUser>) {
                    response.body()?.let {
                        saveUser(it)
                        Toast.makeText(applicationContext, "Signed in successfully", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        )
    }

    private fun saveAccessToken(token: AccessToken)
    {
        with(sharedPref.edit())
        {
            putString(getString(R.string.access_token), token.token)
            apply()
        }
    }

    private fun saveUser(user: CurrentUser)
    {
        this.user = user
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(PREFS_STORE_USER, json)
        editor.apply()
    }

    private fun getStoredUser()
    {
        val gson = Gson()
        val json = sharedPref.getString(PREFS_STORE_USER, "")

        if(!json.isNullOrEmpty())
        {
            user = gson.fromJson(json, CurrentUser::class.java)
            loggedIn = true
        }
    }

    private fun logOut()
    {
        with(sharedPref.edit())
        {
            remove(getString(R.string.access_token))
            remove(PREFS_STORE_USER)
            apply()
            Toast.makeText(applicationContext, "Logged out successfully", Toast.LENGTH_SHORT).show()
            loggedIn = false
        }

    }

    private fun viewProfile()
    {
        val intent = Intent(this, UserProfileActivity::class.java)
        intent.putExtra(INTENT_PROFILE_KEY, user)
        startActivity(intent)
    }

    private fun login()
    {
        networkLayer.launchOauth(this)
    }

    private fun inflateFragment()
    {
        supportFragmentManager.beginTransaction().replace(container.id, RoomListFragment()).commit()
    }

    companion object
    {
        const val INTENT_PROFILE_KEY = "current_user"
        const val PREFS_STORE_USER = "stored_user"
    }
}