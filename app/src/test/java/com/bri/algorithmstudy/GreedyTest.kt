package com.bri.algorithmstudy

import org.junit.Test

class GreedyTest {
    @Test
    fun main() {
//        GreedyAlg._거스름돈()
//        assert(GreedyAlg._1이될때까지() == 2)
//        assert(GreedyAlg._1이될때까지(37, 6) == 3)
//        assert(GreedyAlg._1이될때까지(2, 2) == 1)
//        assert(GreedyAlg._곱하기혹은더하기() == 576)
//        assert(GreedyAlg._곱하기혹은더하기("567") == 210)
//        assert(GreedyAlg._곱하기혹은더하기("5671") == 211)
//        assert(GreedyAlg._모험가길드() == 2)
//        assertThat({ GreedyAlg._큰수의법칙() },46,"_큰수의법칙")
//        assertThat({ GreedyAlg._큰수의법칙(5, 7,2, intArrayOf(3,4,3,4,3)) },28,"_큰수의법칙")
        assertThat({
            GreedyAlg._숫자카드게임(
                intArrayOf(3, 1, 2),
                intArrayOf(4, 1, 4),
                intArrayOf(2, 2, 2)
            )
        }, 2, "_숫자카드게임")
        assertThat(
            { GreedyAlg._숫자카드게임(intArrayOf(7, 3, 1, 8), intArrayOf(3, 3, 3, 4)) },
            3,
            "_숫자카드게임"
        )
    }
}