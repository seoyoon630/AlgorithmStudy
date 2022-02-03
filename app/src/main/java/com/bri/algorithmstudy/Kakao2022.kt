package com.bri.algorithmstudy

object Kakao2022 {
    fun 신고결과받기(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val reportMap = HashMap<String, HashSet<String>>()
        val countMap = HashMap<String, Int>()
        id_list.forEach { id ->
            reportMap[id] = hashSetOf()
            countMap[id] = 0
        }
        report.forEach {
            val splits = it.split(" ")
            val id1 = splits[0]
            val id2 = splits[1]
            if (reportMap[id1]?.contains(id2) == false) {
                reportMap[id1]?.add(id2)
                countMap[id2] = (countMap[id2] ?: 0) + 1
            }
        }
        id_list.forEach { id ->
            val list = reportMap[id]?.filter { countMap[it] ?: 0 >= k }
            println("$id : ${list?.joinToString()}")
        }
        return id_list.map { id ->
            reportMap[id]?.filter { countMap[it] ?: 0 >= k }?.count() ?: 0
        }.toIntArray()
    }

    fun 신고결과받기2(id_list: Array<String>, report: Array<String>, k: Int): IntArray =
        report.map { it.split(" ") }
            .groupBy { it[1] }
            .asSequence()
            .map { it.value.distinct() }
            .filter { it.size >= k }
            .flatten()
            .map { it[0] }
            .groupingBy { it }
            .eachCount()
            .run { id_list.map { getOrDefault(it, 0) } }.toIntArray()
}