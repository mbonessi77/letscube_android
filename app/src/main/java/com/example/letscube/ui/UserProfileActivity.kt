package com.example.letscube.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.letscube.R
import com.example.letscube.model.CurrentUser

class UserProfileActivity : AppCompatActivity() {

    lateinit var newIntent: Intent
    lateinit var wcaIdText: TextView
    lateinit var realNameText: TextView
    lateinit var profileImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setSupportActionBar(findViewById(R.id.toolbar))

        wcaIdText = findViewById(R.id.tv_wca_id)
        realNameText = findViewById(R.id.tv_real_name)
        profileImage = findViewById(R.id.iv_profile)

        newIntent = intent

        val user = newIntent.getParcelableExtra<CurrentUser>(MainActivity.INTENT_PROFILE_KEY)

        wcaIdText.text = "WCA ID: ${user?.user?.wcaId}"
        realNameText.text = "Real Name: ${user?.user?.name}"

        Glide.with(applicationContext).load(user?.user?.avatar?.url).into(profileImage)
    }
}