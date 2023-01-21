package com.example.todo_list_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider

class RegisterActivity : AppCompatActivity() {
    private val database = UserDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val phone = findViewById<TextView>(R.id.phone)
        val name = findViewById<TextView>(R.id.name)
        val email = findViewById<TextView>(R.id.email)

        val register = findViewById<Button>(R.id.button)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)


        toolbar.setNavigationOnClickListener {
            finish()
        }

        register.setOnClickListener {
            val user = database.getUserById(phone.text.toString())
            if (user != null)
                Toast.makeText(
                    applicationContext,
                    "A user with phone Number ${user.phone} already exists Please go back to Login page",
                    Toast.LENGTH_LONG
                ).show()
            else {
                val phoneNum = phone.text.toString()
                val name = name.text.toString()
                val email = email.text.toString()
                database.insertUser(phoneNum, name, email)
                Toast.makeText(applicationContext, "Registered successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}