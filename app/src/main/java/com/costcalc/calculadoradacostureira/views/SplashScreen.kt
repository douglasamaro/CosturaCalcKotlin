package com.costcalc.calculadoradacostureira.views

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.costcalc.calculadoradacostureira.R
import java.lang.Exception

class SplashScreen : AppCompatActivity(){

    var mnome = "teste"
    var mvalorHora = "1234"
    var bancoDados: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        bancoDados = openOrCreateDatabase("DB_DADOS", MODE_PRIVATE, null)

        val handler = Handler()
        handler.postDelayed({
            Check()
            Log.i(ContentValues.TAG, "douglas check")
        }, 5000)

    }

    fun Check() {
        bancoDados!!.execSQL("CREATE TABLE IF NOT EXISTS dados(nome VARCHAR, valorHora VARCHAR)")
        val selectQuery = "SELECT COUNT(*) FROM dados"
        var cursor: Cursor = bancoDados!!.rawQuery(selectQuery, null)
        Log.i(ContentValues.TAG, "douglas inter check")
        if (cursor != null) {
            Log.i(ContentValues.TAG, "douglas move to first")
            cursor.moveToFirst()
            if (cursor.getInt(0) == 0) {
                Log.i(ContentValues.TAG, "douglas vazio")
                bancoDados!!.execSQL("INSERT INTO dados(nome, valorHora)  VALUES('$mnome', '$mvalorHora')")
                val intent = Intent(this, Cadastro::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                Log.i(ContentValues.TAG, "douglas preenchido")
                try {
                    val selectQuery1 = "SELECT nome FROM dados"
                    val cursor1: Cursor = bancoDados!!.rawQuery(selectQuery1, null)
                    cursor1.moveToFirst()
                    @SuppressLint("Range") val snome = cursor1.getString(cursor1.getColumnIndex("nome"))
                    if (snome == "teste") {
                        Log.i(ContentValues.TAG, "douglas preenchido teste")
                        val intent = Intent(this, Cadastro::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        Log.i(ContentValues.TAG, "douglas preenchido nome")
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                } catch (e: Exception) {
                    Log.i(ContentValues.TAG, "douglas first ", e)
                }
            }
        }
    }

}