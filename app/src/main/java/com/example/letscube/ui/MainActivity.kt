package com.example.letscube.ui

import android.app.ProgressDialog
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
import com.example.letscube.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), LoginDialog.LoginListener
{
    lateinit var toolbar: Toolbar
    lateinit var container: FrameLayout
    lateinit var username: String
    lateinit var password: String
    lateinit var networkLayer: RetrofitBuilder
    lateinit var progressDialog: ProgressDialog
    lateinit var user: CurrentUser
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.fragment_container)
        toolbar = findViewById(R.id.toolbar)
        networkLayer = RetrofitBuilder()
        sharedPref = getPreferences(Context.MODE_PRIVATE)

        setSupportActionBar(toolbar)

        inflateFragment()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
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

    private fun doLogin()
    {
        networkLayer.getRetrofitBuilder().create(APIClass::class.java).getAccessToken(username, password, "password")
            .enqueue(object : Callback<AccessToken>
            {
                override fun onFailure(call: Call<AccessToken>, t: Throwable)
                {
                    clearEmailAndPassword()
                    progressDialog.cancel()
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>)
                {
                    clearEmailAndPassword()
                    progressDialog.cancel()
                    response.body()?.let {
                        saveAccessToken(it)
                        getUser(it.token)
                    } ?: run {
                        Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
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
                        user = it
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

    private fun logOut()
    {
        with(sharedPref.edit())
        {
            remove(getString(R.string.access_token))
            apply()
            Toast.makeText(applicationContext, "Logged out successfully", Toast.LENGTH_SHORT).show()
        }

    }

    private fun viewProfile()
    {
        val intent = Intent(this, UserProfileActivity::class.java)
        intent.putExtra(INTENT_PROFILE_KEY, user)
        startActivity(intent)
    }

    private fun clearEmailAndPassword()
    {
        username = ""
        password = ""
    }

    private fun login()
    {
        val dialog = LoginDialog(this)
        dialog.show(supportFragmentManager, "Login Dialog")
    }

    private fun inflateFragment()
    {
        supportFragmentManager.beginTransaction().replace(container.id, RoomListFragment()).commit()
    }

    override fun getEmailAndPassword(email: String, password: String)
    {
        username = email
        this.password = password

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Logging in...")
        progressDialog.show()

        doLogin()
    }

    companion object
    {
        val INTENT_PROFILE_KEY = "current_user"
    }
}