package com.example.contador.helper

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.contador.activities.HomeActivity
import com.example.contador.activities.LoginActivity

fun Context.toast(message: String) =
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

fun Context.login(){
    val intent = Intent(this, HomeActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    startActivity(intent)
}

fun Context.logout(){
    val intent = Intent(this, LoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    startActivity(intent)
}