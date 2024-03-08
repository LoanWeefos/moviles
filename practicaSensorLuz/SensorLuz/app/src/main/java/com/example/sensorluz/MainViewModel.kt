package com.example.sensorluz;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel @Inject constructor(
    private val lightSensor: MeasurableSensor
) : ViewModel() {

    var isDark by mutableStateOf(false)
        private set

    var luz by mutableFloatStateOf(0f)
        private set

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            luz = lux
            isDark = lux < 60f
        }
    }
}
