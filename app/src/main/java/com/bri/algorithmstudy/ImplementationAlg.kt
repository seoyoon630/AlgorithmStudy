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
}