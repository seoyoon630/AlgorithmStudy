@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

import java.util.*
import kotlin.collections.ArrayList

/**
 * 구현 알고리즘
 * = 머릿속에 있는 알고리즘을 소스코드로 바꾸는 과정
 * = 풀이를 떠올리는 것은 쉽지만 소스코드로 옮기기 어려운 문제
 * 완전탐색 = 모든 경우의 수를 주저 없이 다 계산하는 해결 방법
 * 시뮬레이션 = 문제에서 제시한 알고리즘을 한 단계씩 차례대로 직접 수행
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

    /**
     * 방향 [0 : 북쪽, 1 : 동쪽, 2 : 남쪽, 3: 서쪽]
     * 1. 방향에 따라 이동할 x, y를 미리 선언한다.
     * 2. 현재 있는 위치를 1로 바꾼다.
     * 3. 현재 위치에서 갈 수 있는 가장 빠른 곳을 찾아 이동한다.
     * 4. 모든 방향이 갈 수 없을 때까지 반복한다.
     */
    fun _게임개발(
        n: Int = 4,
        m: Int = 4,
        _y: Int = 1,
        _x: Int = 1,
        _d: Int = 0,
        area: Array<IntArray> = arrayOf(
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 0, 1),
            intArrayOf(1, 1, 1, 1)
        )
    ): Int {
        val yList = intArrayOf(-1, 0, 1, 0)
        val xList = intArrayOf(0, -1, 0, 1)

        var y = _y
        var x = _x
        var d = when (_d) {
            1 -> 3
            3 -> 1
            else -> _d
        }
        area[y][x] = 1
        var visitedCount = 1
        println("[$y][$x] 방문")
        var countOfRotate = 0
        while (countOfRotate != 4) {
            d = (d + 1) % 4
            val nextY = y + yList[d]
            val nextX = x + xList[d]
            if (nextY in 0 until m && nextX in 0 until n && area[nextY][nextX] == 0) {
                visitedCount++
                area[nextY][nextX] = 1
                countOfRotate = 0
                y = nextY
                x = nextX
                println("[$y][$x] 방문")
            } else {
                countOfRotate++
            }
        }
        return visitedCount
    }

    fun _럭키스트레이트(score: Int = 123402): String {
        val s = score.toString()
        val first = s.substring(0, s.length / 2).toCharArray().map {
            it.digitToIntOrNull() ?: 0
        }.sum()
        val second = s.substring(s.length / 2, s.length).toCharArray().map {
            it.digitToIntOrNull() ?: 0
        }.sum()
        return if (first == second) "LUCKY" else "READY"
    }

    /**
     * 1~문자열/2까지만 압축 가능
     * 정해진 길이만큼 쪼갠다.
     * 이전 문구, 이전 개수 변수를 선언한다.
     * 쪼갠 목록들을 돌면서 문자열을 압축하고 그 길이를 비교한다.
     */
    fun _문자열압축(s: String = "aabbaccc"): Int {
        return (1..s.length / 2).map { i ->
            val splitsCount = s.length / i
            val splits = Array(splitsCount) { "" }
            for (j in 0 until splitsCount) {
                splits[j] = s.substring(j * i, (j + 1) * i)
            }
            var lastSplit = splits[0]
            var repeatCount = 0
            val sb = StringBuilder()
            splits.forEach {
                if (lastSplit == it) {
                    repeatCount++
                } else {
                    if (repeatCount > 1) sb.append(repeatCount)
                    sb.append(lastSplit)
                    lastSplit = it
                    repeatCount = 1
                }
            }
            if (repeatCount > 1) sb.append(repeatCount)
            sb.append(lastSplit)

            val left = s.length % i
            sb.length + left
        }.minOrNull() ?: s.length
    }

    /**
     * 자물쇠+(열쇠-1)*2 크기의 자물쇠판 생성
     * 자물쇠판 0으로 초기화 후 열쇠-1부터 채우기
     * 열쇠에서 튀어나온부분 최소xy 최대xy구함
     * for i in 0..자물쇠판크기-열쇠크기
     *  최소y 최대y가 열쇠-1..자물쇠판-(열쇠-1) 안에없으면
     *  continue
     *  for j in 0..자물쇠판크기-열쇠크기
     *      최소x 최대x가 열쇠-1..자물쇠판-(열쇠-1) 안에없으면
     *      continue
     *  회전수 = 0
     * while(회전! =4)
     * 자물쇠판에 현재의값을 더한것중에 2가없으면 true
     */
    fun _자물쇠와열쇠(
        key: Array<IntArray> = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 0),
            intArrayOf(0, 1, 1)
        ), lock: Array<IntArray> = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1)
        )
    ): Boolean {
        // 자물쇠 판 준비
        val lockBoardSize = lock.size + (key.size - 1) * 2
        val lockBoard = Array(lockBoardSize) { IntArray(lockBoardSize) { -1 } }

        for (y in lock.indices) {
            for (x in lock.indices) {
                lockBoard[y + key.size - 1][x + key.size - 1] = lock[y][x]
            }
        }
        println(lockBoard.joinToString("\n") { it.joinToString("\t") })

//        // 자물쇠 필요 최소 범위 구하기
//        val minY = lock.indices.first { lock[it].contains(0) }
//        val maxY = lock.indices.last { lock[it].contains(0) }
//        println("minY = $minY, maxY = $maxY")
//        val xLock = Array(lock.size) { IntArray(lock.size) }
//        for (y in lock.indices) {
//            for (x in lock.indices) {
//                xLock[y][x] = lock[x][y]
//            }
//        }
//        val minX = xLock.indices.first { xLock[it].contains(0) }
//        val maxX = xLock.indices.last { xLock[it].contains(0) }
//        println("minX = $minX, maxX = $maxX")

        // 열쇠 이동
        val moveRange = 0 until lockBoardSize - (key.size - 1)
        println("열쇠 이동 범위 = $moveRange")
        for (y in moveRange) {
            for (x in moveRange) {
                println("==================================================================")
                println("$y,$x")
                val after = Array(lockBoardSize) { IntArray(lockBoardSize) { -1 } }
                for (y2 in key.indices) {
                    for (x2 in key.indices) {
                        if (key[y2][x2] == 1) {
                            println("after[${y + y2}][${x + x2}]++ = ${after[y + y2][x + x2]}")
                        }
                        after[y + y2][x + x2] = lockBoard[y + y2][x + x2] + key[y2][x2]
                        if (key[y2][x2] == 1) {
                            println("after = ${after[y + y2][x + x2]}")
                        }
                    }
                }
                println(after.joinToString("\n") { it.joinToString("\t") })
                println("========")
                println(lockBoard.joinToString("\n") { it.joinToString("\t") })
            }
        }
        return false
    }

    /**
     * (n+2 * n+2)보드를 생성
     * 테두리를 모두 -1, 안은 0
     * 사과가 있는 곳은 1
     * 뱀몸통을 stack으로 관리한다
     * stack에 쌓을 때 해당 좌표는 -1로 바뀌고
     * stack에서 뺄 때에는 해당 좌표를 1로 바꾼다
     * 방향 dx, dy 목록을 선언한다.
     *
     * 뱀이 이동하는 대로 한칸씩 이동한다.
     */
    fun _뱀(
        n: Int = 6,
        k: Int = 3,
        apples: Array<String> = arrayOf("3 4", "2 5", "5 3"),
        l: Array<String> = arrayOf("3 D", "15 L", "17 D")
    ): Int {
//        val n = readLine()?.toInt() ?: 0
//        val k = readLine()?.toInt() ?: 0
//        val apples = Array(k) { "" }
//        val m = readLine()?.toInt() ?: 0
//        val l = Array(m) { "" }
        // 기본 보드 형태
        val board = Array(n + 2) { y ->
            IntArray(n + 2) { x -> if (y in listOf(0, n + 1) || x in listOf(0, n + 1)) -1 else 0 }
        }
        // 사과 추가
        apples.forEach {
            val coordinates = it.split(" ").map { it.toInt() }
            board[coordinates[0]][coordinates[1]] = 1
        }

        // 방향 북 = 0, 서 = 1, 남 = 2, 동 = 3
        val dx = listOf(0, -1, 0, 1)
        val dy = listOf(-1, 0, 1, 0)

        // 이동경로 파싱
        val times = Array(l.size) { 0 }
        val changes = Array(l.size) { 0 }
        l.mapIndexed { index, it ->
            val splits = it.split(" ")
            times[index] = splits[0].toInt()
            changes[index] = if (splits[1] == "L") 1 else -1
        }

        // 뱀 몸통 히스토리 큐
        val snake: Queue<Pair<Int, Int>> = LinkedList()
        var y = 1
        var x = 1
        snake.offer(Pair(y, x))

        // 초기화
        board[y][x] = -1
        var direction = 3
        var totalTime = 0
        var index = 0
        println(board.joinToString("\n") { it.joinToString("\t") })

        while (true) {
            while (times.lastIndex < index || totalTime < times[index]) {
                totalTime++
                y += dy[direction]
                x += dx[direction]
                snake.offer(Pair(y, x))
                println("현재시간 : $totalTime [$y,$x]")
                when (board[y][x]) {
                    -1 -> {
                        println("========================================")
                        println("충돌!!!!!!")
                        val tail = snake.poll()
                        tail?.let { board[it.first][it.second] = 0 }
                        board[y][x] = -2
                        println(board.joinToString("\n") { it.joinToString("\t") })
                        return totalTime
                    }
                    0 -> {
                        val tail = snake.poll()
                        tail?.let { board[it.first][it.second] = 0 }
                    }
                }
                board[y][x] = -1
                println("========================================")
                println(board.joinToString("\n") { it.joinToString("\t") })
            }
            direction += changes[index++]
        }
    }

    /**
     * 기둥이 설치될 수 있는 조건
     * y == 0 || [y-1][x] == 기둥존재 || [y][x-1] == 보존재 || [y][x] == 보존재
     * 보가 설치될 수 있는 조건
     * [y][x] == 기둥존재 || [y][x+1] == 기둥존재  || ([y][x-1] == 보존재 && [y][x+1] == 보존재)
     *
     * 기둥이 삭제될 수 있는 조건
     * [y+1][x] != 보존재 && [y+1][x] != 보존재 &&
     *
     */
    fun _기둥과보(
        n: Int = 5, build_frame: Array<IntArray> = arrayOf(
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(2, 1, 0, 1),
            intArrayOf(2, 2, 1, 1),
            intArrayOf(5, 0, 0, 1),
            intArrayOf(5, 1, 0, 1),
            intArrayOf(4, 2, 1, 1),
            intArrayOf(3, 2, 1, 1)
        )
    ): Array<IntArray> {
        val horizontalList = Array(n + 1) { BooleanArray(n + 1) { false } }
        val verticalList = Array(n + 1) { BooleanArray(n + 1) { false } }

        build_frame.forEach {
            val x = it[0]
            val y = it[1]
            val isVertical = it[2] == 0
            val isBuild = it[3] == 1

            println("==================================")
            println("[$y,$x]에 ${if (isVertical) "기둥" else "보"} ${if (isBuild) "설치" else "삭제"}")
            if (isBuild) {
                if (isVertical) {
                    if (y == 0 ||
                        verticalList[y - 1][x] ||
                        (x > 0 && horizontalList[y][x - 1]) ||
                        horizontalList[y][x]
                    ) {
                        verticalList[y][x] = true
                        println("성공")
                    }
                } else {
                    if ((y > 0 && verticalList[y - 1][x] && !verticalList[y][x]) ||
                        (y > 0 && x < n && verticalList[y - 1][x + 1] && !verticalList[y][x + 1]) ||
                        (x in (1 until n) && horizontalList[y][x - 1] && horizontalList[y][x + 1])
                    ) {
                        horizontalList[y][x] = true
                        println("성공")
                    }
                }
            } else {
//                if (isVertical) {
//                    if()
//                } else {
//                    if()
//                }
            }
            val log = StringBuilder()
            (n downTo 0).map { logY ->
                (0..n).map { logX ->
                    log.append(if (verticalList[logY][logX] || horizontalList[logY][logX]) "1 " else "0 ")
                }
                log.append("\n")
            }
            println(log)
        }
        val result = ArrayList<IntArray>()
        for (x in 0..n) {
            for (y in 0..n) {
                if (verticalList[y][x]) result.add(intArrayOf(x, y, 0))
                if (horizontalList[y][x]) result.add(intArrayOf(x, y, 1))
            }
        }
        println(result.joinToString("\n") { it.joinToString() })
        return result.toTypedArray()
    }
}