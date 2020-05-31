package com.example.userforam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    private lateinit var userDetails: UserDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        preview_btn.setOnClickListener {
            onPreviewButton()
        }

    }

    private fun onPreviewButton() {
        if (validateInputs()) {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonString = gsonPretty.toJson(userDetails)
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra("jsonString", jsonString)
            startActivity(intent)
        }
    }

    private fun validateInputs(): Boolean {

        if (editTextTextFirstName.text.isEmpty()) {
            Toast.makeText(this, "First Name is blank", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextTextSecondName.text.isEmpty()) {
            Toast.makeText(this, "Second Name is blank", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextPhone.text.isEmpty()) {
            Toast.makeText(this, "Phone Number is blank", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextTextEmailAddress.text.isEmpty()) {
            Toast.makeText(this, "Email is blank", Toast.LENGTH_LONG).show()
            return false
        }
        if (editAgeNumber.text.isEmpty()) {
            Toast.makeText(this, "Age is blank", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextTextCityName.text.isEmpty()) {
            Toast.makeText(this, "City is blank", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextDescriptin.text.isEmpty()) {
            Toast.makeText(this, "Description is blank", Toast.LENGTH_LONG).show()
            return false
        }

        userDetails = UserDetails(  // Object of data class
            String.addNameParts(
                firstName = editTextTextFirstName.text.toString(),   //Named Argument
                secondName = editTextTextSecondName.text.toString()

            ),
            editTextPhone.text.toString(),
            editTextTextEmailAddress.text.toString(),
            editAgeNumber.text.toString(),
            editTextTextCityName.text.toString(),
            editTextDescriptin.text.toString()
        )
        return true
    }

}

//Extension Function
//Default Argument
//String Templates
private fun String.Companion.addNameParts(
    firstName: String,
    secondName: String,
    salutation: String = "Mr"
): String {
    return "$salutation $firstName $secondName"
}


