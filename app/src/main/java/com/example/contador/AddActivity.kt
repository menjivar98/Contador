package com.example.contador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.example.contador.models.Contador
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    var contadorA: Int = 0
    var contadorB: Int = 0
    var ganardor: String = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        bt_save.setOnClickListener {
            if(et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombres de los equipo")

            }else{
                val contador = Contador()
                contador.nombreEquipoA = et_team_a.text.toString()
                contador.nombreEquipoB = et_team_b.text.toString()
                contador.contadorTotalA = tv_score_team_a.text.toString().toInt()
                contador.contadorTotalB = tv_score_team_b.text.toString().toInt()

                if (contadorA > contadorB){
                    contador.ganador = et_team_a.text.toString()
                }else if(contadorA < contadorB){
                    contador.ganador = et_team_b.text.toString()
                }else if (contadorA == contadorB){
                    contador.ganador = "Se un empate"
                }

                et_team_a.setText(" ")
                et_team_b.setText(" ")

                MainActivity.dbHandler.addContador(this,contador)
                et_team_a.requestFocus()

                tv_score_team_a.setText("0")
                tv_score_team_b.setText("0")

                contadorA= 0
                contadorB = 0

            }
        }




        /*Botones del equipo A*/
        bt_team_a_3_p.setOnClickListener {
            if (et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombre de equipo")
                et_team_a.requestFocus()
                et_team_b.requestFocus()

            }else{
                contadorA +=3
                tv_score_team_a.setText(contadorA.toString())
            }
        }

        bt_team_a_2_p.setOnClickListener {
            if (et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombre de equipo")
                et_team_a.requestFocus()
                et_team_b.requestFocus()
            }else{
                contadorA +=2
                tv_score_team_a.setText(contadorA.toString())
            }
        }

        bt_team_a_free_throw.setOnClickListener {
            if (et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombre de equipo")
                et_team_a.requestFocus()
                et_team_b.requestFocus()
            }else{
                contadorA ++
                tv_score_team_a.setText(contadorA.toString())
            }
        }

        /*Botones del equipo b*/
        bt_team_b_3_p.setOnClickListener {
            if (et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombre de equipo")
                et_team_a.requestFocus()
                et_team_b.requestFocus()
            }else{
                contadorB +=3
                tv_score_team_b.setText(contadorB.toString())
            }
        }

        bt_team_b_2_p.setOnClickListener {
            if (et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombre de equipo")
                et_team_a.requestFocus()
                et_team_b.requestFocus()
            }else{
                contadorB +=2
                tv_score_team_b.setText(contadorB.toString())
            }
        }

        bt_team_b_free_throw.setOnClickListener {
            if (et_team_a.text.isEmpty() || et_team_b.text.isEmpty()){
                showMessage("LLenar los nombre de equipo")
                et_team_a.requestFocus()
                et_team_b.requestFocus()
            }else{
                contadorB ++
                tv_score_team_b.setText(contadorB.toString())
            }
        }

    }


    fun showMessage(texto: String){
        Toast.makeText(applicationContext,texto,Toast.LENGTH_SHORT).show()
    }

}
