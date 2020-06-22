package com.dnyindia.wma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webacctivity.*

class webacctivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webacctivity)

        val builde :Bundle?=intent.extras
        val linkadd= builde!!.getString("link")

        web.webViewClient = WebViewClient()
        web.loadUrl(linkadd)

        home.setOnClickListener()
        {
            val intent= Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }

    }
}