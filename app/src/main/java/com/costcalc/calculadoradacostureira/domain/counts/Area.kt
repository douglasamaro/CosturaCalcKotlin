package com.costcalc.calculadoradacostureira.domain.counts

class Area(val largura: Double, val altura: Double) {

    fun CalcularArea(): Double {
        val area: Double =  largura * altura
        return area
    }
}