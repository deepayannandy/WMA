package com.dnyindia.wma


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calpay.*


class calpayActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calpay)

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
        pay.setOnClickListener() {
            Toast.makeText(this,data[2]+" Rs. "+amount.toString() , Toast.LENGTH_LONG).show()
            val i = packageManager.getLaunchIntentForPackage("net.one97.paytm")
            if (i != null) {
                startActivity(i)
            }

            else {
                var i = Intent()
                i.action = Intent.ACTION_VIEW
                i.data = Uri.parse("https://play.google.com/store/apps/details?id=net.one97.paytm&hl=en")
                startActivity(i)
            }

        }

    }
}
