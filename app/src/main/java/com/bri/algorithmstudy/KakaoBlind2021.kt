package com.bri.algorithmstudy

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object KakaoBlind2021 {
    // 이진탐색 알고리즘
    fun _순위검색(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size) { 0 }
        val infos = HashMap<String, ArrayList<Int>>()
        info.forEach {
            val key = makeKey(it)
            val score = getScore(it)
            if (!infos.containsKey(key)) infos[key] = ArrayList()
            infos[key]?.add(score)
        }
        // 정렬
        infos.keys.forEach { infos[it]?.sort() }

        query.forEachIndexed { index, s ->
            val key = makeKey(s)
            val score = getScore(s)
            val regex = key.replace("0", "[0-9]").toRegex()
            answer[index] = infos.keys.filter { regex.matches(it) }
                .map { infos[it] }
                .sumOf {
                    val size = it?.size ?: 0
                    val first = binarySearch(it, score).takeIf { it > -1 } ?: size
                    size - first
                }
        }

        return answer
    }

    private fun makeKey(s: String): String {
        val split = s.split(" ").filter { it != "and" }
        val lang = listOf("-", "cpp", "java", "python").indexOf(split[0])
        val group = listOf("-", "backend", "frontend").indexOf(split[1])
        val career = listOf("-", "junior", "senior").indexOf(split[2])
        val food = listOf("-", "chicken", "pizza").indexOf(split[3])
        return "$lang$group$career$food"
    }

    private fun getScore(s: String): Int {
        return s.split(" ").filter { it != "and" }[4].toInt()
    }

    private fun binarySearch(arr: ArrayList<Int>?, target: Int): Int {
        if (arr == null) return -1
        var start = 0
        var end = arr.lastIndex
        var mid: Int
        var answer = -1
        while (start <= end) {
            mid = (start + end) / 2
            when {
//                arr[mid] == target -> return mid
                arr[mid] >= target -> {
                    answer = mid
                    end = mid - 1
                }
                arr[mid] < target -> start = mid + 1
            }
        }
        return answer
    }

    //======================================================================

    // 플로이드-워셜 알고리즘
    @RequiresApi(Build.VERSION_CODES.N)
    fun _택시합승요금(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val cost = Array(n + 1) { y ->
            IntArray(n + 1) { x ->
                if (x == y) 0 else 200 * 100000
            }
        }

        fares.forEach {
            val n1 = it[0]
            val n2 = it[1]
            val fare = it[2]
            cost[n1][n2] = fare
            cost[n2][n1] = fare
        }

        // Floyd-Warshall
        for (i in 1..n) {
            for (j in 1..n) {
                if (i == j) continue
                for (k in 1..n) {
                    if (i == k) continue
                    if (j == k) continue
                    val distance = cost[j][i] + cost[i][k]
                    if (cost[j][k] > distance) {
                        cost[j][k] = distance
                    }
                }
            }
        }

        return (1..n).map { cost[s][it] + cost[it][a] + cost[it][b] }
            .minOrNull() ?: -1
    }

    //======================================================================

    @RequiresApi(Build.VERSION_CODES.N)
    fun _광고삽입(play_time: String, adv_time: String, logs: Array<String>): String {
        val totalTime = convertToInt(play_time) + 1
        val advTime = convertToInt(adv_time)
        val times = LongArray(totalTime + 1) { 0 }
        logs.forEach {
            val start = convertToInt(it.split("-")[0]) + 1
            val end = convertToInt(it.split("-")[1]) + 1
            times[start]++
            times[end]--
        }
        for (i in 1..times.lastIndex) {
            times[i] += times[i - 1]
        }
        for (i in 1..times.lastIndex) {
            times[i] += times[i - 1]
        }
        var max: Long = 0
        var result = 0
        for (i in 0 until totalTime - advTime) {
            val current = times[i + advTime] - times[i]
            if (max < current) {
                max = current
                result = i
            }
        }

        return convertToString(result)
    }

    private fun convertToInt(s: String): Int {
        return s.split(":")
            .mapIndexed { index, time ->
                Math.pow(60.toDouble(), (2 - index).toDouble()).toInt() * time.toInt()
            }.sum()
    }

    private fun convertToString(t: Int): String {
        val hour = t / (60 * 60)
        val min = t % (60 * 60) / 60
        val second = t % (60)
        return "${formatString(hour)}:${formatString(min)}:${formatString(second)}"
    }

    private fun formatString(s: Int): String {
        return String.format(Locale.getDefault(), "%02d", s)
    }

    //======================================================================

    fun _카드짝맞추기(board: Array<IntArray>, r: Int, c: Int): Int {
        var answer: Int = Int.MAX_VALUE
        val cards = ArrayList<IntArray>()
        board.forEachIndexed { y, it ->
            it.forEachIndexed { x, i ->
                if (i != 0) {
                    cards.add(intArrayOf(y, x, i))
                }
            }
        }
        val cases = PermutationAlg.permutation(IntArray(cards.size) { it })
        cases.forEach { case ->
            var current = intArrayOf(r, c, 0)
            var distance = 0
            val queue: Queue<Int> = LinkedList()
            val visits = BooleanArray(cards.size) { false }
            queue.addAll(case.toList())
            val copyOfBoard = Array(4) { board[it].copyOf() }
            while (queue.isNotEmpty()) {
                val startIdx = queue.poll() ?: break
                val start = cards[startIdx]
                val endIdx =
                    cards.indices.find { it != startIdx && cards[it][2] == cards[startIdx][2] }
                        ?: continue
                val end = cards[endIdx]
                if (visits[startIdx]) continue
                visits[startIdx] = true
                visits[endIdx] = true
                distance += getDistance(copyOfBoard, current[0], current[1], start[0], start[1])
                distance += getDistance(copyOfBoard, start[0], start[1], end[0], end[1])
                distance += 2
                if (distance > answer) break
                copyOfBoard[start[0]][start[1]] = 0
                copyOfBoard[end[0]][end[1]] = 0
                current = end
            }
            if (answer > distance) {
                answer = distance
            }
        }
        return answer
    }

    fun _카드짝맞추기2(board: Array<IntArray>, r: Int, c: Int): Int {
        var answer: Int = Int.MAX_VALUE
        val cards = HashMap<Int, IntArray>()
        board.forEachIndexed { y, it ->
            it.forEachIndexed { x, i ->
                if (i != 0) {
                    cards[i]?.let {
                        it[2] = y
                        it[3] = x
                    } ?: run {
                        cards[i] = IntArray(4) { -1 }
                        cards[i]?.set(0, y)
                        cards[i]?.set(1, x)
                    }
                }
            }
        }
        val cases = PermutationAlg.permutation(IntArray(cards.size) { it + 1 })
        cases.forEach { case ->
            val queue: Queue<Int> = LinkedList()
            queue.addAll(case.toList())
            val copyOfBoard = Array(4) { board[it].copyOf() }
            println("case = ${case.joinToString()}")
            val distance =
                dfsForCard(copyOfBoard, case, r, c, 0, cards, 0, IntArray(cards.size) { -1 })
            if (answer > distance) {
                answer = distance
            }
        }
        println("총 개수(${cards.size}) : $test")
        return answer
    }

    var test = 0
    private fun dfsForCard(
        board: Array<IntArray>,
        case: IntArray,
        startY: Int,
        startX: Int,
        count: Int,
        cards: HashMap<Int, IntArray>,
        level: Int,
        list: IntArray
    ): Int {
        if (level >= case.size) {
            println(list.joinToString())
            test++
            return count
        } else {
            val card = cards[case[level]] ?: return count
            val distance = getDistance(board, card[0], card[1], card[2], card[3])
            val distance1 = getDistance(board, startY, startX, card[0], card[1]) + distance + 2
            val distance2 = getDistance(board, startY, startX, card[2], card[3]) + distance + 2
            board[card[0]][card[1]] = 0
            board[card[2]][card[3]] = 0
            val list1 = list.copyOf()
            val list2 = list.copyOf()
            list1[level] = level * 2
            list2[level] = level * 2 + 1
            return Math.min(
                dfsForCard(
                    board,
                    case,
                    card[0],
                    card[1],
                    count + distance1,
                    cards,
                    level + 1,
                    list1
                ),
                dfsForCard(
                    board,
                    case,
                    card[2],
                    card[3],
                    count + distance2,
                    cards,
                    level + 1,
                    list2
                )
            )
        }
    }

    private fun getDistance(
        board: Array<IntArray>,
        startY: Int,
        startX: Int,
        endY: Int,
        endX: Int
    ): Int {
        val dx = listOf(-1, 0, 1, 0)
        val dy = listOf(0, -1, 0, 1)
        val distances = Array(board.size) { IntArray(board.size) { 16 } }
        val queue: Queue<IntArray> = LinkedList()
        queue.add(intArrayOf(startY, startX, 0))
        distances[startY][startX] = 0
        if (startY == endY && startX == endX) return 0
        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: break
            val y = current[0]
            val x = current[1]
            val distance = current[2]
            val newDistance = distance + 1
            repeat(4) {
                val newY = y + dy[it]
                val newX = x + dx[it]
                if (newY in board.indices && newX in board.indices) {
                    if (distances[newY][newX] > newDistance) {
                        distances[newY][newX] = newDistance
                        queue.add(intArrayOf(newY, newX, newDistance))
                    }
                    if (newY == endY && newX == endX) {
                        return newDistance
                    }
                }
            }
            repeat(4) {
                var newY = y
                var newX = x
                var nextY = y + dy[it]
                var nextX = x + dx[it]
                while (nextY in board.indices && nextX in board.indices) {
                    newX = nextX
                    newY = nextY
                    if (board[nextY][nextX] == 0) {
                        nextY += dy[it]
                        nextX += dx[it]
                    } else break
                }
                if (distances[newY][newX] > newDistance) {
                    distances[newY][newX] = newDistance
                    queue.add(intArrayOf(newY, newX, newDistance))
                }
                if (newY == endY && newX == endX) {
                    return newDistance
                }
            }
        }
        return -1
    }

    fun _숫자문자열과영단어(s: String): Int {
        var result = s
        arrayOf(
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
        ).forEachIndexed { index, word -> result = result.replace(word, "$index") }
        return result.toInt()
    }

    fun _거리두기확인하기(places: Array<Array<String>>): IntArray {
        return places.map { place ->
            var result = 1
            val board = place.map { it.toCharArray() }
            val queue: Queue<IntArray> = LinkedList()
            val dy = intArrayOf(-1, 0, 1, 0)
            val dx = intArrayOf(0, -1, 0, 1)
            board.forEachIndexed { y, row ->
                val people = row.indices.filter { index -> row[index] == 'P' }
                people.forEach { x -> queue.add(intArrayOf(y, x, 0, 4)) }
            }
            while (queue.isNotEmpty()) {
                val node = queue.poll() ?: break
                for (i in 0 until 4) {
                    if (node[3] == i) continue
                    val y = node[0] + dy[i]
                    val x = node[1] + dx[i]
                    if (y !in board.indices || x !in board.indices) continue
                    when (board[y][x]) {
                        'O' -> if (node[2] == 0)
                            queue.add(intArrayOf(y, x, 1, (i + 2) % 4))
                        'P' -> {
                            result = 0
                            queue.clear()
                        }
                    }
                }
            }
            result
        }.toIntArray()
    }
}