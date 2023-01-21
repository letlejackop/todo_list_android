package com.example.todo_list_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    private lateinit var adapter :TaskRecyclerView
    private lateinit var TaskList: RecyclerView
    private val database = TaskDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        TaskList = findViewById(R.id.list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            startActivity(Intent(applicationContext,AddTaskActivity::class.java))
        }

        listAdapter()
    }

    private fun listAdapter(){
        adapter = TaskRecyclerView(database.getTasks())
        TaskList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        listAdapter()
    }
}