package com.bri.algorithmstudy

import org.junit.Test

class DFSBFSTest {

    @Test
    fun main(){
//        assertThat({DFSBFSAlg.factorial()}, 120.toDouble(), "factorial")
//        assertThat({DFSBFSAlg._유클리드호제법()}, 6, "_유클리드호제법")
        assertThat({DFSBFSAlg.dfs()}, Unit, "DFS")
        assertThat({DFSBFSAlg.bfs()}, Unit, "BFS")
    }
}