package com.bri.algorithmstudy

import org.junit.Test

class GreedyTest {
    @Test
    fun main() {
//        GreedyAlg._거스름돈()
//        assert(GreedyAlg._1이될때까지() == 2)
//        assert(GreedyAlg._1이될때까지(37, 6) == 3)
//        assert(GreedyAlg._1이될때까지(2, 2) == 1)
        assert(GreedyAlg._곱하기혹은더하기() == 576)
        assert(GreedyAlg._곱하기혹은더하기("567") == 210)
        assert(GreedyAlg._곱하기혹은더하기("5671") == 211)
    }
}