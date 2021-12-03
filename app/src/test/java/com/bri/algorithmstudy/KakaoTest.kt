package com.bri.algorithmstudy

import org.junit.Test

class KakaoTest {
    @Test
    fun main() {
//        assertThat({
//            KakaoQuestions._순위검색(
//                arrayOf(
//                    "java backend junior pizza 150",
//                    "python frontend senior chicken 210",
//                    "python frontend senior chicken 150",
//                    "cpp backend senior pizza 260",
//                    "java backend junior chicken 80",
//                    "python backend senior chicken 50"
//                ), arrayOf(
//                    "java and backend and junior and pizza 100",
//                    "python and frontend and senior and chicken 200",
//                    "cpp and - and senior and pizza 250",
//                    "- and backend and senior and - 150",
//                    "- and - and - and chicken 100",
//                    "- and - and - and - 150"
//                )
//            )
//        }, intArrayOf(1, 1, 1, 1, 2, 4), "순위검색")
//
//        assertThat(
//            {
//                KakaoQuestions._택시합승요금(
//                    6, 4, 6, 2,
//                    arrayOf(
//                        intArrayOf(4, 1, 10),
//                        intArrayOf(3, 5, 24),
//                        intArrayOf(5, 6, 2),
//                        intArrayOf(3, 1, 41),
//                        intArrayOf(5, 1, 24),
//                        intArrayOf(4, 6, 50),
//                        intArrayOf(2, 4, 66),
//                        intArrayOf(2, 3, 22),
//                        intArrayOf(1, 6, 25)
//                    )
//                )
//            }, 82, "합승택시요금"
//        )
//        assertThat(
//            {
//                KakaoQuestions._택시합승요금(
//                    7, 3, 4, 1,
//                    arrayOf(
//                        intArrayOf(5, 7, 9),
//                        intArrayOf(4, 6, 4),
//                        intArrayOf(3, 6, 1),
//                        intArrayOf(3, 2, 3),
//                        intArrayOf(2, 1, 6)
//                    )
//                )
//            }, 14, "합승택시요금"
//        )
//        assertThat(
//            {
//                KakaoQuestions._택시합승요금(
//                    6, 4, 5, 6,
//                    arrayOf(
//                        intArrayOf(2, 6, 6),
//                        intArrayOf(6, 3, 7),
//                        intArrayOf(4, 6, 7),
//                        intArrayOf(6, 5, 11),
//                        intArrayOf(2, 5, 12),
//                        intArrayOf(5, 3, 20),
//                        intArrayOf(2, 4, 8),
//                        intArrayOf(4, 3, 9)
//                    )
//                )
//            }, 18, "합승택시요금"
//        )
//        assertThat(
//            {
//                KakaoQuestions._광고삽입(
//                    "00:00:10", "00:00:02",
//                    arrayOf(
//                        "00:00:01-00:00:03",
//                        "00:00:01-00:00:07",
//                        "00:00:04-00:00:06",
//                        "00:00:04-00:00:09",
//                    )
//                )
//            }, "00:00:04", "광고삽입"
//        )
//        assertThat(
//            {
//                KakaoQuestions._광고삽입(
//                    "02:03:55", "00:14:15",
//                    arrayOf(
//                        "01:20:15-01:45:14",
//                        "00:40:31-01:00:00",
//                        "00:25:50-00:48:29",
//                        "01:30:59-01:53:29",
//                        "01:37:44-02:02:30"
//                    )
//                )
//            }, "01:30:59", "광고삽입"
//        )
//        assertThat(
//            {
//                KakaoQuestions._광고삽입(
//                    "99:59:59", "25:00:00",
//                    arrayOf(
//                        "69:59:59-89:59:59",
//                        "01:00:00-21:00:00",
//                        "79:59:59-99:59:59",
//                        "11:00:00-31:00:00"
//                    )
//                )
//            }, "01:00:00", "광고삽입"
//        )
//        assertThat(
//            {
//                KakaoQuestions._광고삽입(
//                    "50:00:00", "50:00:00",
//                    arrayOf(
//                        "15:36:51-38:21:49",
//                        "10:14:18-15:36:51",
//                        "38:21:49-42:51:45"
//                    )
//                )
//            }, "00:00:00", "광고삽입"
//        )
//        assertThat({
//            KakaoQuestions._카드짝맞추기(
//                arrayOf(
//                    intArrayOf(1, 0, 0, 0),
//                    intArrayOf(2, 0, 0, 0),
//                    intArrayOf(0, 0, 0, 2),
//                    intArrayOf(0, 0, 1, 0)
//                ), 1, 0
//            )
//        }, 10, "카드짝맞추기")
//        assertThat({
//            KakaoQuestions._카드짝맞추기2(
//                arrayOf(
//                    intArrayOf(1, 0, 0, 3),
//                    intArrayOf(2, 0, 0, 0),
//                    intArrayOf(0, 0, 0, 2),
//                    intArrayOf(3, 0, 1, 0)
//                ), 1, 0
//            )
//        }, 14, "카드짝맞추기2")
//        assertThat({
//            KakaoQuestions._카드짝맞추기2(
//                arrayOf(
//                    intArrayOf(1, 4, 4, 3),
//                    intArrayOf(2, 5, 5, 6),
//                    intArrayOf(0, 7, 6, 2),
//                    intArrayOf(3, 7, 1, 0)
//                ), 1, 0
//            )
//        }, 14, "카드짝맞추기2")
//        assertThat({
//            KakaoQuestions._카드짝맞추기(
//                arrayOf(
//                    intArrayOf(1, 0, 0, 3),
//                    intArrayOf(2, 0, 0, 0),
//                    intArrayOf(0, 0, 0, 2),
//                    intArrayOf(3, 0, 1, 0)
//                ), 1, 0
//            )
//        }, 14, "카드짝맞추기")
//        assertThat({
//            KakaoQuestions._카드짝맞추기(
//                arrayOf(
//                    intArrayOf(3, 0, 0, 2),
//                    intArrayOf(0, 0, 1, 0),
//                    intArrayOf(0, 1, 0, 0),
//                    intArrayOf(2, 0, 0, 3)
//                ), 0, 1
//            )
//        }, 16, "카드짝맞추기")

//        repeat(7) {
//            val permutation = PermutationAlg.permutation(IntArray(it) { it })
//            println(permutation.joinToString("\n") { it.joinToString() })
//            println("총 개수 = ${permutation.size}")
//            drawLine()
//        }
//        assertThat({ KakaoQuestions._숫자문자열과영단어("one4seveneight") }, 1478, "숫자문자열과영단어")
//        assertThat({ KakaoQuestions._숫자문자열과영단어("23four5six7") }, 234567, "숫자문자열과영단어")
//        assertThat({ KakaoQuestions._숫자문자열과영단어("2three45sixseven") }, 234567, "숫자문자열과영단어")
//        assertThat({ KakaoQuestions._숫자문자열과영단어("123") }, 123, "숫자문자열과영단어")
//        assertThat({
//            KakaoQuestions._거리두기확인하기(
//                arrayOf(
//                    arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
//                    arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
//                    arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
//                    arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
//                    arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
//                )
//            )
//        }, intArrayOf(1, 0, 1, 1, 1), "거리두기확인하기")
//        assertThat({
//            KakaoQuestions._표편집2(
//                8,
//                0,
//                arrayOf("C", "D 2", "C", "U 3", "C", "D 3", "C", "C", "U 2", "Z", "Z", "D 3", "C")
//            )
//        }, "XXOXOOXO", "표편집")
//        assertThat({
//            KakaoQuestions._표편집3(
//                8,
//                0,
//                arrayOf("C", "D 2", "C", "U 3", "C", "D 3", "C", "C", "U 2", "Z", "Z", "D 3", "C")
//            )
//        }, "XXOXOOXO", "표편집")
//        assertThat({
//            KakaoQuestions._표편집3(
//                8,
//                2,
//                arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z")
//            )
//        }, "OOOOXOOO", "표편집")
//        assertThat({
//            KakaoQuestions._표편집3(
//                8,
//                2,
//                arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C")
//            )
//        }, "OOXOXOOO", "표편집")
//        assertThat(
//            { KakaoQuestions._키패드누르기(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5), "right") },
//            "LRLLLRLLRRL",
//            "키패드누르기"
//        )
//        assertThat(
//            { KakaoQuestions._키패드누르기(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left") },
//            "LRLLRRLLLRR",
//            "키패드누르기"
//        )
//        assertThat(
//            { KakaoQuestions._키패드누르기(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0), "right") },
//            "LLRLLRLLRL",
//            "키패드누르기"
//        )
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")
//            )
//        }, intArrayOf(3, 7), "보석쇼핑")
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("AA", "AB", "AC", "AA", "AC")
//            )
//        }, intArrayOf(1, 3), "보석쇼핑")
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("XYZ", "XYZ", "XYZ")
//            )
//        }, intArrayOf(1, 1), "보석쇼핑")
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")
//            )
//        }, intArrayOf(1, 5), "보석쇼핑")
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("A", "A", "A", "B", "B")
//            )
//        }, intArrayOf(3, 4), "보석쇼핑")
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("A")
//            )
//        }, intArrayOf(1, 1), "보석쇼핑")
//        assertThat({
//            KakaoQuestions._보석쇼핑(
//                arrayOf("A", "B", "B", "B", "B", "B", "B", "C", "B", "A")
//            )
//        }, intArrayOf(8, 10), "보석쇼핑")
//        val test = arrayListOf<String>()
//        repeat(2) {
//            test.addAll(arrayOf("A", "B", "B", "B", "B", "B", "B", "C", "B", "A"))
//        }
//        assertThat({
//            KakaoQuestions._보석쇼핑(test.toTypedArray())
//        }, intArrayOf(8, 10), "보석쇼핑")
//        assertThat(
//            { KakaoQuestions._경주로건설(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))) },
//            900,
//            "경주로건설"
//        )
//        assertThat({
//            KakaoQuestions._경주로건설(arrayOf(
//                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
//                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
//                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0),
//                intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
//                intArrayOf(0, 0, 0, 1, 0, 0, 0, 1),
//                intArrayOf(0, 0, 1, 0, 0, 0, 1, 0),
//                intArrayOf(0, 1, 0, 0, 0, 1, 0, 0),
//                intArrayOf(1, 0, 0, 0, 0, 0, 0, 0)
//            ))
//        }, 3800, "경주로건설")
//        assertThat({
//            KakaoQuestions._경주로건설(arrayOf(
//                intArrayOf(0, 0, 1, 0),
//                intArrayOf(0, 0, 0, 0),
//                intArrayOf(0, 1, 0, 1),
//                intArrayOf(1, 0, 0, 0)
//            ))
//        }, 2100, "경주로건설")
//        assertThat({
//            KakaoQuestions._경주로건설(arrayOf(
//                intArrayOf(0, 0, 0, 0, 0, 0),
//                intArrayOf(0, 1, 1, 1, 1, 0),
//                intArrayOf(0, 0, 1, 0, 0, 0),
//                intArrayOf(1, 0, 0, 1, 0, 1),
//                intArrayOf(0, 1, 0, 0, 0, 1),
//                intArrayOf(0, 0, 0, 0, 0, 0)
//            ))
//        }, 3200, "경주로건설")
        assertThat(
            {
                KakaoQuestions._불량사용자(
                    arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
                    arrayOf("fr*d*", "abc1**")
                )
            }, 2, "불량사용자"
        )
        assertThat({
            KakaoQuestions._불량사용자(
                arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
                arrayOf("*rodo", "*rodo", "******")
            )
        }, 2, "불량사용자")
        assertThat({
            KakaoQuestions._불량사용자(
                arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
                arrayOf("fr*d*", "*rodo", "******", "******")
            )
        }, 3, "불량사용자")
        assertThat({
            KakaoQuestions._불량사용자(
                arrayOf("12345", "12453", "aaaaa"),
                arrayOf("*****", "*****")
            )
        }, 3, "불량사용자")
        assertThat({
            KakaoQuestions._불량사용자(
                arrayOf("12345", "12453", "aaaaa"),
                arrayOf("******", "*****")
            )
        }, 3, "불량사용자")
        assertThat({
            KakaoQuestions._불량사용자(
                arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
                arrayOf("**", "", "", "", "***")
            )
        }, 1, "불량사용자")
    }
}