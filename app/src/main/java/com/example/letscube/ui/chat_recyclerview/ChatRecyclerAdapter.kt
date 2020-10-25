package com.example.letscube.ui.chat_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R
import com.example.letscube.model.ChatMessageItem

class ChatRecyclerAdapter : RecyclerView.Adapter<ChatViewHolder>()
{
    lateinit var messageList: List<ChatMessageItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder
    {
        return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_item_view, parent, false))
    }

    override fun getItemCount(): Int
    {
        return if (this::messageList.isInitialized) {
            messageList.count()
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int)
    {
        holder.displayName.text = messageList[position].user.name
        holder.messageText.text = messageList[position].message
    }

    fun setMessageList(messageList: ArrayList<ChatMessageItem>)
    {
        this.messageList = messageList
        notifyDataSetChanged()
    }
}