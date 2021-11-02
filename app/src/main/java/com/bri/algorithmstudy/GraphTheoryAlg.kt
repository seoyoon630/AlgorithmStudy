@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")

package com.bri.algorithmstudy

object GraphTheoryAlg {
    /**
     * 1. 서로소 집합 자료구조 (= 합치기 찾기 자료구조)
     * - 서로소 부분 집합들로 나누어진 원소들의 데이터 처리를 위한 자료 구조
     * - 합집합, 찹기 연산 지원
     * - 루트 노드에 즉시 접근 불가능
     * 합치기연산 : 큰 노드의 부모를 작은 노드로 설정하며 합쳐나감.
     */

    private fun findParent(parent: IntArray, x: Int): Int {
        return if (parent[x] != x) findParent(parent, parent[x])
        else x
    }

    private fun unionParent(parent: IntArray, a: Int, b: Int) {
        val aParent = findParent(parent, a)
        val bParent = findParent(parent, b)
        if (aParent < bParent) parent[b] = aParent
        else parent[a] = bParent
    }

    fun _서로소집합자료구조(n: Int, pairs: List<IntArray>) {
        val parent = IntArray(n + 1) { it }
        pairs.forEach { unionParent(parent, it[0], it[1]) }
        println(parent.joinToString())

        // 집합 찾기
        val groups = HashMap<Int, ArrayList<Int>>()
        for (i in 1..n) {
            val root = findParent(parent, i)
            groups[root]?.add(i) ?: run { groups[root] = arrayListOf(i) }
        }

        groups.forEach { (root, set) ->
            println("$root {${set.joinToString()}}")
        }
    }
}