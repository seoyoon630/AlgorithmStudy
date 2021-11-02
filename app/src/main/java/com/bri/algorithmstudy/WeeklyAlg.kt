package com.bri.algorithmstudy

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object WeeklyAlg {

    fun weekly3(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val blanks = findPiece(game_board, 0)
        val pieces = findPiece(table, 1)
        blanks.forEach { (key, value) ->
            println("$key")
            value.forEach { println(it.joinToString("\t") { it.joinToString() }) }
            drawLine()
        }
        drawLine()
        pieces.forEach { (key, value) ->
            println("$key")
            value.forEach { println(it.joinToString("\t") { it.joinToString() }) }
            drawLine()
        }

        var answer = 0
        blanks.forEach { (size, blanks) ->

        }
        return answer
    }

    private fun findPiece(
        board: Array<IntArray>,
        target: Int
    ): HashMap<Int, ArrayList<ArrayList<IntArray>>> {
        val dy = listOf(-1, 0, 1, 0)
        val dx = listOf(0, -1, 0, 1)
        val result = HashMap<Int, ArrayList<ArrayList<IntArray>>>()
        val visited = Array(board.size) { BooleanArray(board.size) { false } }
        val queue: Queue<IntArray> = LinkedList()
        for (y in board.indices) {
            for (x in board.indices) {
                if (!visited[y][x] && board[y][x] == target) queue.add(intArrayOf(y, x))
                else continue
                val piece = ArrayList<IntArray>()
                while (queue.isNotEmpty()) {
                    val next = queue.poll() ?: break
                    val y = next[0]
                    val x = next[1]
                    if (visited[y][x]) continue
                    visited[y][x] = true
                    piece.add(next)
                    repeat(4) {
                        val nextY = y + dy[it]
                        val nextX = x + dx[it]
                        if (nextY in board.indices && nextX in board.indices
                            && !visited[nextY][nextX] && board[nextY][nextX] == target
                        ) {
                            queue.add(intArrayOf(nextY, nextX))
                        }
                    }
                }
                val size = piece.size
                result[size]?.add(piece) ?: run { result[size] = arrayListOf(piece) }
            }
        }
        return result
    }
}