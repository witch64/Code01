package com.example.code00

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        const val DATABASE_NAME = "register.db"
        const val TABLE_NAME = "REGISTER_USER"
        const val COL_1 = "USER_ID"
        const val COL_2 = "USERNAME"
        const val COL_3 = "PASSWORD"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE REGISTER_USER (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT, PASSWORD TEXT, EMAIL TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase, old_version: Int, new_version: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser (user : String, password : String) : Long{
        val db : SQLiteDatabase
        db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("USERNAME", user)
        contentValues.put("PASSWORD", password)
        val res = db.insert("$TABLE_NAME",null,contentValues)
        db.close()
        return res
    }

    fun checkUser(user : String, password : String) : Boolean{
        val db : SQLiteDatabase
        val columns : Array<String> =  arrayOf(COL_1)
        db = this.readableDatabase
        val selection : String = COL_2 + "= ?" + " AND " + COL_3 + "= ?"
        val selectionArgs : Array<String> =  arrayOf(user,password)
        val cursor : Cursor = db.query(TABLE_NAME, columns, selection,selectionArgs,
            null,null,null)
        val count : Int = cursor.count

        cursor.close()
        db.close()

        if(count > 0)
            return true
        else
            return false
    }

    fun updatePassword(user : String, password : String){
        val db : SQLiteDatabase
        db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("PASSWORD", password)
        db.update(TABLE_NAME, contentValues, COL_2 + "= ?", arrayOf(COL_2))
        db.close()
    }

    fun getUsername() {
        var username = ""
        val cursor = this.readableDatabase.query(
            TABLE_NAME, arrayOf(COL_2),
            null, null, null, null, null
        )
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}
