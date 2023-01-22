package com.example.todo_list_project

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class TaskDatabase(
    context: Context
): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        //database info
        const val DATABASE_NAME = "todo-database"
        const val DATABASE_VERSION = 1

        //table info
        const val TABLE_NAME = "task_table"
        const val COLUMN_ID = "id"
        const val COLUMN_TASK = "task_note"
        const val COLUMN_IS_COMPLETED = "is_completed"
        const val COLUMN_USER_PHONE = "user_phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val initTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY," +
                " $COLUMN_TASK TEXT, " +
                " $COLUMN_IS_COMPLETED BOOLEAN," +
                "$COLUMN_USER_PHONE TEXT);"
        db.execSQL(initTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        when (p2) {
            2 -> {
            }
        }
    }

    fun insertTask(task_note: String, isCompleted: Boolean,phone:String) {
        val database = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TASK, task_note)
        values.put(COLUMN_IS_COMPLETED, if (isCompleted) 1 else 0)
        values.put(COLUMN_USER_PHONE,phone )
        database.insert(TABLE_NAME, null, values)
    }

    fun getTasks(phone:String): List<TaskModel> {
        val tasks = arrayListOf<TaskModel>()

        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_USER_PHONE = '$phone'"
        try {
            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                if (cursor.count >= 0) {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val task_note = cursor.getString(cursor.getColumnIndex(COLUMN_TASK))
                    val isCompleted = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_COMPLETED))

                    val userInfo =
                        TaskModel(id = id, task_note = task_note, isCompleted = (isCompleted == 1),phone=phone)
                    tasks.add(userInfo)
                }
            }
        } catch (exception: SQLiteException) {
            Log.d("exception", "getUsers: ${exception.message}")
        }
        return tasks
    }


    fun changeTask(id: Int, new: Boolean) {
        val database = writableDatabase
        val values = ContentValues()

        if (new)
            values.put(COLUMN_IS_COMPLETED,1)
        else
            values.put(COLUMN_IS_COMPLETED,0)
        database.update(TABLE_NAME, values, "$COLUMN_ID = $id", null)
    }

    fun deleteTask(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = '$id'", null)

    }
}
