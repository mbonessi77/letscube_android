package com.example.letscube.ui.recyclerViewInfo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class RoomViewHolder(val privateImage: ImageView, val roomName: TextView, val userCount: TextView, val itemView: View) : RecyclerView.ViewHolder(itemView)