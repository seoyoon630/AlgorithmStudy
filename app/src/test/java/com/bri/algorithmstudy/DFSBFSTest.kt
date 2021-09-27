package com.bri.algorithmstudy

import org.junit.Test

class DFSBFSTest {

    @Test
    fun main() {
//        assertThat({DFSBFSAlg.factorial()}, 120.toDouble(), "factorial")
//        assertThat({DFSBFSAlg._유클리드호제법()}, 6, "_유클리드호제법")
//        assertThat({DFSBFSAlg.dfs()}, Unit, "DFS")
//        assertThat({DFSBFSAlg.bfs()}, Unit, "BFS")
//        assertThat({ DFSBFSAlg._음료수얼려먹기() }, 3, "음료수얼려먹기")
//        assertThat({
//            DFSBFSAlg._음료수얼려먹기(
//                arrayOf(
//                    intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0),
//                    intArrayOf(1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0),
//                    intArrayOf(1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0),
//                    intArrayOf(1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0),
//                    intArrayOf(1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
//                    intArrayOf(1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0),
//                    intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1),
//                    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
//                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1),
//                    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
//                    intArrayOf(0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
//                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0),
//                    intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1),
//                    intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1),
//                    intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1),
//                )
//            )
//        }, 8, "음료수얼려먹기")
//        assertThat({ DFSBFSAlg._미로찾기()}, 10, "미로찾기")
//        assertThat({
//            DFSBFSAlg._미로찾기(
//                arrayOf(
//                    intArrayOf(1, 1, 0),
//                    intArrayOf(0, 1, 0),
//                    intArrayOf(0, 1, 1)
//                )
//            )
//        }, 5, "미로찾기")
//        assertThat({ DFSBFSAlg._특정거리의도시찾기() }, listOf(4), "특정거리의도시찾기")
//        assertThat({
//            DFSBFSAlg._특정거리의도시찾기(
//                4, 2, 1, arrayOf(
//                    intArrayOf(1, 2),
//                    intArrayOf(1, 3),
//                    intArrayOf(1, 4)
//                )
//            )
//        }, listOf(-1), "특정거리의도시찾기")
//        assertThat({
//            DFSBFSAlg._특정거리의도시찾기(
//                4, 1, 1, arrayOf(
//                    intArrayOf(1, 2),
//                    intArrayOf(1, 3),
//                    intArrayOf(2, 3),
//                    intArrayOf(2, 4)
//                )
//            )
//        }, listOf(2, 3), "특정거리의도시찾기")
//        assertThat({ DFSBFSAlg._연구소() }, 27, "연구소")
//        assertThat({ DFSBFSAlg._경쟁적전염() }, 3, "경쟁적전염")
//        assertThat({ DFSBFSAlg._경쟁적전염(s = 1, targetY = 2, targetX = 2) }, 0, "경쟁적전염")
//        assertThat({ DFSBFSAlg._괄호변환() }, "(()())()", "괄호변환")
//        assertThat({ DFSBFSAlg._괄호변환(")(") }, "()", "괄호변환")
//        assertThat({ DFSBFSAlg._괄호변환("()))((()") }, "()(())()", "괄호변환")
//        assertThat({ DFSBFSAlg._연산자끼워넣기() }, intArrayOf(30, 30), "연산자끼워넣기")
//        assertThat(
//            { DFSBFSAlg._연산자끼워넣기(intArrayOf(3, 4, 5), intArrayOf(1, 0, 1, 0)) },
//            intArrayOf(35, 17),
//            "연산자끼워넣기"
//        )
//        assertThat(
//            { DFSBFSAlg._연산자끼워넣기(intArrayOf(3, 4, 5, 6), intArrayOf(2, 0, 1, 0)) },
//            intArrayOf(72, 23),
//            "연산자끼워넣기"
//        )
//        assertThat(
//            { DFSBFSAlg._연산자끼워넣기(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(2, 1, 1, 1)) },
//            intArrayOf(54, -24),
//            "연산자끼워넣기"
//        )
//        assertThat({ DFSBFSAlg._감시피하기() }, "YES", "감시피하기")
//        assertThat({ DFSBFSAlg._감시피하기(4, arrayOf(
//            charArrayOf('S','S','S','T'),
//            charArrayOf('X','X','X','X'),
//            charArrayOf('X','X','X','X'),
//            charArrayOf('T','T','T','X'),
//        )) }, "NO", "감시피하기")
//        assertThat({ DFSBFSAlg._인구이동() }, 1, "인구이동")
//        assertThat(
//            { DFSBFSAlg._인구이동(2, 40, 50, arrayOf(intArrayOf(50, 30), intArrayOf(20, 40))) },
//            0, "인구이동"
//        )
//        assertThat(
//            { DFSBFSAlg._인구이동(2, 20, 50, arrayOf(intArrayOf(50, 30), intArrayOf(30, 40))) },
//            1, "인구이동"
//        )
//        assertThat(
//            {
//                DFSBFSAlg._인구이동(
//                    3,
//                    5,
//                    10,
//                    arrayOf(intArrayOf(10, 15, 20), intArrayOf(20, 30, 25), intArrayOf(40, 22, 10))
//                )
//            }, 2, "인구이동"
//        )
//        assertThat(
//            {
//                DFSBFSAlg._인구이동(
//                    4, 10, 50, arrayOf(
//                        intArrayOf(10, 100, 20, 90),
//                        intArrayOf(80, 100, 60, 70),
//                        intArrayOf(70, 20, 30, 40),
//                        intArrayOf(50, 20, 100, 10)
//                    )
//                )
//            }, 3, "인구이동"
//        )
    }
}