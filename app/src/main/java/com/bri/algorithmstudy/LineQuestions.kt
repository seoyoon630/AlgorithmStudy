package com.bri.algorithmstudy

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

object LineQuestions {
    fun _프로그래밍1(record: Array<String>): IntArray {
        val queue: Queue<Int> = LinkedList()
        val stack = Stack<Int>()
        var fifo = 0
        var lifo = 0
        record.forEach {
            val splits = it.split(" ")
            val cost = splits[1].toInt()
            val count = splits[2].toInt()
            when (splits[0]) {
                "P" -> {
                    repeat(count) {
                        queue.offer(cost)
                        stack.push(cost)
                    }
                }
                "S" -> {
                    repeat(count) {
                        fifo += queue.poll() ?: 0
                        lifo += stack.pop()
                    }
                }
            }
        }
        return intArrayOf(fifo, lifo)
    }

    fun _프로그래밍2(arr: IntArray): Int {
        val modulo = Math.pow(10.toDouble(), 9.toDouble()) + 7
        var answer = 0.toDouble()
        val start = arr[0]
        var prev = arr[1]
        var isPlus = start < prev
        var plus = if (isPlus) 1 else 0
        var minus = 0
        for (i in 2..arr.lastIndex) {
            val current = arr[i]
            val diff = current - prev
            when {
                // 상승
                diff > 0 -> {
                    if (!isPlus) {
                        answer += (plus * minus.toDouble()) % modulo
                        minus = 0
                        plus = 0
                        isPlus = true
                    }
                    plus++
                }
                // 동일
                diff == 0 -> {
                    if (!isPlus) {
                        answer += (plus * minus.toDouble()) % modulo
                    }
                    minus = 0
                    plus = 0
                }
                // 하락
                diff < 0 -> {
                    isPlus = false
                    minus++
                }
            }
            prev = current
        }
        if (!isPlus) {
            answer += (plus * minus.toDouble()) % modulo
        }
        return (answer % modulo).toInt()
    }

    fun _프로그래밍3(n: Int, k: Int): IntArray {
        var result = intArrayOf()
        val dy = listOf(-1, 0, 1, 0)
        val dx = listOf(0, -1, 0, 1)
        val board = Array(n) { IntArray(n) { n * n } }
        // 초기화
        board[0][0] = 0
        // y, x, cost
        val queue: Queue<IntArray> = LinkedList()
        queue.offer(intArrayOf(0, 0, 0))

        repeat(k - 1) {
            // 거리구하기 (BFS)
            while (queue.isNotEmpty()) {
                val c = queue.poll() ?: break
                repeat(4) {
                    val y = c[0] + dy[it]
                    val x = c[1] + dx[it]
                    val space = c[2] + 1
                    if (y in board.indices && x in board.indices) {
                        if (space < board[y][x]) {
                            board[y][x] = space
                            // 거리 갱신
                            val result = intArrayOf(y, x, board[y][x])
                            queue.add(result)
                        }
                    }
                }
            }
            // 좌석 위치 찾기
            // 최대 거리 찾기
            val set = HashSet<Int>()
            board.forEach { row -> set.addAll(row.toList()) }
            val max = set.maxOrNull() ?: 0
            val candidates = ArrayList<IntArray>()
            board.forEachIndexed { y, row ->
                row.forEachIndexed { x, v ->
                    if (v == max) candidates.add(intArrayOf(y, x))
                }
            }
            // 행, 열 조건에 따라 정렬
            val next = candidates.sortedWith(
                object : Comparator<IntArray> {
                    override fun compare(o1: IntArray, o2: IntArray): Int =
                        (o1[1] - o2[1]).takeIf { it != 0 }
                            ?: o1[0] - o2[0]
                }).first()
            val y = next[0]
            val x = next[1]
            board[y][x] = 0
            queue.offer(intArrayOf(y, x, 0))
            if (it == k - 2) result = intArrayOf(y + 1, x + 1)
        }
        return result
    }

    fun _프로그래밍4(rectangles: Array<IntArray>): Array<String> {
        val x = IntArray(1000000) { 0 }
        val y = IntArray(1000000) { 0 }

        // bottom y가 가장 낮은 직사각형 순으로 정렬한다.
        var indices = rectangles.indices.sortedBy { rectangles[it][1] }
        indices.forEach {
            val rectangle = rectangles[it]
            val start = rectangle[0]
            val end = rectangle[2]
            val bottom = rectangle[1]
            val enableBottom = x.slice(start + 1..end).maxOrNull() ?: 0
            val enableDistance = bottom - enableBottom
            rectangle[1] -= enableDistance
            rectangle[3] -= enableDistance
            repeat(end - start) { c ->
                x[start + c + 1] = Math.max(x[start + c + 1], rectangle[3])
            }
        }
        // start x가 가장 낮은 직사각형 순으로 정렬한다.
        indices = rectangles.indices.sortedBy { rectangles[it][0] }
        indices.forEach {
            val rectangle = rectangles[it]
            val start = rectangle[0]
            val bottom = rectangle[1]
            val top = rectangle[3]
            val enableStart = y.slice(bottom + 1..top).maxOrNull() ?: 0
            val enableDistance = start - enableStart
            rectangle[0] -= enableDistance
            rectangle[2] -= enableDistance
            repeat(top - bottom) { c ->
                y[bottom + c + 1] = Math.max(y[bottom + c + 1], rectangle[2])
            }
        }
        return rectangles.map { it.joinToString(" ") }.toTypedArray()
    }

    fun test(){}
}