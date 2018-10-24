package com.aliumujib.visionapisample.codescanner

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.aliumujib.visionapisample.R
import kotlinx.android.synthetic.main.activity_barcode_scanner.*

class BarcodeScannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scanner)
        setSupportActionBar(toolbar)

       // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
        supportActionBar?.title = "Scan Ticket"

        supportFragmentManager.beginTransaction().replace(R.id.bar_code_scanner_fragment, BarcodeReaderFragment.newInstance()).commitNow()

    }


    companion object {
        val TICKET_SCANNER_REQUEST = 100
        val TICKET_SCANNER_RESPONSE = "TICKET_SCANNER_RESPONSE"

        @JvmStatic
        fun start(context: AppCompatActivity, reqCode: Int) {
            var intent = Intent(context, BarcodeScannerActivity::class.java)
            context.startActivityForResult(intent, reqCode)
        }

        @JvmStatic
        fun start(fragment: Fragment, reqCode: Int) {
            var intent = Intent(fragment.context, BarcodeScannerActivity::class.java)
            fragment.startActivityForResult(intent, reqCode)
        }
    }


}
