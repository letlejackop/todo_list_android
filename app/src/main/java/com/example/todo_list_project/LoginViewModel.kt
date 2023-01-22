package com.example.todo_list_project

import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var phone = ""
    fun checkPhone(phone:String): Boolean = phone.isEmpty()

}