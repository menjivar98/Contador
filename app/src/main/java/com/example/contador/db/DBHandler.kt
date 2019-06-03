package com.example.contador.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.contador.models.Contador
import java.lang.Exception

class DBHandler (context: Context, name : String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "Mi_data.db"
        private val DATABASE_VERSION = 1

        val CONTADOR_TABLE_NAME = "Contador"
        val CONTADOR_ID = "_id"
        val NOMBRE_EQUIPO_A = "equipo_a"
        val CONTADOR_TOTAL_A = "total_a"
        val NOMBRE_EQUIPO_B = "equipo_b"
        val CONTADOR_TOTAL_B = "total_b"
        val GANADOR = "ganador_partido"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTADOR_TABLE: String = ("CREATE TABLE $CONTADOR_TABLE_NAME (" +
                "$CONTADOR_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOMBRE_EQUIPO_A TEXT," +
                "$CONTADOR_TOTAL_A INTEGER DEFAULT 0," +
                "$NOMBRE_EQUIPO_B TEXT , " +
                "$CONTADOR_TOTAL_B INTEGER DEFAULT 0," +
                "$GANADOR TEXT)")
        db?.execSQL(CREATE_CONTADOR_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getContador(mCtx: Context): ArrayList<Contador> {
        val qry = "SELECT * FROM $CONTADOR_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val contadores = ArrayList<Contador>()

        if (cursor.count == 0) {
            Toast.makeText(mCtx, "Registros No encontrados", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                val contador = Contador()
                contador.contadorID = cursor.getInt(cursor.getColumnIndex(CONTADOR_ID))
                contador.nombreEquipoA = cursor.getString(cursor.getColumnIndex(NOMBRE_EQUIPO_A))
                contador.nombreEquipoB = cursor.getString(cursor.getColumnIndex(NOMBRE_EQUIPO_B))
                contador.contadorTotalA = cursor.getInt(cursor.getColumnIndex(CONTADOR_TOTAL_A))
                contador.contadorTotalB = cursor.getInt(cursor.getColumnIndex(CONTADOR_TOTAL_B))
                contador.ganador = cursor.getString(cursor.getColumnIndex(GANADOR))

                contadores.add(contador)

            }

            Toast.makeText(mCtx, "${cursor.count.toString()} Registros encontrados", Toast.LENGTH_SHORT).show()
        }

        cursor.close()
        db.close()
        return contadores
    }

    fun addContador(mCtx: Context,contador: Contador){
        val values = ContentValues()

        values.put(NOMBRE_EQUIPO_A,contador.nombreEquipoA)
        values.put(NOMBRE_EQUIPO_B,contador.nombreEquipoB)
        values.put(CONTADOR_TOTAL_A,contador.contadorTotalA)
        values.put(CONTADOR_TOTAL_B,contador.contadorTotalB)
        values.put(GANADOR,contador.ganador)

        val db = this.writableDatabase
        try {
            db.insert(CONTADOR_TABLE_NAME,null,values)
            Toast.makeText(mCtx,"Contador Agregado",Toast.LENGTH_SHORT).show()

        }catch (e : Exception){
            Toast.makeText(mCtx,e.message,Toast.LENGTH_SHORT).show()
        }

        db.close()

    }




}


















/*
*
*  val CONTADOR_EQUIPO_A_3 = "anotaciones_3"
    val CONTADOR_EQUIPO_A_2 = "anotaciones_2"
    val CONTADOR_EQUIPO_A_FREE = "anotaciones_free"

    "$CONTADOR_EQUIPO_A_3 INTEGER DEFAULT 0," +
            "$CONTADOR_EQUIPO_A_2 INTEGER DEFAULT 0," +
            "$CONTADOR_EQUIPO_A_FREE INTEGER DEFAULT 0, " +

             val CONTADOR_EQUIPO_B_3 = "anotaciones_3"
    val CONTADOR_EQUIPO_B_2 = "anotaciones_2"
    val CONTADOR_EQUIPO_B_FREE = "anotaciones_free"

     "$CONTADOR_EQUIPO_B_3 INTEGER DEFAULT 0, " +
            "$CONTADOR_EQUIPO_B_2 INTEGER DEFAULT 0 ," +
            "$CONTADOR_EQUIPO_B_FREE INTEGER DEFAULT 0," +



    */