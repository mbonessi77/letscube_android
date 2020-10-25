package com.example.letscube.ui.room_info

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(manager: FragmentManager, private val  totalTabs: Int) : FragmentPagerAdapter(manager, totalTabs)
{
    override fun getItem(position: Int): Fragment
    {
        return when(position) {
            0 -> SolveFragment()
            else -> ChatFragment()
        }
    }

    override fun getCount(): Int
    {
        return totalTabs
    }
}