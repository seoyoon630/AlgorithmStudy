package com.bri.algorithmstudy

import org.junit.Test

class LineTest {
    @Test
    fun main() {
//        assertThat({
//            LineQuestions._프로그래밍1(
//                arrayOf(
//                    "P 300 6",
//                    "P 500 3",
//                    "S 1000 4",
//                    "P 600 2",
//                    "S 1200 1"
//                ))
//        }, intArrayOf(1500, 2400), "프로그래밍1")
//        assertThat({
//            LineQuestions._프로그래밍1(
//                arrayOf(
//                    "P 300 6",
//                    "P 500 3",
//                    "S 1000 4",
//                    "P 600 1",
//                    "S 1200 2"
//                ))
//        }, intArrayOf(1800, 2700), "프로그래밍1")
//        assertThat({
//            LineQuestions._프로그래밍1(
//                arrayOf(
//                    "P 100 4",
//                    "P 300 9",
//                    "S 1000 7",
//                    "P 1000 8",
//                    "S 700 7",
//                    "S 700 3"
//                ))
//        }, intArrayOf(7100, 10700), "프로그래밍1")

//        assertThat({ LineQuestions._프로그래밍2(intArrayOf(0, 1, 2, 5, 3, 7)) }, 3, "프로그래밍2")
//        assertThat({ LineQuestions._프로그래밍2(intArrayOf(1, 2, 3, 2, 1)) }, 4, "프로그래밍2")
//        assertThat(
//            { LineQuestions._프로그래밍2(intArrayOf(1, 2, 3, 3, 2, 1, 4, 3, 2, 2, 1)) },
//            2,
//            "프로그래밍2"
//        )
//        assertThat({ LineQuestions._프로그래밍2(intArrayOf(1, 2, 1, 2, 1)) }, 2, "프로그래밍2")
//        assertThat({ LineQuestions._프로그래밍2(intArrayOf(1, 2, 1, 2, 1)) }, 2, "프로그래밍2")

//        assertThat({ LineQuestions._프로그래밍3(5, 12) }, intArrayOf(4, 4), "프로그래밍3")
//        assertThat({ LineQuestions._프로그래밍3(5, 16) }, intArrayOf(1, 2), "프로그래밍3")
//        assertThat({ LineQuestions._프로그래밍3(6, 13) }, intArrayOf(4, 5), "프로그래밍3")

        assertThat(
            {
                LineQuestions._프로그래밍4(
                    arrayOf(
                        intArrayOf(0, 2, 1, 3),
                        intArrayOf(1, 2, 2, 5),
                        intArrayOf(3, 3, 4, 4),
                        intArrayOf(4, 1, 6, 3),
                        intArrayOf(1, 6, 5, 7),
                        intArrayOf(5, 5, 7, 6),
                        intArrayOf(5, 8, 6, 10)
                    )
                ).joinToString()
            },
            arrayOf(
                "0 0 1 1",
                "1 0 2 3",
                "2 0 3 1",
                "3 0 5 2",
                "0 3 4 4",
                "2 2 4 3",
                "4 3 5 5"
            ).joinToString(),
            "프로그래밍4"
        )
    }
}