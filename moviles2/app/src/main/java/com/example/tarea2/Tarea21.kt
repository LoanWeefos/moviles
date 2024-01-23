package com.example.tarea2

fun List<Int>.Sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

fun main(args: Array<String>){
    val sum = listOf(1, 2, 3).Sum()
    println(sum)
}