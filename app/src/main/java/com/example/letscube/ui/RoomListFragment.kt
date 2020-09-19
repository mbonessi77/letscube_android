package com.example.letscube.ui

import RecyclerViewInfo.RoomAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R
import model.RoomInfo

class RoomListFragment : Fragment() {
    private val list = ArrayList<RoomInfo>()
    lateinit var rv: RecyclerView
    lateinit var adapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            it.title = "Let's Cube!"
        }

        list.add(RoomInfo(true, "My test room", 1))
        list.add(RoomInfo(false, "My public room", 100))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_room_list, container, false)

        rv = view.findViewById(R.id.rv_room_list)
        adapter = RoomAdapter()
        adapter.setDataSet(list)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = RoomListFragment()
    }
}