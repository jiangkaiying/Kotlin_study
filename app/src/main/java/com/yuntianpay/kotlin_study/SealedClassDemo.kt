package com.yuntianpay.kotlin_study

// 其存在于SealedClassDemo.kt文件中

sealed class SealedExpr {
    data class Person(val name: String, val age: Int) : SealedExpr()
    object Add : SealedExpr()
    companion object Minus : SealedExpr()
}

object NotANumber : SealedExpr()

//其存在TestSealedDemo.kt文件中

fun <T> SealedExpr.Add.add(num1: T, num2: T): Int {
    return 100
}

fun main(args: Array<String>) {
    println(SealedExpr.Add.add(1, 2))

}


fun <T> Array<T>.forEach(action: (T) -> Unit) {

}

fun <T> Array<out T>.forEach1(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}