package com.example.tarea2

fun main(args: Array<String>){
    val s1: String? = null
    val s2: String? = ""
    println(s1.esVaciaONula() == true)
    println(s2.esVaciaONula() == true)

    val s3 = "   "
    println(s3.esVaciaONula() == false)
}

fun String?.esVaciaONula(): Boolean {
    return this.isNullOrEmpty()
}