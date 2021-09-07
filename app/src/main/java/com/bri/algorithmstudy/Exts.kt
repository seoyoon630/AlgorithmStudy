package com.bri.algorithmstudy

import java.text.SimpleDateFormat
import java.util.*

fun assertThat(function: () -> Any, answer: Any, tag: String) {
    println("===============================================================")
    val start = System.currentTimeMillis()
    println("$tag 예상값 = $answer")
    val result = function()
    println("$tag 결과값 = $result")
    assert(result == answer)
    val end = System.currentTimeMillis()
    println("시간 = ${SimpleDateFormat("mm:ss.SSS", Locale.getDefault()).format(end - start)}")
    println("===============================================================")
}