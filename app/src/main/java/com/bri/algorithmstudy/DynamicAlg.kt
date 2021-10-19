@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

import java.util.*
import kotlin.collections.HashSet

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
        return dp[array.lastIndex]
    }

    fun _바닥공사(n: Int = 3): Int {
        val dp = IntArray(n + 1) { -1 }
        dp[1] = 1
        dp[2] = 3
        for (i in 3..n) {
            dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 796796
//            println("$i = ${dp[i]}")
        }
        return dp[n]
    }

    fun _효율적인화폐구성(coins: IntArray = intArrayOf(2, 3), n: Int = 15): Int {
        val dp = IntArray(n + 1) { if (coins.contains(it)) 1 else -1 }
        for (i in 1..n) {
            // 이전 금액이 존재하는 경우
            val availableCoins = coins.filter { it < i && dp[i - it] >= 0 }
//            println("$i -> ${availableCoins.joinToString()}")
            if (dp[i] == -1 && availableCoins.isNotEmpty())
                dp[i] = availableCoins.minOf { coin -> dp[i - coin] + 1 }
        }
//        println(dp.joinToString())
        return dp[n]
    }

    fun _금광(
        testCases: Array<Array<IntArray>> = arrayOf(
            arrayOf(
                intArrayOf(1, 3, 3, 2),
                intArrayOf(2, 1, 4, 1),
                intArrayOf(0, 6, 4, 7)
            ), arrayOf(
                intArrayOf(1, 3, 1, 5),
                intArrayOf(2, 2, 4, 1),
                intArrayOf(5, 0, 2, 3),
                intArrayOf(0, 6, 1, 2)
            )
        )
    ): IntArray {
        val answer = IntArray(testCases.size) { 0 }
        testCases.forEachIndexed { index, array ->
            val y = array.size
            val x = array[0].size
            val result = Array(y) { IntArray(x) { 0 } }
            for (i in 0 until x) {
                for (j in 0 until y) {
                    if (i == 0) {
                        result[j][i] = array[j][i]
                    } else {
                        val candidates = HashSet<Int>()
                        if (j > 0) candidates.add(result[j - 1][i - 1] + array[j][i])
                        candidates.add(result[j][i - 1] + array[j][i])
                        if (j < y - 1) candidates.add(result[j + 1][i - 1] + array[j][i])
                        result[j][i] = candidates.maxOrNull() ?: 0
                    }
                }
            }
            answer[index] = (0 until y).map { result[it][x - 1] }.maxOrNull() ?: 0
        }
        return answer
    }

    fun _정수삼각형(
        n: Array<IntArray> = arrayOf(
            intArrayOf(7),
            intArrayOf(3, 8),
            intArrayOf(8, 1, 0),
            intArrayOf(2, 7, 4, 4),
            intArrayOf(4, 5, 2, 6, 5)
        )
    ): Int {
        // 현재까지의 값을 더할 곳
        val size = n.size
        val dp = Array(size) { IntArray(size) { 0 } }
        dp[0][0] = n[0][0]
        for (y in 1 until size) {
            dp[y][0] = dp[y - 1][0] + n[y][0]
            for (x in 1..y) {
                dp[y][x] = Math.max(dp[y - 1][x], dp[y - 1][x - 1]) + n[y][x]
            }
        }
        println(dp.joinToString("\n") { it.joinToString("\t") })
        return dp[size - 1].maxOrNull() ?: 0
    }

    /**
     * 시작시간, 종료시간, 금액을 가진 배열로 변경한다.
     * 종료시간을 기준을 (시작시간, 금액)의 HashSet을 생성한다.
     * dp[n] = 정확히 n일에 끝나는 일 중 가장 큰 금액 or n-1일까지 끝나는 일 중 가장 큰 금액
     */
    fun _퇴사(
        n: Int = 7,
        array: Array<IntArray> = arrayOf(
            intArrayOf(3, 10),
            intArrayOf(5, 20),
            intArrayOf(1, 10),
            intArrayOf(1, 20),
            intArrayOf(2, 15),
            intArrayOf(4, 40),
            intArrayOf(2, 200)
        )
    ): Int {
        val sets = Array(n + 1) { hashSetOf<IntArray>() }
        array.forEachIndexed { index, _ ->
            val end = index + array[index][0]
            if (end <= n) {
                sets[end].add(intArrayOf(index, array[index][1]))
            }
        }
        sets.forEachIndexed { index, it ->
            println("$index = ${it.joinToString(" | ") { it.joinToString() }}")
        }
        val dp = IntArray(n + 1) { 0 }
        for (i in 1 until dp.size) {
            // n일에 끝나는 일 중 가장 큰 금액
            val current = sets[i].map { it[1] + dp[it[0]] }.maxOrNull() ?: 0
            // n일까지 끝나는 일과 n-1일까지 끝나는 일 중 최댓값 구하여 넣음
            dp[i] = Math.max(current, dp[i - 1])
        }
        println(dp.joinToString())
        return dp[n]
    }

    /**
     * 풀이 참고
     * 가장 긴 증가하는 부분 수열(LIS) 알고리즘 사용
     *
     */
    fun _병사배치하기(array: IntArray = intArrayOf(15, 11, 4, 8, 5, 2, 4)): Int {
        array.reverse()
        val n = array.size
        val dp = IntArray(n) { 1 }
        println(array.joinToString())
        for (i in 1 until n) {
            for (j in 0 until i) {
                if (array[j] < array[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
            println(dp.joinToString())
        }
        return n - (dp.maxOrNull() ?: 0)
    }

    /**
     * 2,3,5 만을 약수로 가진 수
     */
    fun _못생긴수(n: Int = 10): Int {
        val dp = IntArray(Math.max(n, 5)) { 0 }
        dp[0] = 1
        dp[1] = 2
        dp[2] = 3
        dp[3] = 4
        dp[4] = 5
        var count = 5
        var target = 6
        while (count < n) {
            if (target % 2 == 0 && dp.contains(target / 2)) {
                dp[count++] = target
            } else if (target % 3 == 0 && dp.contains(target / 3)) {
                dp[count++] = target
            } else if (target % 5 == 0 && dp.contains(target / 5)) {
                dp[count++] = target
            }
            target++
        }
        println(dp.joinToString())
        return dp[n - 1]
    }

    /**
     * 삽입, 삭제, 변경 최소로 하는 횟수 구하기 -> Levenshtein Algorithm
     * 2차원 배열 생성
     * char 끼리 비교해서 같으면, answer[i][j] = answer[i-1][j-1]
     * 같지 않으면 answer[i-1][j]+deleteCost, answer[i][j-1]+addCost, answer[i-1][j-1]+replaceCost
     * 중 제일 작은 값으로 설정
     */
    fun _편집거리(array: Array<String> = arrayOf("cat", "cut")): Int {
        // 2차원 배열 생성
        val length = array[0].length
        val width = array[1].length
        val answer = Array(length + 1) { IntArray(width + 1) { 0 } }
        for (y in 0..length) {
            for (x in 0..width) {
                if (y == 0) answer[y][x] = x
                else if (x == 0) answer[y][x] = y
                else if (array[0][y - 1] == array[1][x - 1]) {
                    answer[y][x] = answer[y - 1][x - 1]
                } else {
                    answer[y][x] = (listOf(
                        answer[y - 1][x],
                        answer[y][x - 1],
                        answer[y - 1][x - 1]
                    ).minOrNull() ?: break) + 1
                }
            }
        }
        println(answer.joinToString("\n") { it.joinToString("\t") })
        return answer[length][width]
    }
}