package com.example.todo_list_project

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class UserDatabase(
    context: Context
): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        //database info
        const val DATABASE_NAME="todo_database"
        const val DATABASE_VERSION = 1
        //table info
        const val TABLE_NAME = "user_table"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val initTable = "CREATE TABLE $TABLE_NAME ($COLUMN_PHONE TEXT PRIMARY KEY," +
                " $COLUMN_NAME TEXT, " +
                " $COLUMN_EMAIL TEXT);"
        db.execSQL(initTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        when(p2){
            2->{
                val addColumn = "ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_PHONE TEXT DEFAULT '05XXXXXXXX'"
                db.execSQL(addColumn)
                onCreate(db)
            }
        }
    }

    fun insertUser(phone:String,name:String,email:String){
        val database = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_EMAIL, email)

        database.insert(TABLE_NAME, null, values)
    }

    fun getUsers(): List<UserModel>{
        val users = arrayListOf<UserModel>()

        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        try {
            val cursor = db.rawQuery(query,null)
            while (cursor.moveToNext()) {
                if (cursor.count >= 0){
                    val phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))
                    val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                    val userInfo = UserModel(phone =phone,name=name, email = email)
                    users.add(userInfo)
                }
            }
        }catch (exception: SQLiteException){
            Log.d("exception","getUsers: ${exception.message}")
        }
        return users
    }
    fun getUserById(phone:String): UserModel? {
        var user: UserModel? = null
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_PHONE = '$phone'"
        try {
            val cursor = db.rawQuery(query,null)
            while (cursor.moveToNext()) {
                if (cursor.count >= 0){
                    val phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))
                    val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                    user = UserModel(phone =phone,name=name, email = email)
                }
            }
        }catch (exception: SQLiteException){
            Log.d("exception","getUser: ${exception.message}")
        }
        return user
    }

    fun changeUser(newEmail:String,oldEmail:String){
        val database = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_EMAIL,newEmail)
        database.update(TABLE_NAME,values,"$COLUMN_EMAIL = '$oldEmail'",null)
    }

    fun deleteUser(phone: String){
        val db = writableDatabase
        db.delete(TABLE_NAME,"$COLUMN_PHONE = '$phone'", null)

    }
}