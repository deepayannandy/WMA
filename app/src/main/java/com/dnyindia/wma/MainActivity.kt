package com.dnyindia.wma
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        scan.setOnClickListener{

            val intent=Intent(this,scanneActivity::class.java)
            //intent.putExtra("user_data","Deepayan Nandy/U1/7384213622/Budbud")
            startActivity(intent)

        }
        Logs.setOnClickListener{
            Toast.makeText(this,"Logs Clicked!",Toast.LENGTH_SHORT).show()
        }

    }


}
