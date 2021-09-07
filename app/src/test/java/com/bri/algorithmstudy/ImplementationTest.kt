package com.bri.algorithmstudy

import org.junit.Test

class ImplementationTest {
    @Test
    fun main() {
//        ImplementationAlg._상하좌우()
//        ImplementationAlg._상하좌우(3, "R R R R")
        assertThat({ ImplementationAlg._시각() }, 11475, "_시각")
        assertThat({ ImplementationAlg._시각완전탐색() }, 11475, "_시각완전탐색")
    }
}