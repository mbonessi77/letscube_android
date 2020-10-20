package RecyclerViewInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letscube.R
import com.example.letscube.model.RoomInfo

class RoomAdapter : RecyclerView.Adapter<RoomViewHolder>() {
    var roomList = ArrayList<RoomInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.room_item_view, parent, false)
        return RoomViewHolder(view.findViewById(R.id.iv_private_room), view.findViewById(R.id.tv_room_name), view.findViewById(R.id.tv_user_count), view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        when(roomList[position].isPrivate) {
            true -> holder.privateImage.visibility = View.VISIBLE
            else -> holder.privateImage.visibility = View.GONE
        }
        holder.roomName.text = roomList[position].roomName
        holder.userCount.text = "${roomList[position].userCount} users"
    }

    fun setDataSet(list: ArrayList<RoomInfo>) {
        this.roomList = list
    }
}