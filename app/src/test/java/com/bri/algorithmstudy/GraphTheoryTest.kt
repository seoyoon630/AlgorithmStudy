@file:Suppress("FunctionName", "NonAsciiCharacters")

package com.bri.algorithmstudy

import org.junit.Test
import kotlin.random.Random

class GraphTheoryTest {
    @Test
    fun main() {
//        GraphTheoryAlg._서로소집합자료구조(6, listOf(
//            intArrayOf(1,4),
//            intArrayOf(2,3),
//            intArrayOf(2,4),
//            intArrayOf(5,6),
//        ))
//        val n = 10
//        val v = List(n - 1) { intArrayOf(it + 1, it + 2) }
//        assertThat({ GraphTheoryAlg._서로소집합자료구조(n, v) }, Unit, "서로소집합자료구조")
//        assertThat({ GraphTheoryAlg._개선된서로소집합자료구조(n, v) }, Unit, "개선된서로소집합자료구조")
//        assertThat({
//            GraphTheoryAlg._서로소집합을활용한사이클판별(
//                3,
//                listOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(1, 3))
//            )
//        }, true, "서로소집합을활용한사이클판별")
//        assertThat({ GraphTheoryAlg._크루스칼알고리즘() }, 159, "크루스칼알고리즘")
//        assertThat({
//            GraphTheoryAlg._위상정렬(
//                7, arrayOf(
//                    intArrayOf(1, 2),
//                    intArrayOf(1, 5),
//                    intArrayOf(2, 3),
//                    intArrayOf(2, 6),
//                    intArrayOf(3, 4),
//                    intArrayOf(4, 7),
//                    intArrayOf(5, 6),
//                    intArrayOf(5, 4)
//                )
//            )
//        }, intArrayOf(1, 2, 5, 3, 6, 4, 7), "위상정렬")
//        assertThat({
//            GraphTheoryAlg._팀결성(
//                7, arrayOf(
//                    intArrayOf(0, 1, 3),
//                    intArrayOf(1, 1, 7),
//                    intArrayOf(0, 7, 6),
//                    intArrayOf(1, 7, 1),
//                    intArrayOf(0, 3, 7),
//                    intArrayOf(0, 4, 2),
//                    intArrayOf(0, 1, 1),
//                    intArrayOf(1, 1, 1)
//                )
//            )
//        }, "NO\nNO\nYES", "팀결성")
//        assertThat({
//            GraphTheoryAlg._팀결성(
//                7, arrayOf(
//                    intArrayOf(0, 1, 3),
//                    intArrayOf(1, 1, 7),
//                    intArrayOf(0, 7, 6),
//                    intArrayOf(0, 3, 7),
//                    intArrayOf(1, 7, 1),
//                    intArrayOf(0, 4, 2),
//                    intArrayOf(0, 1, 1),
//                    intArrayOf(1, 1, 1)
//                )
//            )
//        }, "NO\nYES\nYES", "팀결성")
//        assertThat({
//            GraphTheoryAlg._도시분할계획(
//                7, arrayOf(
//                    intArrayOf(1, 2, 3),
//                    intArrayOf(1, 3, 2),
//                    intArrayOf(3, 2, 1),
//                    intArrayOf(2, 5, 2),
//                    intArrayOf(3, 4, 4),
//                    intArrayOf(7, 3, 6),
//                    intArrayOf(5, 1, 5),
//                    intArrayOf(1, 6, 2),
//                    intArrayOf(6, 4, 1),
//                    intArrayOf(6, 5, 3),
//                    intArrayOf(4, 5, 3),
//                    intArrayOf(6, 7, 4),
//                )
//            )
//        }, 8, "도시분할계획")
//        assertThat({
//            GraphTheoryAlg._커리큘럼(
//                5, arrayOf(
//                    arrayListOf(10),
//                    arrayListOf(10, 1),
//                    arrayListOf(4, 1),
//                    arrayListOf(4, 3, 1),
//                    arrayListOf(3, 3),
//                )
//            )
//        }, intArrayOf(10, 20, 14, 18, 17), "커리큘럼")
//        assertThat({
//            GraphTheoryAlg._여행계획(
//                5, arrayOf(
//                    intArrayOf(1, 2),
//                    intArrayOf(1, 4),
//                    intArrayOf(1, 5),
//                    intArrayOf(2, 3),
//                    intArrayOf(2, 4)
//                ), intArrayOf(2, 3, 4, 3)
//            )
//        }, "YES", "여행계획")
//        assertThat({ GraphTheoryAlg._탑승구(4, intArrayOf(4, 1, 1)) }, 2, "탑승구")
//        assertThat({ GraphTheoryAlg._탑승구(4, intArrayOf(2, 2, 3, 3, 4, 4)) }, 3, "탑승구")
//        assertThat({
//            GraphTheoryAlg._어두운길(
//                7, arrayOf(
//                    intArrayOf(0, 1, 7),
//                    intArrayOf(0, 3, 5),
//                    intArrayOf(1, 2, 8),
//                    intArrayOf(1, 3, 9),
//                    intArrayOf(1, 4, 7),
//                    intArrayOf(2, 4, 5),
//                    intArrayOf(3, 4, 15),
//                    intArrayOf(3, 5, 6),
//                    intArrayOf(4, 5, 8),
//                    intArrayOf(4, 6, 9),
//                    intArrayOf(5, 6, 11)
//                )
//            )
//        }, 51, "어두운길")
//        assertThat({
//            GraphTheoryAlg._행성터널(
//                35, arrayOf(
//                    intArrayOf(11, -15, -15),
//                    intArrayOf(14, -5, -15),
//                    intArrayOf(-1, -1, -5),
//                    intArrayOf(10, -4, -1),
//                    intArrayOf(19, -4, 19)
//                )
//            )
////        }, 4, "행성터널")
//        }, Unit, "행성터널")
//        val max = 100
//        val testData = Array(max) { createTestData(3, 10000000, -10000000) }
//        assertThat({ GraphTheoryAlg._행성터널(max, testData) }, Unit, "행성터널")
        assertThat(
            {
                GraphTheoryAlg._최종순위(
                    3,
                    intArrayOf(5, 3, 4),
                    arrayOf(intArrayOf(5, 4, 3, 2, 1), intArrayOf(2, 3, 1), intArrayOf(1, 2, 3, 4)),
                    arrayOf(
                        arrayOf(intArrayOf(2, 4), intArrayOf(3, 4)),
                        arrayOf(),
                        arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(2, 3))
                    )
                )
            }, """5 3 2 4 1
                |2 3 1
                |IMPOSSIBLE
            """.trimMargin(), "최종순위"
        )
    }

    fun createTestData(n: Int, max: Int, min: Int = 0): IntArray {
        val answer = IntArray(n) { Random.nextInt(min, max) }
        return answer
    }
}