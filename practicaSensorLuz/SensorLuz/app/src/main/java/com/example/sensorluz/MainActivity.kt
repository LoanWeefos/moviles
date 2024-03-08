package com.example.sensorluz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sensorluz.ui.theme.SensorLuzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorLuzTheme {
                val viewModel = viewModel<MainViewModel>()
                val isDark = viewModel.isDark
                val luz = viewModel.luz
                val imageRes = if (isDark) R.drawable.tyrael else R.drawable.lulu
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (isDark) Color.DarkGray else Color.White
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = null
                        )
                        Text(
                            text = if(isDark) {
                                "Esta oscuro afuera"
                            } else {
                                "Esta brilloso afuera"
                            },
                            color = if(isDark) Color.White else Color.DarkGray
                        )
                        Text(
                            text = "$luz",
                            color = if(isDark) Color.White else Color.DarkGray
                        )
                    }
                }

            }
        }
    }
}