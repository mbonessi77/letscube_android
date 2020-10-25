package com.example.letscube.ui.room_list_recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R

class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val privateImage: ImageView = itemView.findViewById(R.id.iv_private_room)
    val roomName: TextView = itemView.findViewById(R.id.tv_room_name)
    val userCount: TextView = itemView.findViewById(R.id.tv_user_count)
}