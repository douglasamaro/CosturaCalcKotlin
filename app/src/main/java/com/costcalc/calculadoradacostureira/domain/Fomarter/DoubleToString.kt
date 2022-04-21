package com.costcalc.calculadoradacostureira.domain.Fomarter

import java.util.*

class DoubleToString {

    fun FormatLocale(number: Double): String {
        Locale.setDefault(Locale("pt", "BR"))
        return String.format("R$" + "%.1f", number)
    }

    fun FormatPercent(number: Double): String {
        Locale.setDefault(Locale("pt", "BR"))
        var strin: String = String.format("%.0f", number)
        return strin + "%"
    }
}