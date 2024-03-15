package com.example.sensortempambiente

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class TempAmbientSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE,
    sensorType = Sensor.TYPE_AMBIENT_TEMPERATURE
)

//AQUI SE PUEDEN AGREGAR MÁS SOLO COPIA ESTO