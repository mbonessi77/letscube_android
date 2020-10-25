package com.example.letscube.ui.room_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.letscube.R
import com.example.letscube.ui.TimerOptionsDialog
import com.google.android.material.tabs.TabLayout

class RoomActivity : AppCompatActivity(), TimerOptionsDialog.SaveTimerOptionsListener
{
    lateinit var tabLayout: TabLayout
    lateinit var pager: ViewPager
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        setToolbar()
        setTabs()
        setPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.solve_room_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when(item.itemId)
        {
            R.id.timer_options  ->
            {
                openTimerSettings()
                true
            }

            R.id.edit_room ->
            {
                editRoom()
                true
            }

            R.id.delete_room ->
            {
                deleteRoom()
                true
            }

            R.id.manage_users ->
            {
                manageUsers()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openTimerSettings()
    {
        val dialog = TimerOptionsDialog(this)
        dialog.show(supportFragmentManager, "Timer options")
    }

    private fun editRoom()
    {
        Toast.makeText(applicationContext, "Edit Room", Toast.LENGTH_SHORT).show()
    }

    private fun deleteRoom()
    {
        Toast.makeText(this, "Delete Room", Toast.LENGTH_SHORT).show()
    }

    private fun manageUsers()
    {
        Toast.makeText(this, "Manage Users", Toast.LENGTH_SHORT).show()
    }

    private fun setToolbar()
    {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
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

    override fun saveTimerOptions(isUsingInspection: Boolean, inputMethod: String)
    {
        Toast.makeText(this, "TODO: Save timer options", Toast.LENGTH_SHORT).show()
    }
}