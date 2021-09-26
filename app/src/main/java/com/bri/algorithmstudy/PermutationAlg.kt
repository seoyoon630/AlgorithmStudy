package com.bri.algorithmstudy

object PermutationAlg {
    fun permutation(num: Int) {
        permutation(IntArray(num) { it })
    }

    fun permutation(nums: IntArray): List<IntArray> {
        val result = _permutation(nums)
        return result.distinctBy { it.joinToString() }
    }

    private fun _permutation(
        nums: IntArray,
        temp: IntArray = intArrayOf(),
        numsList: List<Int> = nums.toList()
    ): List<IntArray> = when (numsList.size) {
        1 -> listOf(temp + numsList)
        else -> numsList.flatMap { num -> _permutation(nums, temp + num, numsList - num) }
    }
}