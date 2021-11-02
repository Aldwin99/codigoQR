package com.example.codigoqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codigoqr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator



class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        biding.btnQRCode.setOnClickListener { setUpQRCode() }


    }

    private fun setUpQRCode() {
        IntentIntegrator(this)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setTorchEnabled(false)
            .setBeepEnabled(true)
            .setPrompt("Scan QR Code")
            .initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents !== null) {
                biding.textView.text = result.contents
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
        } else{
                super.onActivityResult(requestCode, resultCode, data)
            }

        }

    }




