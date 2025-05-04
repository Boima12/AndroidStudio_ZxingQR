package com.example.zxingqr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.zxingqr.ui.theme.ZxingQRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZxingQR()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZxingQRTheme {
        ZxingQR()
    }
}