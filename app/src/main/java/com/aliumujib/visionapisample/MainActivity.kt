package com.aliumujib.visionapisample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aliumujib.visionapisample.codescanner.BarcodeScannerActivity
import com.aliumujib.visionapisample.codescanner.BarcodeScannerActivity.Companion.TICKET_SCANNER_REQUEST
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        scan_code_btn.setOnClickListener {
            BarcodeScannerActivity.start(this, TICKET_SCANNER_REQUEST)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == TICKET_SCANNER_REQUEST) {
            var barcode = data!!.getParcelableExtra<Barcode>(BarcodeScannerActivity.TICKET_SCANNER_RESPONSE)
            result.text = barcode.rawValue
        }

    }

}
