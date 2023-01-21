package com.example.todo_list_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TaskRecyclerView(
    private val tasks:List<TaskModel>
): RecyclerView.Adapter<TaskRecyclerView.TaskViewHolder>() {

    inner class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val statusImage = view.findViewById<ImageView>(R.id.statusImage)
        private val note = view.findViewById<TextView>(R.id.note)
        private val root = view.findViewById<ConstraintLayout>(R.id.root)



        fun bind (task:TaskModel){
            note.text = task.task_note
            var status =R.drawable.baseline_remove_circle_24
            if (task.isCompleted)
                status =R.drawable.baseline_check_circle_24

            statusImage.setImageResource(status)
            val context =root.context
        }



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.task_layout,parent,false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        if(holder is TaskViewHolder) holder.bind(tasks[position])
    }
    override fun getItemCount(): Int = tasks.size
}


