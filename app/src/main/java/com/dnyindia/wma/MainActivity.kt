package com.dnyindia.wma
import android.content.Intent
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dnyindia.wma.utils.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = DatabaseHelper(this)

        val userList = db.GetUsers()

        val adapter = SimpleAdapter(this@MainActivity, userList, R.layout.row, arrayOf<String>("name", "contact", "location","amount"), intArrayOf(R.id.title_name, R.id.text_contact, R.id.text_address,R.id.text_amount))
        list_data.adapter = adapter


        scan.setOnClickListener{

            val intent=Intent(this,scanneActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        }
        Logs.setOnClickListener{

            Toast.makeText(this,"Refreshed!",Toast.LENGTH_SHORT).show()
            list_data.adapter = adapter

        }

    }


}
