package com.example.zxingqr.ui.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class ScanContract : ActivityResultContract<Intent, IntentResult?>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): IntentResult? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            IntentIntegrator.parseActivityResult(resultCode, intent)
        } else {
            null
        }
    }
}
