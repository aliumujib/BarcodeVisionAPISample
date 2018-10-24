package com.aliumujib.visionapisample.codescanner

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import com.aliumujib.visionapisample.codescanner.BarcodeScannerActivity.Companion.TICKET_SCANNER_RESPONSE
import com.google.android.gms.vision.barcode.Barcode


class TicketScannedListener(var activity: AppCompatActivity) : BarcodeReaderFragment.BarcodeReaderListener {

    override fun onScanned(barcode: Barcode) {
        val output = Intent()
        output.putExtra(TICKET_SCANNER_RESPONSE, barcode)
        activity.setResult(RESULT_OK, output)
        activity.finish()
    }

    override fun onScannedMultiple(barcodes: List<Barcode>?) {

    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>) {

    }

    override fun onScanError(errorMessage: String) {

    }

    override fun onCameraPermissionDenied() {

    }
}