package com.example.letscube.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.letscube.R


class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var container: FrameLayout
    lateinit var clientId: String
    lateinit var clientSecret: String
    lateinit var redirectUri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clientId = "bLytofP7iwfHysGmAFfQ1twAvtrNnN9rX9LwqFWffwM"
        clientSecret = "siIv2MkZwfoyrpwGLNVg8j5m7eH3oZMEQUbnIUYUsYw"
        redirectUri = "letscube.android://callback"

        container = findViewById(R.id.fragment_container)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        inflateFragment()
    }

    override fun onResume() {
        super.onResume()

        val uri: Uri? = intent.data

        uri?.let {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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

    private fun login() {
        val loginIntent = Intent(Intent.ACTION_VIEW, Uri.parse(
            "https://www.worldcubeassociation.org/oauth/authorize?client_id=$clientId&scope=:public+:email+:dob&redirect_uri=$redirectUri&response_type=token"
        ))

        startActivity(loginIntent)
    }

    private fun inflateFragment() {
        supportFragmentManager.beginTransaction().replace(container.id, RoomListFragment()).commit()
    }
}