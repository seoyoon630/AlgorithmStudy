package com.bri.algorithmstudy

object CombinationAlg {
    private val result = ArrayList<IntArray>()
    private lateinit var current: IntArray
    private lateinit var visited: BooleanArray
    private var size: Int = 0

    fun combination(arr: IntArray, r: Int): ArrayList<IntArray> {
        result.clear()
        size = r
        current = IntArray(r) { -1 }
        visited = BooleanArray(arr.size) { false }
        _combination(arr, visited, 0, r)
        println("=================================================")
        println("${arr.size}에서 ${r}개를 뽑는 경우의 수 = ${result.size}개")
        println(result.joinToString("\t") { it.joinToString() })
        return result
    }

    private fun _combination(arr: IntArray, visited: BooleanArray, start: Int, r: Int) {
        if (r == 0) {
            result.add(visited.indices.filter { visited[it] }.map { arr[it] }.toIntArray())
            return
        } else {
            for (i in start until arr.size) {
                visited[i] = true
                _combination(arr, visited, i + 1, r - 1)
                visited[i] = false
            }
        }
    }

//    private fun _combination(num: IntArray, count: BooleanArray) {
//        if (num == 0) {
//            return
//        }
//        if (current.count { it > -1 } == current.size) {
//            result.add(current)
//            println(current.joinToString())
//        } else {
//            _combination(num - 1, count)
//            current[size - count] = num
//            _combination(num - 1, count - 1)
//        }
//    }

}