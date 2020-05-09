package com.dnyindia.wma


import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calpay.*


class calpayActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calpay)

        var dBase: SQLiteDatabase = openOrCreateDatabase(
            "history",
            Context.MODE_PRIVATE, null)
        dBase.execSQL("create table if not exists log(name varchar(50),contact varchar(50),address varchar(50),amount varchar(50))")



        var amount =0.0
        val builde :Bundle?=intent.extras
        val msg= builde!!.getString("user_data")
        val data = msg!!.split("/").map { it.trim() }
        uname.text=data[0]
        cno.text=data[2]
        address.text=data[3]



        totalcal.setOnClickListener(){
            val d: String =dry.text.toString()
            val w: String =wet.text.toString()
            val m: String =metal.text.toString()
            val g: String =glass.text.toString()
            val t= d.toFloat()*15+w.toFloat()*5+m.toFloat()*54+g.toFloat()*10
            total.text=t.toString()+" Rs. Only"
            amount= t.toDouble()
        }
        pay.setOnClickListener {

            var cv = ContentValues( )
            cv.put("name",data[0])
            cv.put("Contact",data[2])
            cv.put("address",data[3])
            cv.put("amount",amount.toString())
            var status:Long = dBase.insert("log",
                null, cv)
            if(status != -1L){
                Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Record insertion  is failed..", Toast.LENGTH_LONG).show()
            }
            dBase.close()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }





    }
}
