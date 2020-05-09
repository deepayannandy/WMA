package com.dnyindia.wma
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dBase: SQLiteDatabase = openOrCreateDatabase(
            "history",
            Context.MODE_PRIVATE, null)
        dBase.execSQL("create table if not exists log(name varchar(50),contact varchar(50),address varchar(50),amount varchar(50))")


        scan.setOnClickListener{

            val intent=Intent(this,scanneActivity::class.java)
            startActivity(intent)

        }
        Logs.setOnClickListener{

            var c:Cursor= dBase.query("log", null,null,null,null,null,null)
            Toast.makeText(this, c.toString(), Toast.LENGTH_LONG).show()
            /*var cAdapter = SimpleCursorAdapter(
                this,R.layout.row, c, arrayOf("name","contact","address","amount"), intArrayOf(R.id.title_name,R.id.text_contact,R.id.text_address,R.id.text_amount),0)

            list_data.adapter = cAdapter*/
        }

    }


}
