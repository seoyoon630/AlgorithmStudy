@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")
package com.bri.algorithmstudy

object BinarySearchAlg {
    fun _부품찾기(
        arr: IntArray = intArrayOf(8, 3, 7, 9, 2),
        targets: IntArray = intArrayOf(5, 7, 9)
    ): String {
        arr.sort()
        val sb = StringBuilder("")
        for (target in targets) {
            val result = binarySearch(arr, target)?.let { "yes " } ?: "no "
            sb.append(result)
        }
        return sb.toString().trim()
    }

    private fun binarySearch(arr: IntArray, target: Int): Int? {
        var start = 0
        var end = arr.lastIndex
        while (start <= end) {
            val mid = (start + end) / 2
            when {
                arr[mid] == target -> return mid
                arr[mid] > target -> end = mid - 1
                arr[mid] < target -> start = mid + 1
            }
        }
        return null
    }

    fun _부품찾기2(
        arr: IntArray = intArrayOf(8, 3, 7, 9, 2),
        targets: IntArray = intArrayOf(5, 7, 9)
    ): String {
        val set = hashSetOf<Int>()
        set.addAll(arr.toList())
        return targets.map { set.contains(it) }
            .joinToString(" ") { if (it) "yes" else "no" }
    }

    fun _떡볶이떡만들기(m: Int = 6, arr: IntArray = intArrayOf(19, 15, 10, 17)): Int {
        // 1~arr의 최대 높이 이진탐색 시작
        // 자른 길이의 합을 구해 비교한다.
        val max = arr.maxOrNull() ?: m
        var start = 0
        var end = max
        var result = start
        while (start <= end) {
            val mid = (start + end) / 2
            val sum = arr.sumOf { (it - mid).coerceAtLeast(0) }
            println("mid = $mid / sum = $sum")
            when {
                sum == m -> return mid
                sum < m -> end = mid - 1
                sum > m -> {
                    result = mid
                    start = mid + 1
                }
            }
        }
        return result
    }
}