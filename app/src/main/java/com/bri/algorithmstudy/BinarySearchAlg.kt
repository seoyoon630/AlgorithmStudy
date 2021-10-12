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

    fun _정렬된배열에서특정수의개수구하기(x: Int = 2, arr: IntArray = intArrayOf(1, 1, 2, 2, 2, 2, 3)): Int {
        binarySearch(arr, x)?.let {
            var left = it
            var right = it
            var start = 0
            var end = it - 1
            // 시작 위치 구하기
            while (start <= end) {
                val mid = (start + end) / 2
                when {
                    arr[mid] == x -> end = mid - 1
                    arr[mid] != x -> {
                        // arr[mid] != x && arr[mid+1] == x
                        // x 시작 위치 = mid + 1
                        if (arr[mid + 1] == x) {
                            left = mid + 1
                            break
                        } else {
                            start = mid + 1
                        }
                    }
                }
            }
            start = it + 1
            end = arr.lastIndex
            // 종료 위치 구하기
            while (start <= end) {
                val mid = (start + end) / 2
                when {
                    arr[mid] == x -> start = mid + 1
                    // arr[mid] != x && arr[mid-1] == x
                    // x 종료 위치 = mid - 1
                    arr[mid] != x -> {
                        if (arr[mid - 1] == x) {
                            right = mid - 1
                            break
                        } else {
                            end = mid - 1
                        }
                    }
                }
            }
            return right - left + 1
        } ?: run { return -1 }
    }

    // 시간복잡도 비교용
    fun _정렬된배열에서특정수의개수구하기2(x: Int = 2, arr: IntArray = intArrayOf(1, 1, 2, 2, 2, 2, 3)): Int {
        return arr.count { it == x }.takeIf { it > 0 } ?: -1
    }


}