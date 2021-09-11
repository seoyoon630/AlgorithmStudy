@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

/**
 * 구현 알고리즘
 * = 머릿속에 있는 알고리즘을 소스코드로 바꾸는 과정
 * = 풀이를 떠올리는 것은 쉽지만 소스코드로 옮기기 어려운 문제
 */
object ImplementationAlg {
    /**
     * N X N 크기의 정사각형 공간에서, 상하좌우로 이동할 수 있다.
     * 가장 왼쪽 위 좌표는 (1,1)이며 시작좌표도 항상 (1,1)이다.
     * 계획서에 따라 도착할 위치 좌표를 구하세요.
     * L : 왼쪽, R : 오른쪽, U : 위, D : 아래
     * 참고) 공간을 벗어나는 움직임은 무시
     */
    fun _상하좌우(n: Int = 5, s: String = "R R R U D D") {
        val dx = listOf(-1, 1, 0, 0)
        val dy = listOf(0, 0, -1, 1)
        val direction = listOf("L", "R", "U", "D")

        var x = 1
        var y = 1
        println("====================================")
        s.split(" ").map { direction.indexOf(it) }.forEach { index ->
            x = (x + dx[index]).takeIf { it in (1..n) } ?: x
            y = (y + dy[index]).takeIf { it in (1..n) } ?: y
            println("$y $x")
        }
    }

    /**
     * 정수 N이 입력되면 0시 0분 0초부터 N시 59분 59초까지의 모든 시각 중에서
     * 3이 하나라도 포함되는 모든 경우의 수를 구하세요.
     */
    fun _시각(n: Int = 5): Int {
        val timeWith3 = (0 until 60).count { it % 10 == 3 || it / 10 == 3 }
        val timeWithOut3 = 60 - timeWith3
        val hourWith3 = (n - 3 + 10) / 10
        val hourWithOut3 = n + 1 - hourWith3

        return (hourWith3 * 60 * 60 +
                hourWithOut3 * timeWith3 * 60 +
                hourWithOut3 * timeWithOut3 * timeWith3)
    }

    fun _시각완전탐색(n: Int = 5): Int {
        var result = 0
        for (i in 0..n) {
            for (j in 0 until 60) {
                for (k in 0 until 60) {
                    if ("$i$j$k".contains("3"))
                        result++
                }
            }
        }
        return result
    }

    /**
     * 8 X 8 좌표 평면이 주어졌을 때, 나이트가 이동할 수 있는 경우의 수를 출력하세요.
     * 이동 방식은 아래와 같습니다.
     * 수평으로 2칸 이동 후 수직 1칸 이동
     * 수직으로 2칸 이동 후 수평 1칸 이동
     */
    fun _왕실의나이트(p: String = "a1"): Int {
        var result = 0
        val max = 8
        val x = p[0] - 'a'
        val y = p[1].digitToInt() - 1
        val distance = listOf(2, 1)
        for (d1 in listOf(-1, 1)) {
            for (d2 in listOf(-1, 1)) {
                val distance1 = d1 * distance[0]
                val distance2 = d2 * distance[1]
                if ((x + distance1) in 0 until max && (y + distance2) in 0 until max) result++
                if ((y + distance1) in 0 until max && (x + distance2) in 0 until max) result++
            }
        }
        return result
    }

    fun _문자열재정렬(s: String = "K1KA5CB7"): String {
        val alphabets = s.toCharArray().filter { c -> c in 'A'..'Z' }.sorted()
        val sum = s.filter { c -> c in '0'..'9' }.map { it.digitToInt() }.sum()
        return "${alphabets.joinToString("")}$sum"
    }
}