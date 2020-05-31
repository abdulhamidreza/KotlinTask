package com.example.userforam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val intent = intent
        val userDetailsJson = intent.getStringExtra("jsonString")

        initView(userDetailsJson)
        save_btn.setOnClickListener {

            onSaveAndContinue(userDetailsJson, ::saveDataInPrefs)
        }

    }

    private fun initView(userDetailsJson: String) {
        val gson = Gson()
        val userDetails = gson.fromJson(userDetailsJson, UserDetails::class.java)
        textView_name.text = textView_name.text.toString().plus(userDetails.name)
        textView_number.text = textView_number.text.toString().plus(userDetails.number)
        textView_email.text = textView_email.text.toString().plus(userDetails.email)
        textView_age.text = textView_age.text.toString().plus(userDetails.age)
        textView_city.text = textView_city.text.toString().plus(userDetails.city)
        textView_desccription.text = userDetails.description
    }

    private fun onSaveAndContinue(userDetailsJson: String, save: (String) -> Unit) {
        save(userDetailsJson)
        val intent = Intent(this, UserListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun saveDataInPrefs(userDetailsJson: String) {
        val sharedPreferences = this.getSharedPreferences("PREFS_FILENAME", 0)
        val key = sharedPreferences.getInt("count", 0) + 1
        sharedPreferences.edit { putInt("count", key) }
        //Android KTX Core functions, (lambda)
        sharedPreferences.edit { putString(key.toString(), userDetailsJson) }

    }

}