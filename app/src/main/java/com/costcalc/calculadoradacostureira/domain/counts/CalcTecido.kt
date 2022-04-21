package com.costcalc.calculadoradacostureira.domain.counts

import com.costcalc.calculadoradacostureira.domain.Fomarter.DoubleToString

class CalcTecido(val pago: Double, val areaComprada: Double, val areaUsada: Double) {

    fun ContaFinalTecido(): String {
        val resultado = (pago / areaComprada) * areaUsada
        return DoubleToString().FormatLocale(resultado)
    }
}