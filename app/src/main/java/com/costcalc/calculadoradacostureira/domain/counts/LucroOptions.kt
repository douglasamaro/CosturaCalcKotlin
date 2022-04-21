package com.costcalc.calculadoradacostureira.domain.counts

import com.costcalc.calculadoradacostureira.domain.Fomarter.DoubleToString

class LucroOptions(var total: Double, var lucro: Double) {
    var formarter = DoubleToString()

    fun LucroCalc(): Array<String> {
        var porcent1: Double = 0.0  // se o usuario não colocar nada, esse será o resultado
        var porcent2: Double = 0.0
        var porcent3: Double = 0.0
        var porcent4: Double = 0.0
        var resultporcent1: Double = 0.0
        var resultporcent2: Double = 0.0
        var resultporcent3: Double = 0.0
        var resultporcent4: Double = 0.0
        var resultLucro = arrayOf("err", "err", "err", "err", "err", "err", "err", "err")

        //catalogar o grupo que o lucro está 0 - 5
        if ((lucro >= 0) && (lucro < 21)) {
            porcent1 = lucro + 15
            porcent2 = lucro + 38
            porcent3 = lucro + 56
            porcent4 = lucro + 73
            resultporcent1 = (total / 100) * porcent1 + total
            resultporcent2 = (total / 100) * porcent2 + total
            resultporcent3 = (total / 100) * porcent3 + total
            resultporcent4 = (total / 100) * porcent4 + total
            resultLucro = arrayOf(
                formarter.FormatPercent(porcent1),
                formarter.FormatPercent(porcent2),
                formarter.FormatPercent(porcent3),
                formarter.FormatPercent(porcent4),
                formarter.FormatLocale(resultporcent1),
                formarter.FormatLocale(resultporcent2),
                formarter.FormatLocale(resultporcent3),
                formarter.FormatLocale(resultporcent4)
            )
        } else if ((lucro > 20) && (lucro < 41)) {
            porcent1 = lucro - 17
            porcent2 = lucro - 7
            porcent3 = lucro + 23
            porcent4 = lucro + 47
            resultporcent1 = (total / 100) * porcent1 + total
            resultporcent2 = (total / 100) * porcent2 + total
            resultporcent3 = (total / 100) * porcent3 + total
            resultporcent4 = (total / 100) * porcent4 + total
            resultLucro = arrayOf(
                formarter.FormatPercent(porcent1),
                formarter.FormatPercent(porcent2),
                formarter.FormatPercent(porcent3),
                formarter.FormatPercent(porcent4),
                formarter.FormatLocale(resultporcent1),
                formarter.FormatLocale(resultporcent2),
                formarter.FormatLocale(resultporcent3),
                formarter.FormatLocale(resultporcent4)
            )
        } else if ((lucro > 40) && (lucro < 61)) {
            porcent1 = lucro - 26
            porcent2 = lucro - 11
            porcent3 = lucro + 15
            porcent4 = lucro + 24
            resultporcent1 = (total / 100) * porcent1 + total
            resultporcent2 = (total / 100) * porcent2 + total
            resultporcent3 = (total / 100) * porcent3 + total
            resultporcent4 = (total / 100) * porcent4 + total
            resultLucro = arrayOf(
                formarter.FormatPercent(porcent1),
                formarter.FormatPercent(porcent2),
                formarter.FormatPercent(porcent3),
                formarter.FormatPercent(porcent4),
                formarter.FormatLocale(resultporcent1),
                formarter.FormatLocale(resultporcent2),
                formarter.FormatLocale(resultporcent3),
                formarter.FormatLocale(resultporcent4)
            )
        } else if ((lucro > 60) && (lucro < 81)) {
            porcent1 = lucro - 47
            porcent2 = lucro - 27
            porcent3 = lucro - 7
            porcent4 = lucro + 17
            resultporcent1 = (total / 100) * porcent1 + total
            resultporcent2 = (total / 100) * porcent2 + total
            resultporcent3 = (total / 100) * porcent3 + total
            resultporcent4 = (total / 100) * porcent4 + total
            resultLucro = arrayOf(
                formarter.FormatPercent(porcent1),
                formarter.FormatPercent(porcent2),
                formarter.FormatPercent(porcent3),
                formarter.FormatPercent(porcent4),
                formarter.FormatLocale(resultporcent1),
                formarter.FormatLocale(resultporcent2),
                formarter.FormatLocale(resultporcent3),
                formarter.FormatLocale(resultporcent4)
            )
        } else if (lucro > 80) {
            porcent1 = lucro - 67
            porcent2 = lucro - 49
            porcent3 = lucro - 33
            porcent4 = lucro - 15
            resultporcent1 = (total / 100) * porcent1 + total
            resultporcent2 = (total / 100) * porcent2 + total
            resultporcent3 = (total / 100) * porcent3 + total
            resultporcent4 = (total / 100) * porcent4 + total
            resultLucro = arrayOf(
                formarter.FormatPercent(porcent1),
                formarter.FormatPercent(porcent2),
                formarter.FormatPercent(porcent3),
                formarter.FormatPercent(porcent4),
                formarter.FormatLocale(resultporcent1),
                formarter.FormatLocale(resultporcent2),
                formarter.FormatLocale(resultporcent3),
                formarter.FormatLocale(resultporcent4)
            )
        } else if (lucro < 0) {
            porcent1 = lucro + 15
            porcent2 = lucro + 33
            porcent3 = lucro + 49
            porcent4 = lucro + 67
            resultporcent1 = (total / 100) * porcent1 + total
            resultporcent2 = (total / 100) * porcent2 + total
            resultporcent3 = (total / 100) * porcent3 + total
            resultporcent4 = (total / 100) * porcent4 + total
            resultLucro = arrayOf(
                formarter.FormatPercent(porcent1),
                formarter.FormatPercent(porcent2),
                formarter.FormatPercent(porcent3),
                formarter.FormatPercent(porcent4),
                formarter.FormatLocale(resultporcent1),
                formarter.FormatLocale(resultporcent2),
                formarter.FormatLocale(resultporcent3),
                formarter.FormatLocale(resultporcent4)
            )
        }
        return resultLucro
    }
}
