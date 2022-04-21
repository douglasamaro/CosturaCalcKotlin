package com.costcalc.calculadoradacostureira.views

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.costcalc.calculadoradacostureira.R
import com.costcalc.calculadoradacostureira.data.sqlite.ReturnValorHora
import com.costcalc.calculadoradacostureira.domain.counts.CountMain
import com.costcalc.calculadoradacostureira.domain.counts.LucroOptions
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_resultados.*
import java.util.*

class Resultados : AppCompatActivity() {

    // se acontecer algum problema ele assume como = 0
    var atempo : Double = 0.0
    var acomprado : Double = 0.0
    var apagou : Double = 0.0
    var ausado : Double = 0.0
    var aadicionais : Double = 0.0
    var alucro : Double = 0.0
    var avalorHora : Double = 0.0

    // val anuncio
    val adRequest = AdRequest.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        // Carregar anuncio banner
        MobileAds.initialize(this) {}
        adView.loadAd(adRequest)

        // pegar o valor da hora do usuário no banco de dados
        val bancoDados: SQLiteDatabase = openOrCreateDatabase("DB_DADOS", MODE_PRIVATE, null)
        avalorHora = ReturnValorHora().RetunrUserValorHora(bancoDados)

        // preparar para receber dados do array
        val b = this.intent.extras
        val dadosCalcArray = b?.getDoubleArray("dadosCalc")

        // pegando os dados do array e substituindo as variáveis já inicializadas com 0
        atempo = dadosCalcArray!![0]
        apagou = dadosCalcArray[1]
        acomprado = dadosCalcArray[2]
        ausado = dadosCalcArray[3]
        aadicionais = dadosCalcArray[4]
        alucro = dadosCalcArray[5]

        // Requisition of the result
        val countMain = CountMain(atempo, apagou, acomprado, ausado, aadicionais, alucro, avalorHora)
        val resultadoArray = countMain.Count()
        mtecido.text = resultadoArray[0]
        mhora.text = resultadoArray[1]
        mlucro.text = resultadoArray[2]
        madicionais.text = resultadoArray[3]
        mtotal.text = resultadoArray[4]

        // Requisition of the function alternatives for %
        val lucroreturn = countMain.Alternative()
        val lucroCalc = LucroOptions(lucroreturn, alucro)
        val lucroArray = lucroCalc.LucroCalc()

        porcent1.text = lucroArray[0]
        porcent2.text = lucroArray[1]
        porcent3.text = lucroArray[2]
        porcent4.text = lucroArray[3]
        resultporcent1.text = lucroArray[4]
        resultporcent2.text = lucroArray[5]
        resultporcent3.text = lucroArray[6]
        resultporcent4.text = lucroArray[7]
    }

    fun Voltar(view: View?) {
        val int = Intent(this, MainActivity::class.java)
        startActivity(int)
    }

    override fun onBackPressed() {
        val int = Intent(this@Resultados, MainActivity::class.java)
        int.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        super.onBackPressed()
        startActivity(int)
    }
}
