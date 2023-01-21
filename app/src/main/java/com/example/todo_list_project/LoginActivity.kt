package com.example.todo_list_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {
    private val database = UserDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val phone = findViewById<TextView>(R.id.phone)
        val login = findViewById<Button>(R.id.button)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)


        val viewmodel = ViewModelProvider(this)[LoginViewModel::class.java]

        toolbar.setNavigationOnClickListener {
            finish()
        }

        login.setOnClickListener {
            val user = database.getUserById(phone.text.toString())
            if (user != null){
                startActivity(Intent(applicationContext,HomeActivity::class.java))
                Log.d("TAG", "onCreate:$user ")

            }
            else{
                Toast.makeText(applicationContext,"This User does not exist! Please go back to register",Toast.LENGTH_LONG).show()
                Log.d("TAG", "onCreate: pue ")

            }
        }

    }
}