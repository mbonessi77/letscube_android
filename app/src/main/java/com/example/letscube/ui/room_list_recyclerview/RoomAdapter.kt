package com.example.letscube.ui.room_list_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R
import com.example.letscube.model.RoomInfo

class RoomAdapter(val listener: ItemClickListener) : RecyclerView.Adapter<RoomViewHolder>() {
    var roomList = ArrayList<RoomInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.room_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        when(roomList[position].isPrivate) {
            true -> holder.privateImage.setBackgroundResource(R.drawable.ic_private_room)
            else -> holder.privateImage.setBackgroundResource(R.drawable.ic_public)
        }
        holder.roomName.text = roomList[position].roomName
        holder.userCount.text = "${roomList[position].userCount} users"
        holder.itemView.setOnClickListener {
            listener.enterRoom(roomList[position].roomId)
        }
    }

    fun setDataSet(list: ArrayList<RoomInfo>) {
        this.roomList = list
    }
}