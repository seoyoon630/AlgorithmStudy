package com.bri.algorithmstudy

object BinarySearchAlg {
    fun init(){}

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
}