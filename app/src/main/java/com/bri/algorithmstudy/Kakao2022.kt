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

    fun k진수에서_소수개수구하기(n: Int, k: Int): Int {
        val num = convert(n, k)
        val nums = num.split(Regex("0+")).filter { it.isNotEmpty() }.map { it.toDouble() }
        return nums.filter { isPrime(it) }.count()
    }

    fun convert(n: Int, k: Int): String {
        if (k == 10) {
            return n.toString()
        }
        val sb = StringBuilder()
        var t = n
        while (t >= k) {
            sb.append(t % k)
            t /= k
        }
        sb.append(t)
        return sb.reverse().toString()
    }

    fun isPrime(n: Double): Boolean {
        if(n == 1.0) return false
        val max = Math.sqrt(n)
        for (i in 2..max.toInt()) {
            if (n % i == 0.0) return false
        }
        return true
    }
}