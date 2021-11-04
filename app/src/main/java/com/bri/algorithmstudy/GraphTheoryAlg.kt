@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object GraphTheoryAlg {
    /**
     * 1. 서로소 집합 자료구조 (= 합치기 찾기 자료구조)
     * - 서로소 부분 집합들로 나누어진 원소들의 데이터 처리를 위한 자료 구조
     * - 합집합, 찹기 연산 지원
     * - 루트 노드에 즉시 접근 불가능
     * 합치기연산 : 큰 노드의 부모를 작은 노드로 설정하며 합쳐나감.
     * 2. 사이클 판별
     * - 무방향 그래프 내에서 사이클을 판별하기 위해 서로소 집합 알고리즘을 사용
     * (방향 그래프에서는 DFS를 통해 사이클 여부 판별)
     * - 각 간선을 하나씩 확인하며 두 노드의 루트 노드를 확인
     * - 루트 노드가 서로 다르면 두 노드에 대하여 합집합 연산을 수행
     * - 루트 노드가 같다면 사이클이 발생
     * 3. 최소신장트리
     * - 그래프에서 모든 노드를 포함하면서 사이클이 존재하지 않는 부분 그래프
     * -> 최소한의 비용으로 구성되는 신장 트리를 찾는 문제
     * = N개의 도시가 존재하는 상황에서 두 도시 사이에 다리를 놓아 전체 도시가 연결될 수 있도록 도로를 설치하는 문제
     * - 간선의 개수는 (N - 1)개
     * 4. 크루스칼 알고리즘
     * - 대표적인 최소 신장 트리 알고리즘
     * - 그리디 알고리즘
     * - 동작 과정
     *  1. 간선 데이터를 비용에 따라 오름차순으로 정렬
     *  2. 간선을 하나씩 확인하며 현재의 간선이 사이클을 발생시키는지 확인
     *   2-1. 사이클이 발생하지 않으면 최소 신장 트리에 포함
     *   2-2. 사이클이 발생하면 최소 신장 트리에서 제외
     *  3. 모든 간선에 대해 2번의 과정을 반복
     *  5. 위상정렬
     *  - 사이클이 없는 방향 그래프(DAG)의 모든 노드를 방향성에 거스르지 않도록 순서대로 정렬
     *  - 모든 원소를 방문하기 전에 큐가 빈다면 사이클이 존재하는 것
     *  ex)  선수 과목을 고려한 학습 순서 설정
     *  - 진입차수(Indegree)  : 특정한 노드로 들어오는 간선의 개수
     *  - 진출차수(Outdegree) : 특정한 노드에서 나가는 간선의 개수
     *  - 동작 과정
     *   1. 진입차수가 0인 모든 노드를 큐에 넣는다
     *   2. 큐가 빌 때까지 다음의 과정을 반복한다.
     *    2-1. 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거
     *    2-2. 새롭게 진입차수가 0이 된 노드를 큐에 넣는다.
     */

    private fun findParent(parent: IntArray, x: Int): Int {
        if (parent[x] != x) {
            parent[x] = findParent(parent, parent[x])
        }
        return parent[x]
    }

    private fun unionParent(parent: IntArray, a: Int, b: Int) {
        val aParent = findParent(parent, a)
        val bParent = findParent(parent, b)
        if (aParent < bParent) parent[bParent] = aParent
        else parent[aParent] = bParent
    }

    fun _서로소집합자료구조(n: Int, pairs: List<IntArray>) {
        val parent = IntArray(n + 1) { it }
        pairs.forEach { unionParent(parent, it[0], it[1]) }

        // 집합 찾기
        val groups = HashMap<Int, ArrayList<Int>>()
        for (i in 1..n) {
            groups[parent[i]]?.add(i) ?: run { groups[parent[i]] = arrayListOf(i) }
        }
        groups.forEach { (root, set) -> println("$root {${set.joinToString()}}") }
    }

    fun _서로소집합을활용한사이클판별(n: Int, pairs: List<IntArray>): Boolean {
        val parent = IntArray(n + 1) { it }
        pairs.forEach {
            if (findParent(parent, it[0]) == findParent(parent, it[1])) {
                return true
            } else unionParent(parent, it[0], it[1])
        }

        return false
    }

    fun _크루스칼알고리즘(
        n: Int = 7,
        pairs: List<IntArray> = listOf(
            intArrayOf(1, 2, 29),
            intArrayOf(1, 5, 75),
            intArrayOf(2, 3, 35),
            intArrayOf(2, 6, 34),
            intArrayOf(3, 4, 7),
            intArrayOf(4, 6, 23),
            intArrayOf(4, 7, 13),
            intArrayOf(5, 6, 53),
            intArrayOf(6, 7, 25)
        )
    ): Int {
        var answer = 0
        val result = ArrayList<IntArray?>()
        // 오름차순으로 정렬
        val sorted = pairs.sortedWith { o1, o2 -> o1[2] - o2[2] }

        // 사이클이 발생하는지 확인
        val parent = IntArray(n + 1) { it }
        sorted.forEach {
            val a = it[0]
            val b = it[1]
            val distance = it[2]
            // 사이클 발생 X
            if (findParent(parent, a) != findParent(parent, b)) {
                println("$a ---- $b 성공")
                result.add(intArrayOf(a, b))
                unionParent(parent, a, b)
                answer += distance
            } else {
                println("$a ---- $b 실패")
            }
        }

        println("연결된 간선 ${result.size}개")
        println(result.joinToString(" | ") { it?.joinToString() ?: "" })
        return answer
    }

    fun _위상정렬(n: Int, graph: Array<IntArray>): IntArray {
        val answer = IntArray(n) { 0 }
        val map = HashMap<Int, ArrayList<Int>>()
        val indegrees = IntArray(n + 1) { 0 }
        val queue: Queue<Int> = LinkedList()

        // 초기화
        repeat(n + 1) { map[it] = ArrayList() }
        // 간선 정보 입력
        graph.forEach {
            val a = it[0]
            val b = it[1]
            indegrees[b]++
            map[a]?.add(b)
        }
        // 진입차수가 0이면 queue에 추가
        for (i in 1..n) {
            if (indegrees[i] == 0) queue.offer(i)
        }

        var index = 0
        while (queue.isNotEmpty()) {
            val next = queue.poll() ?: break
            answer[index++] = next
            map[next]?.forEach {
                // 진입차수 1 빼기
                indegrees[it]--
                // 진입차수 == 0 이면 queue에 추가
                if (indegrees[it] == 0) queue.offer(it)
            }
        }
        return answer
    }

    fun _팀결성(n: Int, graph: Array<IntArray>): String {
        // 0 이면 합치기
        // 1이면 같은지 여부 확인
        val answer = ArrayList<Boolean>()
        val parent = IntArray(n + 1) { it }
        graph.forEach {
            val operator = it[0]
            val a = it[1]
            val b = it[2]
            if (operator == 0) {
                unionParent2(parent, a, b)
            } else {
                if (findParent2(parent, a) == findParent2(parent, b)) answer.add(true)
                else answer.add(false)
            }
        }
        return answer.joinToString("\n") { if (it) "YES" else "NO" }
    }

    private fun findParent2(parent: IntArray, a: Int): Int {
        if (a != parent[a]) parent[a] = findParent2(parent, parent[a])
        return parent[a]
    }

    private fun unionParent2(parent: IntArray, a: Int, b: Int) {
        val aParent = findParent2(parent, a)
        val bParent = findParent2(parent, b)
        if (aParent < bParent) parent[bParent] = aParent
        else parent[aParent] = bParent
    }

    fun _도시분할계획(n: Int, graph: Array<IntArray>): Int {
        val parent = IntArray(n + 1) { it }
        // 제일 비용이 큰 도로를 삭제하여 도시를 분할
        var max = 0
        var answer = 0
        graph.sortBy { it[2] }

        graph.forEach {
            val a = it[0]
            val b = it[1]
            val cost = it[2]
            // 최소신장트리 찾기 (크루스칼)
            if (findParent(parent, a) != findParent(parent, b)) {
                unionParent(parent, a, b)
                if (max < cost) max = cost
                answer += cost
            }
        }
        return answer - max
    }
}