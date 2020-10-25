package com.example.letscube.ui.room_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.letscube.R
import com.google.android.material.tabs.TabLayout

class RoomActivity : AppCompatActivity()
{
    lateinit var tabLayout: TabLayout
    lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        setTabs()
        setPager()
    }


    private fun setTabs()
    {
        tabLayout = findViewById(R.id.room_headers)
        tabLayout.addTab(tabLayout.newTab().setText("Solve"))
        tabLayout.addTab(tabLayout.newTab().setText("Chat"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?)
            {
                tab?.let {
                    pager.currentItem = it.position
                }
            }

        })
    }

    private fun setPager()
    {
        pager = findViewById(R.id.room_pager)
        val pagerAdapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        pager.adapter = pagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }
}