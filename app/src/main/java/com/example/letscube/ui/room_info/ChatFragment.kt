package com.example.letscube.ui.room_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R
import com.example.letscube.model.ChatMessageItem
import com.example.letscube.model.User
import com.example.letscube.ui.chat_recyclerview.ChatRecyclerAdapter

class ChatFragment : Fragment()
{
    private val chatList = ArrayList<ChatMessageItem>()
    private lateinit var adapter: ChatRecyclerAdapter
    private lateinit var chatRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chatList.add(ChatMessageItem(User("Feliks Zemdegs"), "Nice solve Leo"))
        chatList.add(ChatMessageItem(User("Tymon Kolasiński"), "Yeah dude what was that last layer?"))
        chatList.add(ChatMessageItem(User("Leo Borromeo"), "Feesh to PLL skip"))
        chatList.add(ChatMessageItem(User("Feliks Zemdegs"), "pog"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        chatRecyclerView = view.findViewById(R.id.rv_chat_list)
        adapter = ChatRecyclerAdapter()
        adapter.setMessageList(chatList)
        chatRecyclerView.adapter = adapter
        chatRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }
}