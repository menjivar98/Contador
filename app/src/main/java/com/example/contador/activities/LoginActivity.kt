package com.example.contador.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.contador.R
import com.example.contador.helper.login
import com.example.contador.helper.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        button_sign_in.setOnClickListener {
            val email = text_email.text.toString().trim()
            val password = edit_text_password.text.toString().trim()

            if (email.isEmpty()){
                text_email.error = "El correo es requerido"
                text_email.requestFocus()
                return@setOnClickListener
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                text_email.error = "Se nececita un correo valido"
                text_email.requestFocus()
                return@setOnClickListener
            }else if (password.isEmpty() || password.length < 6){
                edit_text_password.error = "Se necesita minimo 6 caracteres"
                edit_text_password.requestFocus()
                return@setOnClickListener
            }

            loginUser(email,password)

        }

        text_view_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        progressbar.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                progressbar.visibility = View.GONE
                if (task.isSuccessful){

                    login()

                }else{
                    task.exception?.message?.let {
                        toast(it)
                    }
                }

            }

    }

    override fun onStart() {
        super.onStart()

        mAuth.currentUser?.let {
            login()
        }
    }

}
