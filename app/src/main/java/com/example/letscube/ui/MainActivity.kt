package com.example.letscube.ui

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
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.fragment_container)
        toolbar = findViewById(R.id.toolbar)
        networkLayer = RetrofitBuilder()

        setSupportActionBar(toolbar)

        inflateFragment()
    }


    private fun doLogin()
    {
        networkLayer.getRetrofitBuilder().create(APIClass::class.java).getAccessToken(username, password, "password")
            .enqueue(object : Callback<AccessToken>
            {
                override fun onFailure(call: Call<AccessToken>, t: Throwable)
                {
                }

                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>)
                {
                    response.body()?.let {
                        getUser(it.token)
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
                    Toast.makeText(applicationContext, response.body()!!.user.wcaId, Toast.LENGTH_SHORT).show()
                }

            }
        )
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
                Toast.makeText(applicationContext, "Will Logout", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.profile -> {
                Toast.makeText(applicationContext, "View Profile", Toast.LENGTH_SHORT).show()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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

        doLogin()
    }
}