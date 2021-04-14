package com.example.a7minuteworkout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQliteHelper(context : Context,factory : SQLiteDatabase.CursorFactory?):
        SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {
            companion object{
              private  var DATABASE_VERSION = 1
                private val DATABASE_NAME = "7Minute"
               private val TABLE_NAME = "History"
                private val COULMN_ID = "_id"
                private val INFO_COULMN = "date"
            }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DATABASE_TABLE = ("CREATE TABLE "+ TABLE_NAME +"("+ COULMN_ID+
                                                                            " INTEGER PRIMARY KEY,"+ INFO_COULMN+" TEXT)")
        db?.execSQL(CREATE_DATABASE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)
    }

    fun addDate(date : String){
        val values = ContentValues()
        values.put(INFO_COULMN,date)
        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun getDate(): ArrayList<String>{
        val list = ArrayList<String>()
        val db = this.readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        while(cursor.moveToNext()){
            val date = cursor.getString(cursor.getColumnIndex(INFO_COULMN))
            list.add(date)
        }
        cursor.close()
        return list

    }

}