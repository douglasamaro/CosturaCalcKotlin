package com.costcalc.calculadoradacostureira.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.costcalc.calculadoradacostureira.R
import kotlinx.android.synthetic.main.activity_tutorial.*
import java.util.*


class Tutorial : AppCompatActivity() {

    var valorDaHoraFinal = 0.0 // se houver algum problema no banco de dados, o app irá assumir valor da hora = 0
    var dias = 0 // se o usuário não checar nenhum checkbox, ele vai assumir = 0 | e vai pedir para o usuário selecionar ao menos 1
    var msg: String? = null // String para o toast, ela assume um diferente valor mediante a condição que cair

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        Locale.setDefault(Locale("pt", "BR")) // para a formatação de numero em virgula
    }

    fun CalcSalv(view: View?) {

        dias = 0
        if (checkboxseg.isChecked) {
            dias += 1
        }
        if (checkboxter.isChecked) {
            dias += 1
        }
        if (checkboxqua.isChecked) {
            dias += 1
        }
        if (checkboxqui.isChecked) {
            dias += 1
        }
        if (checkboxsex.isChecked) {
            dias += 1
        }
        if (checkboxsab.isChecked) {
            dias += 1
        }
        if (checkboxdom.isChecked) {
            dias += 1
        }
        if (valorDaHoraFinal == 0.0) {
            if (salario.text.toString() == "" || horas.text.toString() == "" || dias == 0) {
                msg = "é preciso que você preencha todos os campos e pelo menos um dia da semana"
                val contexto = applicationContext
                val texto = msg!!
                val duracao = Toast.LENGTH_LONG
                val toast = Toast.makeText(contexto, texto, duracao)
                toast.show()
            } else {
                val msalario = salario.text.toString().toDouble()
                val mhoras = horas.text.toString().toDouble()
                dias = dias * 4
                valorDaHoraFinal = msalario / dias / mhoras
                resultado.text =
                    String.format("A sua hora vale R$" + "%.2f", valorDaHoraFinal)
                mybtn.text = "voltar"
            }
        } else {
            val intent = Intent(this, Cadastro::class.java)
            intent.putExtra("valorhora", String.format("%.2f", valorDaHoraFinal))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val int = Intent(this@Tutorial, Cadastro::class.java)
        int.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        super.onBackPressed()
        startActivity(int)
    }
}