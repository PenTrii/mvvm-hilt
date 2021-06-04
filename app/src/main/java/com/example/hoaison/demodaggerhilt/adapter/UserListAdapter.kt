package com.example.hoaison.demodaggerhilt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hoaison.demodaggerhilt.R
import com.example.hoaison.demodaggerhilt.model.User
import com.example.hoaison.demodaggerhilt.utils.ImageNetwork

class UserListAdapter(
    private val context: Context?,
    private val list: List<User>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolderGird(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (itemCount <= 0 && position >= itemCount) return
            val user = list[holder.adapterPosition]
            when (holder) {
                is ViewHolderGird -> holder.onBind(user)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private inner class ViewHolderGird(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val areaAndAge: TextView = itemView.findViewById(R.id.areaAndAge)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)

        @SuppressLint("SetTextI18n")
        fun onBind(item: User) {
            areaAndAge.text = item.displayName
            context?.let {
                ImageNetwork.with(it)
                    .url(item.avatarURL!!)
                    .into(avatar)
            }
        }
    }
}