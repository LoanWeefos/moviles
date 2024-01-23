class Ciclos {
    fun validar(cadena: String): Boolean {
        if (cadena.isNotEmpty() && (cadena[0].isLetter() || cadena[0] == '_')) {
            return cadena.all { it.isLetterOrDigit() || it == '_' }
        }
        return false
    }
}

fun main(args: Array<String>) {
    val ciclos  = Ciclos()
    println(ciclos.validar("wikit"))   // true
    println(ciclos.validar("_wikit"))  // true
    println(ciclos.validar("_02"))      // true
    println(ciclos.validar(""))         // false
    println(ciclos.validar("023"))      // false
    println(ciclos.validar("ea."))      // false
}