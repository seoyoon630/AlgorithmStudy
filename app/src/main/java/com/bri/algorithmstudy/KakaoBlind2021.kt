package com.bri.algorithmstudy

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object KakaoBlind2021 {
    // 이진탐색 알고리즘
    fun _순위검색(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size) { 0 }
        val infos = HashMap<String, ArrayList<Int>>()
        info.forEach {
            val key = makeKey(it)
            val score = getScore(it)
            if (!infos.containsKey(key)) infos[key] = ArrayList()
            infos[key]?.add(score)
        }
        // 정렬
        infos.keys.forEach { infos[it]?.sort() }

        query.forEachIndexed { index, s ->
            val key = makeKey(s)
            val score = getScore(s)
            val regex = key.replace("0", "[0-9]").toRegex()
            answer[index] = infos.keys.filter { regex.matches(it) }
                .map { infos[it] }
                .sumOf {
                    val size = it?.size ?: 0
                    val first = binarySearch(it, score).takeIf { it > -1 } ?: size
                    size - first
                }
        }

        return answer
    }

    private fun makeKey(s: String): String {
        val split = s.split(" ").filter { it != "and" }
        val lang = listOf("-", "cpp", "java", "python").indexOf(split[0])
        val group = listOf("-", "backend", "frontend").indexOf(split[1])
        val career = listOf("-", "junior", "senior").indexOf(split[2])
        val food = listOf("-", "chicken", "pizza").indexOf(split[3])
        return "$lang$group$career$food"
    }

    private fun getScore(s: String): Int {
        return s.split(" ").filter { it != "and" }[4].toInt()
    }

    private fun binarySearch(arr: ArrayList<Int>?, target: Int): Int {
        if (arr == null) return -1
        var start = 0
        var end = arr.lastIndex
        var mid : Int
        var answer = -1
        while (start <= end) {
            mid = (start + end) / 2
            when {
//                arr[mid] == target -> return mid
                arr[mid] >= target -> {
                    answer = mid
                    end = mid - 1
                }
                arr[mid] < target -> start = mid + 1
            }
        }
        return answer
    }

    //======================================================================

    // 플로이드-워셜 알고리즘
    @RequiresApi(Build.VERSION_CODES.N)
    fun _택시합승요금(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val cost = Array(n + 1) { y ->
            IntArray(n + 1) { x ->
                if (x == y) 0 else 200 * 100000
            }
        }

        fares.forEach {
            val n1 = it[0]
            val n2 = it[1]
            val fare = it[2]
            cost[n1][n2] = fare
            cost[n2][n1] = fare
        }

        // Floyd-Warshall
        for (i in 1..n) {
            for (j in 1..n) {
                if (i == j) continue
                for (k in 1..n) {
                    if (i == k) continue
                    if (j == k) continue
                    val distance = cost[j][i] + cost[i][k]
                    if (cost[j][k] > distance) {
                        cost[j][k] = distance
                    }
                }
            }
        }

        return (1..n).map { cost[s][it] + cost[it][a] + cost[it][b] }
            .minOrNull() ?: -1
    }

    //======================================================================

    @RequiresApi(Build.VERSION_CODES.N)
    fun _광고삽입(play_time: String, adv_time: String, logs: Array<String>): String {
        val totalTime = convertToInt(play_time) + 1
        val advTime = convertToInt(adv_time)
        val times = LongArray(totalTime + 1) { 0 }
        logs.forEach {
            val start = convertToInt(it.split("-")[0]) + 1
            val end = convertToInt(it.split("-")[1]) + 1
            times[start]++
            times[end]--
        }
        for (i in 1..times.lastIndex) {
            times[i] += times[i - 1]
        }
        for (i in 1..times.lastIndex) {
            times[i] += times[i - 1]
        }
        var max : Long = 0
        var result = 0
        for (i in 0 until totalTime - advTime) {
            val current = times[i + advTime] - times[i]
            if (max < current) {
                max = current
                result = i
            }
        }

        return convertToString(result)
    }

    private fun convertToInt(s: String): Int {
        return s.split(":")
            .mapIndexed { index, time ->
                Math.pow(60.toDouble(), (2 - index).toDouble()).toInt() * time.toInt()
            }.sum()
    }

    private fun convertToString(t: Int): String {
        val hour = t / (60 * 60)
        val min = t % (60 * 60) / 60
        val second = t % (60)
        return "${formatString(hour)}:${formatString(min)}:${formatString(second)}"
    }

    private fun formatString(s: Int): String {
        return String.format(Locale.getDefault(), "%02d", s)
    }

    //======================================================================

}