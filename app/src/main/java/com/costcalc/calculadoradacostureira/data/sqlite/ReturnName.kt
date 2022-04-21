package com.costcalc.calculadoradacostureira.data.sqlite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase

class ReturnName {
    fun ReturnUserName(bancoDados: SQLiteDatabase) : String {
        val searchString = "SELECT nome FROM dados"
        val cursor: Cursor = bancoDados.rawQuery(searchString, null)
        cursor.moveToFirst()
        @SuppressLint("Range")val mnome = cursor.getString(cursor.getColumnIndex("nome"))

        return "Olá, $mnome!"
    }
}