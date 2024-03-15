package com.example.sensortempambiente;

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.*

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tempAmbientSensor: MeasurableSensor
) : ViewModel() {

    var isHot by mutableStateOf(false)
        private set

    var temperature by mutableFloatStateOf(0f)
        private set

    init {
        tempAmbientSensor.startListening()
        tempAmbientSensor.setOnSensorValuesChangedListener { values ->
            val temp = values[0]
            isHot = temp > 26f
            temperature = temp
        }
    }
}
