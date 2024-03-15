package com.example.sensortempambiente

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sensortempambiente.ui.theme.SensorTempAmbienteTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.*
import java.text.SimpleDateFormat
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var tema = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorTempAmbienteTheme {
                val viewModel = viewModel<MainViewModel>()
                val isHot = viewModel.isHot
                val temp = viewModel.temperature
                val imageRes = if (isHot) R.drawable.fan else R.drawable.fire
                val imageBack = if (isHot) R.drawable.beach else R.drawable.cabin

                if(tema!=isHot){
                    registerTemperature(temp)
                    tema = isHot
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(imageBack),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = null
                        )
                        Text(
                            text = if(isHot) {
                                "HACE CALOR! 🍻"
                            } else {
                                "HACE FRÍO! ☕"
                            },
                            color = Color.White,
                            modifier = Modifier.background(Color.Black)
                        )
                        Text(
                            text = "$temp",
                            color = Color.White,
                            modifier = Modifier.background(Color.Black)
                        )
                        Button(
                            onClick = {
                                showTemperatureLog()
                            }
                        ) {
                            Text("Mostrar último registro")
                        }
                    }
                }

            }
        }
    }



    public fun registerTemperature(temperature: Float) {
        val filename = "temperature_log.txt"

        val calendario = Calendar.getInstance()
        val año = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH) + 1
        val día = calendario.get(Calendar.DAY_OF_MONTH)
        val hora = calendario.get(Calendar.HOUR_OF_DAY)
        val minuto = calendario.get(Calendar.MINUTE)
        val segundo = calendario.get(Calendar.SECOND)

        val fechaHoraFormateada = "$día/$mes/$año $hora:$minuto:$segundo"
        val data:String = "$temperature - $fechaHoraFormateada"

        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }
        Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
    }

    private fun showTemperatureLog() {
        val filename = "temperature_log.txt"
        try {
            val fileInputStream: FileInputStream = openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String? = null
            while ({ line = bufferedReader.readLine(); line }() != null) {
                println(line)
            }
            bufferedReader.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}