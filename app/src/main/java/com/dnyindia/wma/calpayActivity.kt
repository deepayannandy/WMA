package com.dnyindia.wma


import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dnyindia.wma.utils.DatabaseHelper
import kotlinx.android.synthetic.main.activity_calpay.*


class calpayActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calpay)

        var dBase: SQLiteDatabase = openOrCreateDatabase(
            "history",
            Context.MODE_PRIVATE, null)
        dBase.execSQL("create table if not exists log(_id integer primary key  autoincrement, name varchar(50),contact varchar(50),address varchar(50),amount varchar(50))")



        var amount =0.0
        val builde :Bundle?=intent.extras
        val msg= builde!!.getString("user_data")
        val data = msg!!.split("/").map { it.trim() }
        uname.text=data[0]
        cno.text=data[2]
        address.text=data[3]



        // calculation part
        totalcal.setOnClickListener(){
            val d: String =dry.text.toString()
            val w: String =wet.text.toString()
            val m: String =metal.text.toString()
            val g: String =glass.text.toString()
            val t= d.toFloat()*15+w.toFloat()*5+m.toFloat()*54+g.toFloat()*10
            total.text=t.toString()+" Rs. Only"
            amount= t.toDouble()
        }

        // saving the details and return to the main activity
        pay.setOnClickListener {

            val username = data[0] + "\n"
            val contact1 = data[2] + "\n"
            val address1 = data[3] + "\n"
            var dbHelper = DatabaseHelper(this)
            dbHelper.insertData(username,contact1,address1,amount.toString())

            val webstr= "https://script.google.com/macros/s/AKfycbxhT1JwYHymHef4-bkyNw2SBVElMlfMQkRmUNh-uEPuif-UHD17/exec?id=1N2veXBR2N7cVwZj2AqViG-rI1f8hrpU0DRS7E10xOU0&U_id="+" &"+"name="+username+"&contact_no="+contact1+"&address="+address1+"&amountD="+amount.toString()+"&U_id="+"1"
            val intent= Intent(this,webacctivity::class.java)
            intent.putExtra("link",webstr)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }





    }
}
