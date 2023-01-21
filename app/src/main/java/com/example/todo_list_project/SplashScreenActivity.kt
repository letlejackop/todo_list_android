package com.example.todo_list_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)

        login.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }

        register.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }
    }
}