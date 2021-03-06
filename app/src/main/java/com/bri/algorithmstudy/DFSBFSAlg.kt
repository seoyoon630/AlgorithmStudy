@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.absoluteValue

object DFSBFSAlg {
    /**
     * 스택 : 선입후출
     * 삽입 = push / 삭제 = pop / 참조 = peek
     * val stack = Stack<Any>()
     * 큐 : 선입선출
     * 삽입 = offer / 삭제 = poll / 참조 = peek
     * val queue : Queue<Any> = LinkedList()
     * 재귀함수 : 자기자신을 다시 호출하는 함수, 종료 조건을 반드시 명시해야 한다.
     */

    fun factorial(n: Int = 5): Double {
        if (n <= 1) return 1.toDouble()
        return n * factorial(n - 1)
    }

    /**
     * 유클리드 호제법
     * 두 자연수 A, B에 대해서 (A>B) A를 B로 나눈 나머지를 R이라고 할 때,
     * A와 B의 최대공약수는 B와 R의 최대공약수와 같다.
     */
    fun _유클리드호제법(a: Int = 192, b: Int = 162): Int {
        println("_유클리드호제법 $a, $b")
        if (a % b == 0) return b
        val rest = a % b
        return _유클리드호제법(b, rest)
    }

    fun dfs(
        graph: Array<IntArray> = arrayOf(
            intArrayOf(),
            intArrayOf(2, 3, 8),
            intArrayOf(1, 7),
            intArrayOf(1, 4, 5),
            intArrayOf(3, 5),
            intArrayOf(3, 4),
            intArrayOf(7),
            intArrayOf(2, 6, 8),
            intArrayOf(1, 8)
        ), start: Int = 1
    ) {
        val visited = BooleanArray(graph.size) { false }
        dfsRecursive(graph, start, visited)
    }

    /** DFS(깊이 우선 탐색)
     * = 스택을 사용
     * 1. 탐색 시작 노드에 스택을 삽입하고 방문처리 한다.
     * 2. 스택의 최상단 노드에 방문하지 않은 인접한 노드가 하나라도 있으면, 그 노드에 스택을 넣고 방문처리 한다.
     * 방문하지 않은 인접 노드가 없으면 스택에서 최상단 노드를 꺼낸다.
     * 3. 2번의 과정을 수행할 수 없을 때까지 반복한다.
     */
    private fun dfsRecursive(graph: Array<IntArray>, current: Int, visited: BooleanArray) {
        println(current)
        visited[current] = true
        for (node in graph[current]) {
            if (!visited[node]) dfsRecursive(graph, node, visited)
        }
    }

    /** BFS(너비 우선 탐색)
     * = 큐를 사용
     * 1. 탐색 시작 노드를 큐에 삽입하고 방문처리 한다.
     * 2. 큐에서 노드를 꺼낸 뒤에 해당 노드의 인접 노드 중에서 방문하지 않은 노드를 모두 큐에 삽입하고 방문처리 한다.
     * 3. 2번의 과정을 수행할 수 없을 때까지 반복한다.
     * 각 간선의 길이가 동일하다는 조건에서 최단거리를 구하는 문제에서 사용됨
     */
    fun bfs(
        graph: Array<IntArray> = arrayOf(
            intArrayOf(),
            intArrayOf(2, 3, 8),
            intArrayOf(1, 7),
            intArrayOf(1, 4, 5),
            intArrayOf(3, 5),
            intArrayOf(3, 4),
            intArrayOf(7),
            intArrayOf(2, 6, 8),
            intArrayOf(1, 8)
        ), start: Int = 1
    ) {
        val visited = BooleanArray(graph.size) { false }
        val queue: Queue<Int> = LinkedList()
        var node: Int? = start
        visited[start] = true
        while (node != null) {
            println(node)
            for (next in graph[node]) {
                if (!visited[next]) {
                    queue.offer(next)
                    visited[next] = true
                }
            }
            node = queue.poll()
        }
    }

    /**
     * 1. y, x 로 이루어진 2중 for문 작성
     * 2. 현재 값이 0인지 확인
     * 3. 0이면 상하좌우에 있는 모든 값에 대해 재귀 호출
     */
    fun _음료수얼려먹기(
        arr: Array<IntArray> = arrayOf(
            intArrayOf(0, 0, 1, 1, 0),
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 0)
        )
    ): Int {
        var result = 0
        val visited = Array(arr.size) { BooleanArray(arr[0].size) { false } }

        for (y in arr.indices) {
            for (x in arr[y].indices) {
                if (recursiveDFS1(arr, visited, y, x)) {
                    result++
                }
            }
        }
        return result
    }

    private fun recursiveDFS1(
        arr: Array<IntArray>,
        visited: Array<BooleanArray>,
        y: Int,
        x: Int,
    ): Boolean {
        if (visited[y][x]) return false
        visited[y][x] = true
        if (arr[y][x] == 0) {
            if (y != 0) recursiveDFS1(arr, visited, y - 1, x)
            if (x != 0) recursiveDFS1(arr, visited, y, x - 1)
            if (y != arr.lastIndex) recursiveDFS1(arr, visited, y + 1, x)
            if (x != arr[0].lastIndex) recursiveDFS1(arr, visited, y, x + 1)
            return true
        }
        return false
    }

    /**
     * 최솟값을 찾을 때에는 BFS가 효율적
     * 1. 현재 위치에서 갈 수 있는 곳을 Queue에 쌓는다.
     * 2. 이동할 위치의 값을 현재 노드의 값 + 1로 변경한다.
     * 3. 출구 지점에 도달했을 때 현재 노드의 값을 리턴한다.
     */
    fun _미로찾기(
        arr: Array<IntArray> = arrayOf(
            intArrayOf(1, 0, 1, 0, 1, 0),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1)
        )
    ): Int {
        val dy = listOf(-1, 0, 1, 0)
        val dx = listOf(0, -1, 0, 1)
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(Pair(0, 0))
        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: continue
            val y = current.first
            val x = current.second
            if (y == arr.lastIndex && x == arr[0].lastIndex)
                return arr[y][x]
//            println("현재위치 $y,$x = ${arr[y][x]}")
//            arr.forEach {
//                println(it.joinToString())
//            }
//            println("=========================================================")
            for (i in dy.indices) {
                val nextY = y + dy[i]
                val nextX = x + dx[i]
                if (nextY in arr.indices &&
                    nextX in arr[0].indices &&
                    arr[nextY][nextX] == 1
                ) {
                    arr[nextY][nextX] = arr[y][x] + 1
                    queue.offer(Pair(nextY, nextX))
                }
            }
        }
        return -1
    }

    /**
     * @param n : 도시 개수
     * @param k : 특정 거리
     * @param x : 시작 도시
     * @param arr : 간선 목록
     *
     * 특정 depth까지만 가면 되기 때문에 BFS로 풀이
     * -> 모든 도시의 거리가 1이라는 가정때문에 가능
     * 1. arr을 첫번째 도시를 기준으로 map으로 변경
     * 2. 거리값을 넣을 IntArray(n) 생성 후 Int.MAX_VALUE로 초기화
     * 3. 시작도시까지의 거리는 0으로 시작
     * 4. 시작도시를 key로 가진 map을 꺼내서 해당 도시들의 값 + 1 해준다
     * 5. 특정거리에 도달하면 해당 도시의 개수를 카운트하여 리턴
     */
    fun _특정거리의도시찾기(
        n: Int = 4, k: Int = 2, x: Int = 1, arr: Array<IntArray> = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 4)
        )
    ): List<Int> {
        val map = HashMap<Int, ArrayList<Int>>().also {
            repeat(n) { i -> it[i] = ArrayList() }
        }
        arr.forEach { map[it[0]]?.add(it[1]) }

        val distance = Array(n + 1) { Int.MAX_VALUE }
        val queue: Queue<Int> = LinkedList()
        queue.offer(x)
        distance[x] = 0
        while (queue.isNotEmpty()) {
            val node = queue.poll() ?: break
            if (distance[node] == k) break
            map[node]?.forEach {
                if (distance[it] == Int.MAX_VALUE) {
                    distance[it] = distance[node] + 1
                    queue.offer(it)
                }
            }
        }
        val result = distance.indices.filter { distance[it] == k }.sorted()
        return if (result.isEmpty()) listOf(-1) else result
    }

    /**
     * 2에 근접한 빈 공간을 막은 뒤 가능한 모든 경우를 DFS로 구해서 안전 구역 최대값을 구해야 한다.
     * 0. 바이러스가 위치한 좌표를 미리 구한다.
     * 1. 2에 상하좌우로 맞닿은 좌표를 모두 구한다.
     * 2. n개 중 3개를 구하는 모든 경우의 수를 구한다.
     * 3. 모든 경우의 수에 대해 안전구역 최대값을 구한다.
     */
    fun _연구소(
        arr: Array<IntArray> = arrayOf(
            intArrayOf(2, 0, 0, 0, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 1, 2, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
        )
    ): Int {
        val viruses = arr.mapIndexed { y, row ->
            row.indices.filter { row[it] == 2 }.map { Pair(y, it) }
        }.flatten()
        println("바이러스 위치 = ${viruses.joinToString()}")
        val canMakeWall = ArrayList<Pair<Int, Int>>()
        arr.forEachIndexed { y, row ->
            row.forEachIndexed { x, v -> if (v == 0) canMakeWall.add(Pair(y, x)) }
        }
        println("벽 생성 가능 위치 = ${canMakeWall.joinToString()}")
        // todo 0인 모든 곳에 세울 필요가 있을까? 더 줄일 수 있을 것 같음
        drawLine()
        val cases = CombinationAlg.combination(canMakeWall.size, 3)
        var maxArea = 0
        for (case in cases) {
            val result = Array(arr.size) { arr[it].clone() }
            case.forEach { result[canMakeWall[it].first][canMakeWall[it].second] = 3 }
            val area = spreadVirus(result, viruses)
            if (maxArea < area) {
                maxArea = area
                println(case.joinToString { "[${canMakeWall[it].first}, ${canMakeWall[it].second}]" } + "에 벽을 세웁니다.")
                println("현재 최대값 = $area")
                drawLine()
            }
        }
        return maxArea
    }

    /**
     * 바이러스를 퍼뜨린 뒤 안전지역의 갯수를 구하는 함수
     */
    private fun spreadVirus(arr: Array<IntArray>, viruses: List<Pair<Int, Int>>): Int {
        val dy = listOf(-1, 0, 1, 0)
        val dx = listOf(0, -1, 0, 1)
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        viruses.forEach { queue.offer(it) }
        while (queue.isNotEmpty()) {
            val node = queue.poll() ?: break
            val y = node.first
            val x = node.second
            repeat(4) {
                val nextY = y + dy[it]
                val nextX = x + dx[it]
                if (nextY in arr.indices && nextX in arr[0].indices &&
                    arr[nextY][nextX] == 0
                ) {
                    arr[nextY][nextX] = 2
                    queue.offer(Pair(nextY, nextX))
                }
            }
        }
        return arr.map { it.count { it == 0 } }.sum()
    }

    /**
     * BFS
     * 1. k개의 queue 생성
     * 2. arr에서 바이러스가 있는 위치를 queue에 삽입
     * 3. s초가 될 때까지 반복
     * 4. queue에서 꺼내서 상하좌우 좌표 추가 및 해당 값 변경
     */
    fun _경쟁적전염(
        n: Int = 3,
        k: Int = 3,
        s: Int = 2,
        targetY: Int = 3,
        targetX: Int = 2,
        arr: Array<IntArray> = arrayOf(
            intArrayOf(1, 0, 2),
            intArrayOf(0, 0, 0),
            intArrayOf(3, 0, 0)
        )
    ): Int {
        val dy = listOf(-1, 0, 1, 0)
        val dx = listOf(0, -1, 0, 1)
        val map = HashMap<Int, HashSet<Coordinate>>().apply {
            repeat(k + 1) {
                this[it] = hashSetOf()
            }
        }
        // arr에서 바이러스가 있는 위치를 map에 삽입
        arr.forEachIndexed { y, row ->
            row.forEachIndexed { x, v -> if (v > 0) map[v]?.add(Coordinate(y, x)) }
        }
        val queue: Queue<Coordinate> = LinkedList()
        map.flatMap { it.value }.forEach { queue.offer(it) }

        // s초동안 반복
        repeat(s) {
            // 만약 바이러스가 모두 찼다면 반복문을 빠져 나옴
            if (arr.map { it.count { it == 0 } }.sum() == 0) return@repeat
            // 1~k 까지의 queue를
            repeat(queue.size) {
                val coordinate = queue.poll() ?: return -1
                // 상하좌우 값 변경 및 queue에 삽입
                repeat(4) { i ->
                    val nextY = coordinate.y + dy[i]
                    val nextX = coordinate.x + dx[i]
                    if (nextY in 0 until n && nextX in 0 until n && arr[nextY][nextX] == 0) {
                        arr[nextY][nextX] = arr[coordinate.y][coordinate.x]
                        queue.offer(Coordinate(nextY, nextX))
                    }
                }
            }
        }
        return arr[targetY - 1][targetX - 1]
    }

    //1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
    //2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
    //3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
    //  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
    //4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
    //  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
    //  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
    //  4-3. ')'를 다시 붙입니다.
    //  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
    //  4-5. 생성된 문자열을 반환합니다.
    fun _괄호변환(s: String = "(()())()"): String {
        val map = HashMap<Char, Int>().apply {
            put('(', 1)
            put(')', -1)
        }
        return recursiveDFS2(s, map, "")
    }

    private fun recursiveDFS2(
        s: String,
        map: HashMap<Char, Int>,
        result: String
    ): String {
        if (s.isEmpty()) return result
        val uv = splitUV(s, map)
        val u = uv.first
        val v = uv.second
        return if (isCorrect(u, map)) recursiveDFS2(v, map, result + u)
        else {
            val reversed =
                u.substring(1, u.lastIndex).map { if (it == '(') ')' else '(' }.joinToString("")
            "$result(${recursiveDFS2(v, map, "")})$reversed"
        }
    }

    private fun splitUV(s: String, map: HashMap<Char, Int>): Pair<String, String> {
        val splitIndex = s.indices.first {
            var sum = 0
            repeat(it + 1) { i -> sum += map[s[i]] ?: 0 }
            sum == 0
        }
        val u = s.substring(0, splitIndex + 1)
        val v = s.substring(splitIndex + 1, s.length)
        return Pair(u, v)
    }

    private fun isCorrect(s: String, map: HashMap<Char, Int>): Boolean {
        var sum = 0
        s.forEach {
            sum += map[it] ?: 0
            if (sum < 0) return false
        }
        return true
    }

    /**
     * 순열(DFS)
     * 연산자 목록을 만들어서 DFS로 최솟값과 최댓값을 구한다.
     * + - * /
     */
    fun _연산자끼워넣기(
        numbers: IntArray = intArrayOf(5, 6),
        o: IntArray = intArrayOf(0, 0, 1, 0)
    ): IntArray {
        val result = intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE)
        val operators = IntArray(numbers.size - 1) {
            val index = o.indices.first { o[it] != 0 }
            o[index] -= 1
            index
        }
        val cases = PermutationAlg.permutation(operators)
        for (case in cases) {
            var sum = numbers[0]
            case.forEachIndexed { i, v ->
//                println(
//                    when (v) {
//                        0 -> "$sum + ${numbers[i+1]} = ${sum + numbers[i+1]}"
//                        1 -> "$sum - ${numbers[i+1]} = ${sum - numbers[i+1]}"
//                        2 -> "$sum * ${numbers[i+1]} = ${sum * numbers[i+1]}"
//                        else -> "$sum / ${numbers[i+1]} = ${sum / numbers[i+1]}"
//                    }
//                )
                when (v) {
                    0 -> sum += numbers[i + 1]
                    1 -> sum -= numbers[i + 1]
                    2 -> sum *= numbers[i + 1]
                    3 -> sum /= numbers[i + 1]
                }

            }
            drawLine()
            if (sum > result[0]) result[0] = sum
            if (sum < result[1]) result[1] = sum
        }
        return result
    }

    /**
     * 순서 중요 X -> 조합(DFS)
     * 모든 학생의 상하좌우를 확인한다.
     * 해당 방향에 선생님이 있다면 사이에 있는 위치를 모두 set에 넣는다.
     * set에 들어간 모든 경우를 확인하고, 모두 막은 케이스가 나오면 바로 return
     */
    fun _감시피하기(
        n: Int = 5, arr: Array<CharArray> = arrayOf(
            charArrayOf('X', 'S', 'X', 'X', 'T'),
            charArrayOf('T', 'X', 'S', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X', 'X'),
            charArrayOf('X', 'T', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'T', 'X', 'X'),
        )
    ): String {
        val dy = listOf(-1, 0, 1, 0)
        val dx = listOf(0, -1, 0, 1)
        val students = hashSetOf<Coordinate>()
        val set = hashSetOf<Coordinate>()

        arr.forEachIndexed { y, row ->
            row.forEachIndexed { x, v ->
                if (v == 'S') {
                    students.add(Coordinate(x, y))
                    repeat(4) {
                        var nextY = y + dy[it]
                        var nextX = x + dx[it]
                        while (nextY in arr.indices && nextX in arr.indices) {
                            if (arr[nextY][nextX] == 'T') {
                                // 선생님과 학생이 붙어있을 경우 불가능
                                if ((nextY - y).absoluteValue == 1 || (nextX - x).absoluteValue == 1)
                                    return "NO"
                                val start = when (it) {
                                    0 -> nextY + 1
                                    1 -> nextX + 1
                                    2 -> y + 1
                                    else -> x + 1
                                }
                                val end = when (it) {
                                    0 -> y
                                    1 -> x
                                    2 -> nextY
                                    else -> nextX
                                }
                                if (it % 2 == 0) {
                                    for (i in start until end) {
                                        set.add(Coordinate(x, i))
                                    }
                                } else {
                                    for (i in start until end) {
                                        set.add(Coordinate(i, y))
                                    }
                                }
                                break
                            } else {
                                nextY += dy[it]
                                nextX += dx[it]
                            }
                        }
                    }
                }
            }
        }
        val list = set.toTypedArray()
        val cases = CombinationAlg.combination(list.size, 3)
        for (case in cases) {
            var nextCase = false
            val copied = Array(arr.size) { arr[it].clone() }
            repeat(3) {
                copied[list[case[it]].y][list[case[it]].x] = 'O'
            }
            copied.forEach { println(it.joinToString()) }
            drawLine()
            for (student in students) {
                repeat(4) {
                    var nextY = student.y + dy[it]
                    var nextX = student.x + dx[it]
                    while (nextY in arr.indices && nextX in arr.indices) {
                        when (copied[nextY][nextX]) {
                            'T' -> {
                                nextCase = true
                                break
                            }
                            'O' -> break
                            else -> {
                                nextY += dy[it]
                                nextX += dx[it]
                            }
                        }
                    }
                    if (student == students.last() && it == 3) return "YES"
                    if (nextCase) return@repeat
                }
                if (nextCase) break
            }
        }

        return "NO"
    }

    /**
     * BFS로 풀이
     */
    fun _인구이동(
        n: Int = 2, l: Int = 20, r: Int = 50, arr: Array<IntArray> = arrayOf(
            intArrayOf(50, 30),
            intArrayOf(20, 40)
        )
    ): Int {
        var result = 0
        while (true) {
            val queue: Queue<Coordinate> = LinkedList()
            val visited = Array(n) { BooleanArray(n) { false } }
            var index = 0
            val map = HashMap<Int, ArrayList<Coordinate>>()
            for (y in 0 until n) {
                for (x in 0 until n) {
                    if (!visited[y][x]) {
                        queue.offer(Coordinate(y, x))
                        map[++index] = ArrayList()
                        while (queue.isNotEmpty()) {
                            val c = queue.poll() ?: return result
                            if (visited[c.y][c.x]) continue
                            visited[c.y][c.x] = true
                            map[index]?.add(c)
                            if (c.y > 0 && !visited[c.y - 1][c.x] &&
                                (arr[c.y][c.x] - arr[c.y - 1][c.x]).absoluteValue in l..r
                            )
                                queue.offer(Coordinate(c.y - 1, c.x))
                            if (c.x > 0 && !visited[c.y][c.x - 1] &&
                                (arr[c.y][c.x] - arr[c.y][c.x - 1]).absoluteValue in l..r
                            )
                                queue.offer(Coordinate(c.y, c.x - 1))
                            if (c.y < n - 1 && !visited[c.y + 1][c.x] &&
                                (arr[c.y][c.x] - arr[c.y + 1][c.x]).absoluteValue in l..r
                            )
                                queue.offer(Coordinate(c.y + 1, c.x))
                            if (c.x < n - 1 && !visited[c.y][c.x + 1] &&
                                (arr[c.y][c.x] - arr[c.y][c.x + 1]).absoluteValue in l..r
                            )
                                queue.offer(Coordinate(c.y, c.x + 1))
                        }
                    }
                }
            }
            if (map.values.count { it.size > 1 } == 0) {
                break
            } else {
                result++
                map.filter { it.value.size > 1 }.forEach { (key, value) ->
                    println("$key = ${value.joinToString()}")
                    val count = value.size
                    val sum = value.map { arr[it.y][it.x] }.sum()
                    val population = sum / count
                    value.map { arr[it.y][it.x] = population }
                }
                map.clear()
            }
        }
        return result
    }

    fun _블록이동하기(
        arr: Array<IntArray> = arrayOf(
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 1, 0, 1, 1),
            intArrayOf(1, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0)
        )
    ) {
        val n = arr.size
        val max = n*n
        // visited 배열 추가
        val visited = HashMap<String, Boolean>()
        arr.forEachIndexed { y, row ->
            row.forEachIndexed { x, v ->
                if (v == 1) {
                    arr[y][x] = -1
                } else {
                    if ((y == 0 && x == 0) || (y == 0 && x == 1)) {
                        arr[y][x] = 0
                    } else arr[y][x] = max
                }
                if (x < n - 1) {
                    visited[intArrayOf(y, x, y, x + 1).joinToString()] = false
                }
                if (y < n - 1) {
                    visited[intArrayOf(y, x, y + 1, x).joinToString()] = false
                }
            }
        }
        println(arr.joinToString("\n") { it.joinToString("\t") })
        drawLine()
        // queue 추가
        val queue: Queue<IntArray> = LinkedList()
        queue.add(intArrayOf(0, 0, 0, 1))
        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: return
            if (visited[current.joinToString()] == true) continue
            visited[current.joinToString()] = true
            // 상하좌우 이동
            val y1 = current[0]
            val x1 = current[1]
            val y2 = current[2]
            val x2 = current[3]
            if (y1 > 0 && visited[intArrayOf(y1 - 1, x1, y2 - 1, x2).joinToString()] == false) {
                queue.offer(intArrayOf(y1 - 1, x1, y2 - 1, x2))
                println("상")
                if (arr[y1 - 1][x1] == max) {
                    arr[y1 - 1][x1] = arr[y1][x1] + 1
                }
                if (arr[y2 - 1][x2] == max) {
                    arr[y2 - 1][x2] = arr[y2][x2] + 1
                }
            }
            if (x1 > 0 && visited[intArrayOf(y1, x1 - 1, y2, x2 - 1).joinToString()] == false) {
                queue.offer(intArrayOf(y1, x1 - 1, y2, x2 - 1))
                println("좌")
                if (arr[y1][x1 - 1] == max) {
                    arr[y1][x1 - 1] = arr[y1][x1] + 1
                }
                if (arr[y2][x2 - 1] == max) {
                    arr[y2][x2 - 1] = arr[y2][x2] + 1
                }
            }
            if (y2 < n - 1 && visited[intArrayOf(y1 + 1, x1, y2 + 1, x2).joinToString()] == false) {
                queue.offer(intArrayOf(y1 + 1, x1, y2 + 1, x2))
                println("하")
                if (arr[y1 + 1][x1] == max) {
                    arr[y1 + 1][x1] = arr[y1][x1] + 1
                }
                if (arr[y2 + 1][x2] == max) {
                    arr[y2 + 1][x2] = arr[y2][x2] + 1
                }
            }
            if (x2 < n - 1 && visited[intArrayOf(y1 , x1+1, y2 , x2+1).joinToString()] == false) {
                queue.offer(intArrayOf(y1, x1 + 1, y2, x2 + 1))
                println("우")
                if (arr[y1][x1 + 1] == max) {
                    arr[y1][x1 + 1] = arr[y1][x1] + 1
                }
                if (arr[y2][x2 + 1] == max) {
                    arr[y2][x2 + 1] = arr[y2][x2] + 1
                }
            }
            // 가로 -> 세로 회전
            if (x2 - x1 == 1) {
                if (y1 > 0 && arr[y1 - 1][x1] != -1 && arr[y2 - 1][x2] != -1) {
                    if (arr[y1 - 1][x1] == max &&
                        visited[intArrayOf(y1 - 1, x1, y1, x1).joinToString()] == false
                    ) {
                        println("가로 1")
                        arr[y1 - 1][x1] = arr[y1][x1] + 1
                        queue.offer(intArrayOf(y1 - 1, x1, y1, x1))
                    }
                    if (arr[y2 - 1][x2] == max &&
                        visited[intArrayOf(y2 - 1, x2, y2, x2).joinToString()] == false
                    ) {
                        println("가로 2")
                        arr[y2 - 1][x2] = arr[y2][x2] + 1
                        queue.offer(intArrayOf(y2 - 1, x2, y2, x2))
                    }
                }
                if (y1 < n - 1 && arr[y1 + 1][x1] != -1 && arr[y2 + 1][x2] != -1) {
                    if (arr[y1 + 1][x1] == max &&
                        visited[intArrayOf(y1, x1, y1 + 1, x1).joinToString()] == false
                    ) {
                        println("가로 3")
                        arr[y1 + 1][x1] = arr[y1][x1] + 1
                        queue.add(intArrayOf(y1, x1, y1 + 1, x1))
                    }
                    if (arr[y2 + 1][x2] == max &&
                        visited[intArrayOf(y2, x2, y2 + 1, x2).joinToString()] == false
                    ) {
                        println("가로 4")
                        arr[y2 + 1][x2] = arr[y2][x2] + 1
                        queue.add(intArrayOf(y2, x2, y2 + 1, x2))
                    }
                }
            }
            // 세로 -> 가로 회전
            else {
                if (x1 > 0 && arr[y1][x1 - 1] != -1 && arr[y2][x2 - 1] != -1 &&
                    visited[intArrayOf(y1, x1 - 1, y2, x2 - 1).joinToString()] == false
                ) {
                    if (arr[y1][x1 - 1] == max) {
                        println("세로 1")
                        arr[y1][x1 - 1] = arr[y1][x1] + 1
                    }
                    if (arr[y2][x2 - 1] == max) {
                        println("세로 2")
                        arr[y2][x2 - 1] = arr[y2][x2] + 1
                    }
                }
                if (x1 < n - 1 && arr[y1][x1 + 1] != -1 && arr[y2][x2 + 1] != -1 &&
                    visited[intArrayOf(y1, x1 + 1, y2, x2 + 1).joinToString()] == false
                ) {
                    if (arr[y1][x1 + 1] == max) {
                        println("세로 3")
                        arr[y1][x1 + 1] = arr[y1][x1] + 1
                    }
                    if (arr[y2][x2 + 1] == max) {
                        println("세로 4")
                        arr[y2][x2 + 1] = arr[y2][x2] + 1

                    }
                }
            }
            println("[$y1, $x1], [$y2, $x2]")
            println(arr.joinToString("\n") { it.joinToString("\t") })
            drawLine()
        }
    }
}

data class Coordinate(val y: Int, val x: Int)