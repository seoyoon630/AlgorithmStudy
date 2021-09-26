package com.bri.algorithmstudy

import java.text.SimpleDateFormat
import java.util.*

fun assertThat(function: () -> Any, answer: Any, tag: String) {
    drawLine()
    val start = System.currentTimeMillis()
    val result = function()
    if (result is Array<*> && answer is Array<*>) {
        val a = result.map { (it as? IntArray)?.joinToString() }.joinToString()
        val b = answer.map { (it as? IntArray)?.joinToString() }.joinToString()
        println("$tag 예상값 = $b")
        println("$tag 결과값 = $a")
        assert(a == b)
    } else if (result is IntArray && answer is IntArray){
        val a = result.joinToString()
        val b = answer.joinToString()
        println("$tag 예상값 = $b")
        println("$tag 결과값 = $a")
        assert(a == b)
    }else {
        println("$tag 예상값 = $answer")
        println("$tag 결과값 = $result")
        assert(result == answer)
    }
    val end = System.currentTimeMillis()
    println("시간 = ${SimpleDateFormat("mm:ss.SSS", Locale.getDefault()).format(end - start)}")
    drawLine()
}

fun drawLine(){
    println("===============================================================")
}