package com.bri.algorithmstudy

import org.junit.Test

class WeeklyTest {
    @Test
    fun main() {
        assertThat({
            WeeklyAlg.weekly3(
                arrayOf(
                    intArrayOf(1, 1, 0, 0, 1, 0),
                    intArrayOf(0, 0, 1, 0, 1, 0),
                    intArrayOf(0, 1, 1, 0, 0, 1),
                    intArrayOf(1, 1, 0, 1, 1, 1),
                    intArrayOf(1, 0, 0, 0, 1, 0),
                    intArrayOf(0, 1, 1, 1, 0, 0)
                ),
                arrayOf(
                    intArrayOf(1, 0, 0, 1, 1, 0),
                    intArrayOf(1, 0, 1, 0, 1, 0),
                    intArrayOf(0, 1, 1, 0, 1, 1),
                    intArrayOf(0, 0, 1, 0, 0, 0),
                    intArrayOf(1, 1, 0, 1, 1, 0),
                    intArrayOf(0, 1, 0, 0, 0, 0)
                )
            )
        }, 14, "weekly3")
//        assertThat({
//            WeeklyAlg.weekly3(
//                arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 0), intArrayOf(1, 1, 1)),
//                arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 0), intArrayOf(0, 0, 0))
//            )
//        }, 0, "weekly3")
    }
}