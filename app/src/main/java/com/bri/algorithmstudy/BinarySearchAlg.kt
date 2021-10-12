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

    // 이진탐색으로 인덱스와 값을 비교하여 찾음
    fun _고정점찾기(arr: IntArray = intArrayOf(-15, -6, 1, 3, 7)): Int {
        var start = 0
        var end = arr.lastIndex
        while (start <= end) {
            val mid = (start + end) / 2
            when {
                mid == arr[mid] -> return mid
                mid < arr[mid] -> end = mid - 1
                mid > arr[mid] -> start = mid + 1
            }
        }
        return -1
    }

    /**
     * 거리를 기준으로 이진탐색 (최소거리~최대거리)
     * mid(거리) 이상일 때 공유기를 설치하고, 공유기 개수가 부족하면 거리를 줄여서 재검색
     * 공유기 개수가 많으면 거리를 늘려서 재검색
     */
    fun _공유기설치(n: Int = 3, arr: IntArray = intArrayOf(1, 2, 8, 4, 9)): Int {
        arr.sort()
        var result = -1
        var start = arr.first()
        var end = arr.last() - arr.first()
        while (start <= end) {
            val mid = (start + end) / 2
            var prev = arr.first()
            var count = 1
            // 이전 공유기 위치와 비교하여 mid 거리 이상일 때 공유기 설치
            for (home in arr) {
                if (home - prev >= mid) {
                    prev = home
                    count++
                }
            }
            // 공유기 개수에 따라 거리를 줄이거나 늘려서 재검색
            when {
                count < n -> end = mid - 1
                else -> {
                    result = mid
                    start = mid + 1
                }
            }
        }
        return result
    }
}