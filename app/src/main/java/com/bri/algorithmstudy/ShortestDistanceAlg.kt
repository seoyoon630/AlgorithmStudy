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
        val result = Array(n + 1) { Int.MAX_VALUE }
        val visited = Array(n + 1) { false }

        println(d.joinToString("\n") { it.joinToString() })
        // 시작점 설정
        result[from] = 0
        queue.add(Node(result[from], from))

        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: break
            if (visited[current.to]) continue
            visited[current.to] = true
            d[current.to].forEach {
                val d1 = it.distance + result[current.to]
                val d2 = result[it.to]
                if (d1 < d2) {
                    queue.add(it)
                    result[it.to] = d1
                }
            }
        }
        println(result.joinToString())
        return result[to]
    }
}

data class Node(val distance: Int, val to: Int)

class NodeComparator : Comparator<Node> {
    override fun compare(o1: Node, o2: Node): Int {
        return o1.distance - o2.distance
    }
}