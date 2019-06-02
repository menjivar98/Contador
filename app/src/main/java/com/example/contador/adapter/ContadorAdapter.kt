package com.example.contador.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.contador.R
import com.example.contador.models.Contador
import kotlinx.android.synthetic.main.item_contador.view.*

class ContadorAdapter(mCtx : Context, val contador : ArrayList<Contador>) :
    RecyclerView.Adapter<ContadorAdapter.ViewHolder>() {


    val mCtx = mCtx

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContadorAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contador,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return contador.size
    }

    override fun onBindViewHolder(holder: ContadorAdapter.ViewHolder, position: Int) {
        val contador : Contador = contador[position]
        holder.txtNameA.text = contador.nombreEquipoA
        holder.txtNameb.text = contador.nombreEquipoB
        holder.contadora.text = contador.contadorTotalA.toString()
        holder.contadorb.text = contador.contadorTotalB.toString()
        holder.ganador.text = contador.ganador
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNameA = itemView.txtequipoa
        val txtNameb = itemView.txtequipob
        val contadora = itemView.txt_punto_A
        val contadorb = itemView.txt_punto_b
        val ganador = itemView.txt_ganador
    }
}