package com.example.contador.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contador.R
import com.example.contador.adapter.ContadorAdapter
import com.example.contador.db.DBHandler
import com.example.contador.helper.logout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    companion object {
        lateinit var dbHandler: DBHandler
    }

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = DBHandler(this,null,null,1 )

        viewContadores()

        mAuth = FirebaseAuth.getInstance()

        fab.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
        }




    }



    private fun viewContadores(){
        val contadoreslist = dbHandler.getContador(this)
        val adapter = ContadorAdapter(this,contadoreslist)
        val rv: RecyclerView = findViewById(R.id.rv)

        rv.layoutManager  = LinearLayoutManager(this,RecyclerView.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {

        viewContadores()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.action_bar,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.salir){
            FirebaseAuth.getInstance().signOut()
            logout()
        }

        return super.onOptionsItemSelected(item)
    }
}
