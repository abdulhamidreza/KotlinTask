package com.example.userforam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: ArrayList<UserDetails>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: UserDetails) {
            val textViewName = itemView.findViewById(R.id.name_lst) as TextView
            val textViewNumber = itemView.findViewById(R.id.number_lst) as TextView
            val textViewEmail = itemView.findViewById(R.id.email_lst) as TextView
            val textViewAge = itemView.findViewById(R.id.age_lst) as TextView
            val textViewCity = itemView.findViewById(R.id.city_lst) as TextView
            textViewName.text = user.name
            textViewNumber.text = user.number
            textViewEmail.text = user.email
            textViewAge.text = user.age
            textViewCity.text = user.city

        }
    }
}