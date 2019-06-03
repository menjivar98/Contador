package com.example.contador.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contador.R
import com.example.contador.adapter.ContadorAdapter
import com.example.contador.db.DBHandler
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    companion object {
        lateinit var dbHandler: DBHandler
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = DBHandler(this,null,null,1 )

        viewContadores()

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
}
