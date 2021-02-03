package com.example.letscube.ui.room_info

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.ViewPager
import com.example.letscube.R
import com.example.letscube.ui.TimerOptionsDialog
import com.google.android.material.tabs.TabLayout

class RoomActivity : AppCompatActivity(), TimerOptionsDialog.SaveTimerOptionsListener
{
    lateinit var tabLayout: TabLayout
    lateinit var pager: ViewPager
    lateinit var toolbar: Toolbar
    lateinit var sharedPrefs: SharedPreferences
    lateinit var inspectionLiveData: MutableLiveData<Boolean>
    lateinit var timerLiveData: MutableLiveData<String>
    lateinit var listener: UpdateSolveFragment

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        sharedPrefs = getPreferences(Context.MODE_PRIVATE)

        setToolbar()
        setTabs()
        setPager()
        initLiveData()
    }

    private fun initLiveData()
    {
        inspectionLiveData = MutableLiveData()
        timerLiveData = MutableLiveData()

        inspectionLiveData.observeForever {
            listener.updateInspection(it)
        }

        timerLiveData.observeForever {
            listener.updateUi(it)
        }
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

    fun setCurrentListener(listener: UpdateSolveFragment)
    {
        this.listener = listener
    }

    override fun saveTimerOptions(isUsingInspection: Boolean, inputMethod: String)
    {
        if (inspectionLiveData.value != isUsingInspection)
        {
            inspectionLiveData.postValue(isUsingInspection)
        }

        if (timerLiveData.value.toString() != inputMethod)
        {
            timerLiveData.postValue(inputMethod)
        }

        with(sharedPrefs.edit())
        {
            putBoolean("usingInspection", isUsingInspection)
            putString("inputMethod", inputMethod)
            apply()
        }
    }
}