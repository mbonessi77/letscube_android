package com.example.letscube.ui.chat_recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val messageText: TextView = itemView.findViewById(R.id.chat_message)
    val displayName: TextView = itemView.findViewById(R.id.chat_user_name)
    val profilePicture: ImageView = itemView.findViewById(R.id.chat_profile_picture)
}