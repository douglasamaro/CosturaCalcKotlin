package com.costcalc.calculadoradacostureira.data.sqlite

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.lang.Double.parseDouble

class ReturnValorHora {

    fun RetunrUserValorHora(bancoDados: SQLiteDatabase) : Double {
        val selectQuery = "SELECT valorHora FROM dados"
        val cursor: Cursor = bancoDados.rawQuery(selectQuery, null)
        cursor.moveToFirst()
        @SuppressLint("Range")val mvalorHora = cursor.getString(cursor.getColumnIndex("valorHora"))

        return parseDouble(mvalorHora)
    }
}