package com.example.tarea2

/**
 * Práctica: Cadena Fina
 * Nombre: Luis Esteban Durán Quintanar
 * 00000233267
 */

fun String.esFina(): Boolean{
    val contieneSubCadenas = !Regex("bu|ba|be").containsMatchIn(this)
    val contieneTresVocales = Regex("[aeiouAEIOU]").findAll(this).count() >= 3
    val contieneLetraDoble = Regex("(\\w)\\1").containsMatchIn(this)

    val condicionesCumplidas = listOf(contieneSubCadenas, contieneTresVocales, contieneLetraDoble)
    return condicionesCumplidas.count { it } >= 2
}

fun main(args: Array<String>){
    println("bac".esFina()) //false
    println("aza".esFina()) //false
    println("abaca".esFina()) //false
    println("baaa".esFina()) //true
    println("aaab".esFina()) //true
}