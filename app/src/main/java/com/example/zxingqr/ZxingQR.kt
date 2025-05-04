package com.example.zxingqr

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zxingqr.ui.theme.Roboto
import com.example.zxingqr.ui.utilities.ScanContract
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

@Composable
fun ZxingQR(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val activity = context as? Activity

    var messageContent by remember { mutableStateOf("No scan yet") }
    var messageFormat by remember { mutableStateOf("") }

    // Launcher to handle ZXing scan result
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract()
    ) { result: IntentResult? ->
        if (result != null) {
            if (result.contents != null) {
                messageContent = result.contents
                messageFormat = result.formatName ?: ""
            } else {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = messageContent,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.W500,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = messageFormat,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.W400,
                color = Color.DarkGray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Launch the scanner
                val integrator = IntentIntegrator(activity)
                integrator.setPrompt("Scan a barcode or QR Code")
                integrator.setOrientationLocked(true)
                scanLauncher.launch(integrator.createScanIntent())
            },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A980D)),
            modifier = Modifier
                .width(120.dp)
                .height(50.dp)
        ) {
            Text("Scan", fontSize = 20.sp, color = Color.White)
        }
    }
}
