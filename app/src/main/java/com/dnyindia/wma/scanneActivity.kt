package com.dnyindia.wma

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_scan.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class scanneActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)


        Dexter.withActivity(this).withPermission(android.Manifest.permission.CAMERA).withListener(object:
            PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                zxscan.setResultHandler(this@scanneActivity)
                zxscan.startCamera()
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest?,
                token: PermissionToken?
            ) {

            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                Toast.makeText(this@scanneActivity,"You should enable this permission", Toast.LENGTH_SHORT).show()

            }

        }).check()
    }

    override fun handleResult(rawResult: Result?) {
        resp.text= rawResult!!.text

        val intent= Intent(this,calpayActivity::class.java)
        intent.putExtra("user_data",rawResult.text)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
