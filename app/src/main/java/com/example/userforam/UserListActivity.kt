package com.example.userforam

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_list.*


class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        floatingActionButton.setOnClickListener {
            onAddButton()
        }

    }

    override fun onResume() {
        super.onResume()
        showList()
    }

    private fun onAddButton() {
        val intent = Intent(this, FormActivity::class.java)
        startActivity(intent)
    }

    private fun showList() {
        val recyclerView = findViewById<RecyclerView>(R.id.user_list)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val users = ArrayList<UserDetails>()
        val gson = Gson()
        var userDetails: UserDetails
        val sharedPreferences = this.getSharedPreferences("PREFS_FILENAME", 0)
        if (sharedPreferences.getInt("count", 0) == 0) textView_Empty_holder.visibility =
            View.VISIBLE
        else textView_Empty_holder.visibility = View.GONE
        for (key in 1 until sharedPreferences.getInt("count", 0) + 1) {
            userDetails = gson.fromJson(
                sharedPreferences.getString(key.toString(), ""),
                UserDetails::class.java
            )
            users.add(userDetails)
        }

        val adapter = UserAdapter(users)

        recyclerView.adapter = adapter
    }
}