package com.bri.algorithmstudy

import java.util.*

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
    fun dfsRecursive(graph: Array<IntArray>, current: Int, visited: BooleanArray) {
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

    fun recursiveDFS1(
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
}