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
//        assertThat({
//            Kakao2022.신고결과받기2(
//                arrayOf("muzi", "frodo", "apeach", "neo"),
//                arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
//                2
//            )
//        }, intArrayOf(2, 1, 1, 0), "신고결과받기2")
//        assertThat({
//            Kakao2022.신고결과받기2(
//                arrayOf("con", "ryan"),
//                arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
//                3
//            )
//        }, intArrayOf(0, 0), "신고결과받기2")
//        assertThat({ Kakao2022.k진수에서_소수개수구하기(437674, 3) }, 3, "k진수에서_소수개수구하기")
//        assertThat({ Kakao2022.k진수에서_소수개수구하기(110011, 10) }, 2, "k진수에서_소수개수구하기")
        assertThat({
            Kakao2022.주차요금계산(
                intArrayOf(180, 5000, 10, 600),
                arrayOf(
                    "05:34 5961 IN",
                    "06:00 0000 IN",
                    "06:34 0000 OUT",
                    "07:59 5961 OUT",
                    "07:59 0148 IN",
                    "18:59 0000 IN",
                    "19:09 0148 OUT",
                    "22:59 5961 IN",
                    "23:00 5961 OUT"
                )
            )
        }, intArrayOf(14600, 34400, 5000), "주차 요금 계산")
//        assertThat({
//            Kakao2022.주차요금계산(
//                intArrayOf(120, 0, 60, 591),
//                arrayOf(
//                    "16:00 3961 IN",
//                    "16:00 0202 IN",
//                    "18:00 3961 OUT",
//                    "18:00 0202 OUT",
//                    "23:58 3961 IN"
//                )
//            )
//        }, intArrayOf(0, 591), "주차 요금 계산")
//        assertThat({
//            Kakao2022.주차요금계산(
//                intArrayOf(1, 461, 1, 10),
//                arrayOf("00:00 1234 IN")
//            )
//        }, intArrayOf(14841), "주차 요금 계산")
    }
}