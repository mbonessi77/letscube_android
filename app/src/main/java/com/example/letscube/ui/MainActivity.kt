package com.example.letscube.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.letscube.R

class MainActivity : AppCompatActivity() {
    lateinit var container: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.fragment_container)

        inflateFragment()
    }

    private fun inflateFragment() {
        supportFragmentManager.beginTransaction().replace(container.id, RoomListFragment()).commit()
    }
}