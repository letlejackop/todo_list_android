package com.example.todo_list_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class AddTaskActivity : AppCompatActivity() {

    private val database = TaskDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        val add = findViewById<Button>(R.id.button)
        val task_name = findViewById<EditText>(R.id.name)
        val iscomplete = findViewById<RadioGroup>(R.id.iscomplete)
        val yes = findViewById<RadioButton>(R.id.yes)
        val no = findViewById<RadioButton>(R.id.no)

        var completed = false
        iscomplete.setOnCheckedChangeListener { group, checkedId ->
            when{
                yes.isChecked ->{
                    completed = true
                }
                no.isChecked ->{
                    completed = false
                }
            }
        }

        add.setOnClickListener {
            database.insertTask(task_name.text.toString(),completed)
            finish()
        }
    }
}