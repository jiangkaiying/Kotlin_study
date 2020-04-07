package com.yuntianpay.prepaidcard


fun onlyIf(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) block()
}

fun main(): Unit {



    var runnable = Runnable {
        println("Runnable::run")
    }
    val function: () -> Unit
    function = runnable::run

    onlyIf(true, function)

}

fun main(args: Array<String>) {

    println("Kotlin".lastChar11())
    strings.forEach(::printString)

    var runnable = Runnable {
        println("Runnable::run")
    }
    val function: () -> Unit


    function = runnable::run

    onlyIf(true, function)
}



var strings = setOf<String>("1", "2", "3")

/**
 * 扩展别人类的函数和属性
 */
fun <T> Array<out T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}
fun String.lastChar11(): Char = get(this.length - 1)

fun printString(a:Any){

    println(a.toString())
}


