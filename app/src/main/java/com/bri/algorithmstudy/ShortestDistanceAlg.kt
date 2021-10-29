@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

object ShortestDistanceAlg {
    /**
     * 다익스트라 알고리즘
     * 특정한 노드에서 출발하여 다른 모든 노드로 가는 최단 경로를 계산
     * 음의 간선이 없을 때 정상적으로 동작
     * -> 그리디 알고리즘으로 분류
     * 매 상황에서 가장 비용이 적은 노드를 선택하여 반복
     *
     * 1. 출발 노드 설정
     * 2. 최단 거리 테이블 초기화 -> 출발 노드 = 0 , 그외 노드 = 최댓값
     * 3. 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드 선택
     * 4. 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블을 갱신
     * 5. 3,4번 반복
     *
     * 우선순위 큐
     * 우선순위가 가장 높은 데이터를 가장 먼저 삭제
     * 내부적으로 트리형태로 구현
     * -> 최소 힙, 최대 힙 (logN 시간 복잡도)
     */

    @RequiresApi(Build.VERSION_CODES.N)
    fun _우선순위큐를활용한_다익스트라(
        n: Int = 6,
        from: Int = 1, to: Int = 6, distances: Array<IntArray> = arrayOf(
            intArrayOf(1, 2, 2),
            intArrayOf(1, 3, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(2, 3, 3),
            intArrayOf(2, 4, 2),
            intArrayOf(3, 2, 3),
            intArrayOf(3, 6, 5),
            intArrayOf(4, 3, 3),
            intArrayOf(4, 5, 1),
            intArrayOf(5, 3, 1),
            intArrayOf(5, 6, 2),
        )
    ) : Int {
        val queue = PriorityQueue(NodeComparator())
        val d = Array(n + 1) { ArrayList<Node>() }
        distances.forEach { d[it[0]].add(Node(it[2], it[1])) }
        val result = Array(n + 1) { 100 }
        val visited = Array(n + 1) { false }

        println(d.joinToString("\n") { it.joinToString() })
        // 시작점 설정
        result[from] = 5
        queue.add(Node(result[from], from))

        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: break
            if (visited[current.index]) continue
            visited[current.index] = true
            println("${current.index} 연결 도시 갱신")
            d[current.index].forEach {
                val d1 = it.distance + result[current.index]
                val d2 = result[it.index]
                if (d1 < d2) {
                    queue.add(it)
                    result[it.index] = d1
                    println("${it.index} 갱신! $d1")
                    for (i in 0 until 3) {
                        println("" + result[i * 3 + 1] + "\t" + result[i * 3 + 2] + "\t" + result[i * 3 + 3] + "\t")
                    }
                    drawLine()
                }
            }
        }
        println(result.joinToString())
        return result[to]
    }

    /**
     * 플로이드 워셜 알고리즘
     * 단계별로 거쳐 가는 노드를 기준으로 알고리즘을 수행
     * 매 단계마다 방문하지 않은 노드 중에 최단 거리를 갖는 노드를 찾는 과정이 필요 없음
     * 시간 복잡도 = O(N^3)
     * 2차원 테이블에 최단 거리 정보를 저장
     * 다이나믹 프로그래밍 유형
     * ab최단거리 = min(ak최단거리 + kb최단거리, ab최단거리)
     */
    fun _플로이드워셜(
        n: Int = 4,
        from: Int = 1, to: Int = 4, distances: Array<IntArray> = arrayOf(
            intArrayOf(1, 2, 4),
            intArrayOf(1, 4, 6),
            intArrayOf(2, 1, 3),
            intArrayOf(2, 3, 7),
            intArrayOf(3, 1, 5),
            intArrayOf(3, 4, 4),
            intArrayOf(4, 3, 2)
        )
    ): Int {
        val d = Array(n) { y ->
            IntArray(n) { x ->
                if (y == x) 0
                else Int.MAX_VALUE
            }
        }
        distances.forEach {
            d[it[0] - 1][it[1] - 1] = it[2]
        }
        println(d.joinToString("\n") { it.joinToString() })
        drawLine()
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (j == i) continue
                for (k in 0 until n) {
                    if (k == j || k == i) continue
                    if (d[j][i] == Int.MAX_VALUE || d[i][k] == Int.MAX_VALUE) continue
                    d[j][k] = Math.min(d[j][k], d[j][i] + d[i][k])
                }
            }
            println(d.joinToString("\n") { it.joinToString() })
            drawLine()
        }
        println(d.joinToString("\n") { it.joinToString() })
        return d[from - 1][to - 1]
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun _전보(
        n: Int = 3,
        c: Int = 1,
        m: Array<IntArray> = arrayOf(intArrayOf(1, 2, 4), intArrayOf(1, 3, 2))
    ): IntArray {
        val d = Array(n + 1) { ArrayList<Node>() }
        m.forEach { d[it[0]].add(Node(it[2], it[1])) }
        val visited = BooleanArray(n + 1) { false }
        val result = Array(n + 1) { Int.MAX_VALUE }
        val queue = PriorityQueue(NodeComparator())
        var max = 0

        // 시작점
        queue.add(Node(0, c))
        result[c] = 0
        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: break
            if (visited[current.index]) continue
            visited[current.index] = true
            d[current.index].forEach {
                val cost = current.distance + it.distance
                if (cost < result[it.index]) {
                    result[it.index] = cost
                    queue.add(Node(cost, it.index))
                    if (max < cost) max = cost
                }
            }
        }
        println(result.joinToString())
        return intArrayOf(visited.count { it } - 1, max)
    }

    fun _미래도시(
        n: Int = 5, x: Int = 4, k: Int = 5, d: Array<IntArray> = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 4),
            intArrayOf(3, 4),
            intArrayOf(3, 5),
            intArrayOf(4, 5)
        )
    ): Int {
        val roads = Array(n + 1) { ArrayList<Int>() }
        d.forEach {
            roads[it[0]].add(it[1])
            roads[it[1]].add(it[0])
        }

        val d1 = dijkstra(n, 1, k, roads)
        val d2 = dijkstra(n, k, x, roads)
        return if (d1 == null || d2 == null) -1 else d1 + d2
    }

    private fun dijkstra(n: Int, i: Int, k: Int, roads: Array<ArrayList<Int>>): Int? {
        val visited = BooleanArray(n + 1) { false }
        val distance = IntArray(n + 1) { Int.MAX_VALUE }
        distance[i] = 0
        val queue: Queue<Int> = LinkedList()
        queue.add(i)
        while (queue.isNotEmpty()) {
            val node = queue.poll() ?: break
            if (visited[node]) continue
            visited[node] = true
            roads[node].forEach {
                val cost = distance[node] + 1
                if (cost < distance[it]) {
                    distance[it] = cost
                    queue.add(it)
                }
            }
        }
        if (distance[k] == Int.MAX_VALUE) return null
        return distance[k]
    }

    fun _플로이드(
        n: Int = 5, d: Array<IntArray> = arrayOf(
            intArrayOf(1, 2, 2),
            intArrayOf(1, 3, 3),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 5, 10),
            intArrayOf(2, 4, 2),
            intArrayOf(3, 4, 1),
            intArrayOf(3, 5, 1),
            intArrayOf(4, 5, 3),
            intArrayOf(3, 5, 10),
            intArrayOf(3, 1, 8),
            intArrayOf(1, 4, 2),
            intArrayOf(5, 1, 7),
            intArrayOf(3, 4, 2),
            intArrayOf(5, 2, 4)
        )
    ): String {
        val floyd = Array(n) { IntArray(n) { 100000 } }

        // 모든 노선을 입력
        // n^3
        // a -> b + b -> c , a -> c를 비교
        repeat(n) {
            floyd[it][it] = 0
        }
        d.forEach {
            val y = it[0] - 1
            val x = it[1] - 1
            val distance = it[2]
            floyd[y][x] = Math.min(distance, floyd[y][x])
        }
        println(floyd.joinToString("\n") { it -> it.joinToString("\t") { if (it == 100000) "${-1}" else "$it" } })
        drawLine()

        repeat(n) { i ->
            for (j in 0 until n) {
                if (i == j) continue
                for (k in 0 until n) {
                    if (i == k || j == k) continue
                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k])
                }
            }
        }
        println(floyd.joinToString("\n") { it -> it.joinToString("\t") { if (it == 100000) "${-1}" else "$it" } })
        return ""
    }

    fun _정확한순위(
        n: Int = 6, arr: Array<IntArray> = arrayOf(
            intArrayOf(1, 5),
            intArrayOf(3, 4),
            intArrayOf(4, 2),
            intArrayOf(4, 6),
            intArrayOf(5, 2),
            intArrayOf(5, 4)
        )
    ): Int {
        val floyd = Array(n + 1) { i -> IntArray(n + 1) { j -> if (i == j) 0 else -1 } }
        arr.forEach {
            floyd[it[0]][it[1]] = 1
        }

        // 자신보다 높은 순위를 가진 배열
        for (i in 1..n) {
            for (j in 0..n) {
                if (i == j) continue
                for (k in 0..n) {
                    if (i == k || j == k) continue
                    if (floyd[j][i] == -1 || floyd[i][k] == -1) continue
                    floyd[j][k] = Math.max(floyd[j][k], floyd[j][i] + floyd[i][k])
                }
            }
        }
        // 자신이 이긴 횟수 + 자신이 진 횟수를 더하여 n - 1 개가 나오면 정확한 순위를 구할 수 있음
        val result = IntArray(n + 1) { 0 }
        floyd.forEachIndexed { y, array ->
            array.forEachIndexed { x, v ->
                if (v > 0) {
                    result[y] = result[y] + 1
                    result[x] = result[x] + 1
                }
            }
        }
        return result.count { it == n - 1 }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun _화성탐사(
        cases: Array<Array<IntArray>> = arrayOf(
            arrayOf(
                intArrayOf(5, 5, 4),
                intArrayOf(3, 9, 1),
                intArrayOf(3, 2, 7)
            ),
            arrayOf(
                intArrayOf(3, 7, 2, 0, 1),
                intArrayOf(2, 8, 0, 9, 1),
                intArrayOf(1, 2, 1, 8, 1),
                intArrayOf(9, 8, 9, 2, 0),
                intArrayOf(3, 6, 5, 1, 5)
            ),
            arrayOf(
                intArrayOf(9, 0, 5, 1, 1, 5, 3),
                intArrayOf(4, 1, 2, 1, 6, 5, 3),
                intArrayOf(0, 7, 6, 1, 6, 8, 5),
                intArrayOf(1, 1, 7, 8, 3, 2, 3),
                intArrayOf(9, 4, 0, 7, 6, 4, 1),
                intArrayOf(5, 8, 3, 2, 4, 8, 3),
                intArrayOf(7, 4, 8, 4, 8, 3, 4)
            )
        )
    ): IntArray {
        val dx = intArrayOf(0, -1, 0, 1)
        val dy = intArrayOf(-1, 0, 1, 0)
        return cases.map { case ->
            val queue = PriorityQueue(NodeComparator())
            val length = case.size * case.size
            val costs = IntArray(length) { Int.MAX_VALUE }
            val visited = BooleanArray(length) { false }
            costs[0] = case[0][0]
            queue.add(Node(case[0][0], 0))
            while (queue.isNotEmpty()) {
                val node = queue.poll() ?: break
                if (visited[node.index]) continue
                visited[node.index] = true
                val y = node.index / case.size
                val x = node.index % case.size
                val min = (0 until 4).map {
                    val nextY = dy[it] + y
                    val nextX = dx[it] + x
                    val index = nextY + nextX
                    if (nextX in case.indices && nextY in case.indices) {
                        queue.add(Node(case[nextY][nextX], index))
                        case[nextY][nextX]
                    } else Int.MAX_VALUE
                }.minOrNull()
                min?.takeIf { it != Int.MAX_VALUE }?.let {
                    costs[node.index] = Math.min(costs[node.index], it + case[y][x])
                }
            }
            costs.last()
        }.toIntArray()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun _화성탐사2(
        cases: Array<Array<IntArray>> = arrayOf(
//            arrayOf(
//                intArrayOf(5, 5, 4),
//                intArrayOf(3, 9, 1),
//                intArrayOf(3, 2, 7)
//            ),
            arrayOf(
                intArrayOf(5, 8, 1),
                intArrayOf(4, 8, 1),
                intArrayOf(4, 7, 7)
            )
//            arrayOf(
//                intArrayOf(3, 7, 2, 0, 1),
//                intArrayOf(2, 8, 0, 9, 1),
//                intArrayOf(1, 2, 1, 8, 1),
//                intArrayOf(9, 8, 9, 2, 0),
//                intArrayOf(3, 6, 5, 1, 5)
//            ),
//            arrayOf(
//                intArrayOf(9, 0, 5, 1, 1, 5, 3),
//                intArrayOf(4, 1, 2, 1, 6, 5, 3),
//                intArrayOf(0, 7, 6, 1, 6, 8, 5),
//                intArrayOf(1, 1, 7, 8, 3, 2, 3),
//                intArrayOf(9, 4, 0, 7, 6, 4, 1),
//                intArrayOf(5, 8, 3, 2, 4, 8, 3),
//                intArrayOf(7, 4, 8, 4, 8, 3, 4)
//            )
        )
    ): IntArray {
        val dx = intArrayOf(0, -1, 0, 1)
        val dy = intArrayOf(-1, 0, 1, 0)
        return cases.map { case ->
            val n = case.size
            // 연결된 그래프 목록을 구함
            val graph = Array(n) { Array(n) { ArrayList<Node2>() } }
            case.forEachIndexed { y, arr ->
                arr.forEachIndexed { x, _ ->
                    repeat(4) {
                        val nextY = y + dy[it]
                        val nextX = x + dx[it]
                        if (nextY in case.indices && nextX in case.indices) {
                            graph[y][x].add(Node2(case[nextY][nextX], nextY, nextX))
                        }
                    }
                }
            }

            println(case.joinToString("\n") { it.joinToString("\t") })
            val distance = Array(n) { IntArray(n) { 100 } }
            distance[0][0] = case[0][0]
            val queue = PriorityQueue(Node2Comparator())
            val visited = Array(n) { BooleanArray(n) { false } }
            queue.add(Node2(case[0][0], 0, 0))
            while (queue.isNotEmpty()) {
                val node = queue.poll() ?: break
                val y = node.y
                val x = node.x
                if (visited[y][x]) continue
                visited[y][x] = true
                println("$y , $x 연결 도시 갱신")
                graph[y][x].forEach {
//                    if(distance[it.y][it.x] < distance[y][x])
                    queue.add(it)
                    val d1 = distance[y][x] + it.distance
                    val d2 = distance[it.y][it.x]
                    if (d1 < d2) {
                        distance[it.y][it.x] = d1
                        println("$y, $x -> ${it.y}, ${it.x} 갱신! $d1")
                        println(distance.joinToString("\n") { it.joinToString("\t") })
                        drawLine()
                    }
                }
//                val distances = graph[node.y][node.x].map { distance[it.y][it.x] }
//                distances.minOrNull()?.takeIf { it != Int.MAX_VALUE }?.let {
//                    distance[node.y][node.x] =
//                        Math.min(distance[node.y][node.x], it + case[node.y][node.x])
//                }
//                graph[node.y][node.x].forEach {
//                    queue.add(it)
//                }
            }
            distance[n - 1][n - 1]
        }.toIntArray()
    }
}

data class Node(val distance: Int, val index: Int)

data class Node2(val distance: Int, val y: Int, val x: Int)

class NodeComparator : Comparator<Node> {
    override fun compare(o1: Node, o2: Node): Int {
        return o1.distance - o2.distance
    }
}

class Node2Comparator : Comparator<Node2> {
    override fun compare(o1: Node2, o2: Node2): Int {
        return o1.distance - o2.distance
    }
}