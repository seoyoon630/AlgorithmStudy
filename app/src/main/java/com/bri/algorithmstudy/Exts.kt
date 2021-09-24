package com.bri.algorithmstudy

import java.text.SimpleDateFormat
import java.util.*

fun assertThat(function: () -> Any, answer: Any, tag: String) {
    drawLine()
    val start = System.currentTimeMillis()
    println("$tag 예상값 = $answer")
    val result = function()
    println("$tag 결과값 = $result")
    if (result is Array<*> && answer is Array<*>) {
        val a = result.map { (it as? IntArray)?.joinToString() }.joinToString()
        val b = answer.map { (it as? IntArray)?.joinToString() }.joinToString()
        assert(a == b)
    } else {
        assert(result == answer)
    }
    val end = System.currentTimeMillis()
    println("시간 = ${SimpleDateFormat("mm:ss.SSS", Locale.getDefault()).format(end - start)}")
    drawLine()
}

fun drawLine(){
    println("===============================================================")
}