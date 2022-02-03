package com.bri.algorithmstudy

import org.junit.Test

class Kakao2022Test {
    @Test
    fun main() {
//        assertThat({
//            Kakao2022.신고결과받기(
//                arrayOf("muzi", "frodo", "apeach", "neo"),
//                arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
//                2
//            )
//        }, intArrayOf(2, 1, 1, 0), "신고결과받기")
//        assertThat({
//            Kakao2022.신고결과받기(
//                arrayOf("con", "ryan"),
//                arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
//                3
//            )
//        }, intArrayOf(0, 0), "신고결과받기")
        assertThat({
            Kakao2022.신고결과받기2(
                arrayOf("muzi", "frodo", "apeach", "neo"),
                arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
                2
            )
        }, intArrayOf(2, 1, 1, 0), "신고결과받기2")
        assertThat({
            Kakao2022.신고결과받기2(
                arrayOf("con", "ryan"),
                arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
                3
            )
        }, intArrayOf(0, 0), "신고결과받기2")
    }
}