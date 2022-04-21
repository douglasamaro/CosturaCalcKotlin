package com.costcalc.calculadoradacostureira.domain.counts

import com.costcalc.calculadoradacostureira.domain.Fomarter.DoubleToString

class CountMain {
    var tempo : Double = 0.0
    var comprado : Double = 0.0
    var pagou : Double = 0.0
    var usado : Double = 0.0
    var adicionais : Double = 0.0
    var lucro : Double = 0.0
    var valorHora : Double = 0.0

    var formarter = DoubleToString()


    constructor(tempo: Double, pagou: Double, comprado: Double, usado: Double, adicionais: Double, lucro: Double, valorHora: Double) {
        this.tempo = tempo
        this.comprado = comprado
        this.pagou = pagou
        this.usado = usado
        this.adicionais = adicionais
        this.lucro = lucro
        this.valorHora = valorHora

    }

    fun Count(): Array<String>{
        val tecido: Double = pagou / comprado * usado
        val hora: Double = valorHora / 60 * tempo
        val total: Double = tecido + hora + adicionais
        val mlucro: Double = total / 100 * lucro
        val totalGeral: Double = mlucro + total

        val resultCalc = arrayOf(
            formarter.FormatLocale(tecido),
            formarter.FormatLocale(hora),
            formarter.FormatLocale(mlucro),
            formarter.FormatLocale(adicionais),
            formarter.FormatLocale(totalGeral)
        )
        return resultCalc
    }

    // This function is used when the requisition of the alternatives of % in the Result.kt is asked
    // is the Total without the $lucro
    fun Alternative(): Double {
        val tecido: Double = (pagou / comprado) * usado
        val hora: Double = (valorHora / 60) * tempo
        val total: Double = tecido + hora + adicionais
        return total
    }

}
