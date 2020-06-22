package com.dnyindia.wma.utils
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList
import java.util.HashMap
class DatabaseHelper(context:Context):SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db:SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_CON + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_AMN + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }
    override fun onUpgrade(db:SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun insertData(name:String, contact:String, address:String, amount:String) {
        //Get the Data Repository in write mode
        val db = this.getWritableDatabase()
        //Create a new map of values, where column names are the keys
        val cValues = ContentValues()
        cValues.put(KEY_NAME, name)
        cValues.put(KEY_LOC, address)
        cValues.put(KEY_CON, contact)
        cValues.put(KEY_AMN, amount)
        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(TABLE_NAME, null, cValues)
        db.close()
    }
    // Get User Details
    fun GetUsers():ArrayList<HashMap<String, String>> {
        val db = this.getWritableDatabase()
        val userList = ArrayList<HashMap<String, String>>()
        val query = "SELECT name, address, contact,amount FROM " + TABLE_NAME
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext())
        {
            val user = HashMap<String, String>()
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)))
            user.put("contact", cursor.getString(cursor.getColumnIndex(KEY_CON)))
            user.put("amount", cursor.getString(cursor.getColumnIndex(KEY_AMN)))
            user.put("address", cursor.getString(cursor.getColumnIndex(KEY_LOC)))
            userList.add(user)
        }
        return userList
    }
    // Get User Details based on userid
    fun GetUserByUserId(userid:Int):ArrayList<HashMap<String, String>> {
        val db = this.getWritableDatabase()
        val userList = ArrayList<HashMap<String, String>>()
        val query = "SELECT name, address, contact, amount FROM " + TABLE_NAME
        val cursor = db.query(TABLE_NAME, arrayOf<String>(KEY_NAME, KEY_LOC, KEY_CON, KEY_AMN), KEY_ID + "=?", arrayOf<String>((userid).toString()), null, null, null, null)
        if (cursor.moveToNext())
        {
            val user = HashMap<String, String>()
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)))
            user.put("contact", cursor.getString(cursor.getColumnIndex(KEY_CON)))
            user.put("amount", cursor.getString(cursor.getColumnIndex(KEY_AMN)))
            user.put("address", cursor.getString(cursor.getColumnIndex(KEY_LOC)))
            userList.add(user)
        }
        return userList
    }
    // Delete User Details
    fun DeleteUser(userid:Int) {
        val db = this.getWritableDatabase()
        db.delete(TABLE_NAME, KEY_ID + " = ?", arrayOf<String>((userid).toString()))
        db.close()
    }
    companion object {
        val DATABASE_NAME = "history1.db"
        val TABLE_NAME = "log1"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_CON = "contact"
        private val KEY_AMN = "amount"
        private val KEY_LOC = "address"
    }
}