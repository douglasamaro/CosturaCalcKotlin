package com.costcalc.calculadoradacostureira.views

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.costcalc.calculadoradacostureira.R
import kotlinx.android.synthetic.main.activity_cadastro.*
import java.lang.Exception

class Cadastro : AppCompatActivity() {

    var bancoDados: SQLiteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        bancoDados = openOrCreateDatabase("DB_DADOS", MODE_PRIVATE, null)

        bancoDados!!.execSQL("CREATE TABLE IF NOT EXISTS dados(nome VARCHAR, valorHora VARCHAR)")

    }

    fun Salvar(view: View?) {

        try {
            if (nome.text.toString() == "" || valorHora.text.toString() == "") {
                val contexto = applicationContext
                val texto = "preencha todos os campos"
                val duracao = Toast.LENGTH_LONG
                val toast = Toast.makeText(contexto, texto, duracao)
                toast.show()
            } else {
                val mnome = nome.text.toString()
                val mvalorHora = valorHora.text.toString()
                bancoDados!!.execSQL("UPDATE dados SET nome = '$mnome', valorHora = '$mvalorHora'")
                Log.i(ContentValues.TAG, "errod: $mnome e $mvalorHora")
                Toast.makeText(this, "dados salvos com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.i(ContentValues.TAG, "errod function Salvar() ", e)
        }
    }

    fun Tutorial(view: View?) {
        val int = Intent(this, Tutorial::class.java)
        startActivity(int)
    }

}