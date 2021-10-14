@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

import java.util.*

/**
 * 다이나믹 프로그래밍(동적 계획법) = 메모리를 적절히 사용하여 수행 시간 효율성을 비약적으로 향상시키는 방법
 * 이미 계산된 결과(작은 문제)는 별도의 메모리 영역에 저장하여 다시 계산하지 않는다.
 * 일반적으로 탑다운 / 보텀업 두 가지 방식으로 구성
 * 탑다운 = 메모이제이션(캐싱) -> 한 번 계산한 결과를 메모리 공간에 메모하는 방법
 * 최적 부분 구조 및 중복되는 부분 문제 라는 조건을 만족 시 사용할 수 있다.
 * -> 큰 문제를 작은 문제로 나눌 수 있으며, 작은 문제의 답을 모아 큰 문제를 해결할 수 있고,
 * 동일한 작은 문제를 반복적으로 해결해야 함
 * ex) 피보나치 수열
 *
 * 다이나믹 프로그래밍 VS 분할 정복
 * 공통점 : 둘 다 최적 부분 구조일 때 사용 가능
 * 차이점 : 부분 문제의 중복
 */
object DynamicAlg {
    fun _피보나치수열(n: Int = 99) {
        val dp = LongArray(n + 1) { if (it <= 2) 1 else -1 }
        _피보나치수열재귀(n, dp)
        println(dp.joinToString())
    }

    private fun _피보나치수열재귀(n: Int, array: LongArray): Long {
        if (array[n] != (-1).toLong()) return array[n]
        array[n] = _피보나치수열재귀(n - 2, array) + _피보나치수열재귀(n - 1, array)
        return array[n]
    }

    fun _피보나치수열반복(n: Int = 99) {
        val dp = LongArray(n + 1) { if (it <= 2) 1 else -1 }
        for (i in 3..n) {
            dp[i] = dp[i - 2] + dp[i - 1]
        }
        println(dp.joinToString())
    }

    /**
     * 최소 횟수를 구해야 하므로 BFS로 풀이
     */
    fun _1로만들기(x: Int = 26): Int {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(Pair(x, 0))
        while (queue.isNotEmpty()) {
            val node = queue.poll() ?: break
//            println("${node.second} : ${node.first}")
            val n = node.first
            if (n == 1) return node.second
            if (n % 5 == 0) queue.offer(Pair(n / 5, node.second + 1))
            if (n % 3 == 0) queue.offer(Pair(n / 3, node.second + 1))
            if (n % 2 == 0) queue.offer(Pair(n / 2, node.second + 1))
            queue.offer(Pair(n - 1, node.second + 1))

        }
        return -1
    }

    /**
     * dynamic Programming으로 풀이
     * 시간복잡도, 공간복잡도 모두 BFS보다 유리
     */
    fun _1로만들기2(x: Int = 26): Int {
        val dp = IntArray(x + 1) { if (it == 1) 0 else Int.MAX_VALUE }
        for (i in 2..x) {
            dp[i] = dp[i - 1] + 1
            if (i % 2 == 0) dp[i] = (dp[i / 2] + 1).coerceAtMost(dp[i])
            if (i % 3 == 0) dp[i] = (dp[i / 3] + 1).coerceAtMost(dp[i])
            if (i % 5 == 0) dp[i] = (dp[i / 5] + 1).coerceAtMost(dp[i])
        }
//        println(dp.joinToString())
        return dp[x]
    }

    fun _개미전사(array: IntArray = intArrayOf(1, 3, 1, 5)): Int {
        val dp = IntArray(array.size) { -1 }
        dp[0] = array[0]
        dp[1] = Math.max(array[0], array[1])

        for (i in 2..array.lastIndex) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + array[i])
        }
        println(dp.joinToString())
        return Math.max(dp[array.lastIndex - 1], dp[array.lastIndex])
    }
}