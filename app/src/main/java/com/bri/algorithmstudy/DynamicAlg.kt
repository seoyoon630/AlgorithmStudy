@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

/**
 * 다이나믹 프로그래밍(동적 계획법) = 메모리를 적절히 사용하여 수행 시간 효율성을 비약적으로 향상시키는 방법
 * 이미 계산된 결과(작은 문제)는 별도의 메모리 영역에 저장하여 다시 계산하지 않는다.
 * 일반적으로 탑다운 / 보텀업 두 가지 방식으로 구성
 * 탑다운 = 메모이제이션(캐싱) -> 한 번 계산한 결과를 메모리 공간에 메모하는 방법
 * 최적 부분 구조 및 중복되는 부분 문제 라는 조건을 만족 시 사용할 수 있다.
 * -> 큰 문제를 작은 문제로 나눌 수 있으며, 작은 문제의 답을 모아 큰 문제를 해결할 수 있고,
 * 동일한 작은 문제를 반복적으로 해결해야 함
 * ex) 피보나치 수열
 */
object DynamicAlg {
    fun _피보나치수열(n: Int = 99) {
        val dp = DoubleArray(n + 1) { if (it <= 2) 1.0 else -1.0 }
        _피보나치수열재귀(n, dp)
        println(dp.joinToString())
    }

    private fun _피보나치수열재귀(n: Int, array: DoubleArray): Double {
        if (array[n] != -1.0) return array[n]
        array[n] = _피보나치수열재귀(n - 2, array) + _피보나치수열재귀(n - 1, array)
        return array[n]
    }

    fun _피보나치수열반복(n: Int = 99) {
        val dp = DoubleArray(n + 1) { if (it <= 2) 1.0 else -1.0 }
        for (i in 3..n) {
            dp[i] = dp[i - 2] + dp[i - 1]
        }
        println(dp.joinToString())
    }

}