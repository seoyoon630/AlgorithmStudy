package com.bri.algorithmstudy

import kotlin.math.absoluteValue

object SortingAlg {
    /**
     * 정렬 = 데이터를 특정한 기준에 따라 순서대로 나열하는 것
     * 1. 선택정렬 = 처리되지 않은 데이터 중에서 가장 작은 데이터를 맨 앞의 데이터와 바꾸는 것을 반복
     * -> 이중 반복(시간복잡도 = O(N^2))
     *
     * 2. 삽입정렬 = 처리되지 않은 데이터를 하나씩 골라 적절한 위치에 삽입하는 것을 반복
     * -> 이전 데이터들이 정렬되어 있다고 판단하고, 다음 데이터가 들어갈 위치를 판단
     * -> 현재 데이터가 거의 정렬되어 있는 상태라면 매우 빠르게 동작(O(N))
     *
     * 3. 퀵정렬 = 기준 데이터를 설정하고 그 기준보다 큰 데이터와 작은 데이터의 위치를 바꾸는 방법
     * -> 첫번째 데이터를 기준 데이터로 설정
     * -> 오른쪽으로 가면서 피벗보다 큰 값, 왼쪽으로 가면서 피벗보다 작은 값의 위치를 서로 변경
     * -> 위치가 엇갈리는 경우 피벗과 작은 값의 위치를 서로 변경
     * -> 재귀, 정렬 범위가 점점 좁아짐
     * -> 분할이 절반씩 일어난다면 O(NlogN), 최악의 경우 O(N^2)
     *
     * 4. 계수정렬 = 데이터의 크기 범위가 제한되어 정수형태로 표현가능할 때 사용 가능
     * -> 데이터의 개수가 N, 최댓값이 K일 때 수행시간 O(N+K)를 보장
     * -> 가장 작은 데이터부터 가장 큰 데이터까지 범위가 모두 담길 수 있도록 리스트를 생성
     * -> 동일한 데이터가 여러 개 등장할 때 효율적
     */

    val orgArr = intArrayOf(7, 5, 9, 0, 3, 1, 6, 2, 4, 8)
    fun _선택정렬(): IntArray {
        val array = orgArr.clone()
        println(array.joinToString())
        for (i in array.indices) {
            var minIndex = i
            for (j in i + 1 until array.size) {
                if (array[j] < array[minIndex]) {
                    minIndex = j
                }
            }
            array[i] = array[minIndex].also { array[minIndex] = array[i] }
            println(array.joinToString())
        }
        return array
    }

    fun _삽입정렬(): IntArray {
        val array = orgArr.clone()
        println(array.joinToString())
        for (i in 1 until array.size) {
            for (j in i downTo 1) {
                if (array[j] < array[j - 1]) {
                    array[j] = array[j - 1].also { array[j - 1] = array[j] }
                } else break
            }
            println(array.joinToString())
        }
        return array
    }

    fun _퀵정렬(): IntArray {
        val array = orgArr.clone()
        println(array.joinToString())
        recursiveQuickSort(array, 0, array.lastIndex)
        return array
    }

    private fun recursiveQuickSort(array: IntArray, start: Int, end: Int) {
        if (start >= end) return
        val pivot = start
        var left = start + 1
        var right = end
        while (left <= right) {
            while (left <= end && array[left] <= array[pivot]) left++
            while (right > start && array[right] >= array[pivot]) right--
            if (left < right) {
                array[left] = array[right].also { array[right] = array[left] }
            } else {
                array[right] = array[pivot].also { array[pivot] = array[right] }
            }
            println(array.joinToString())
        }
        recursiveQuickSort(array, start, right - 1)
        recursiveQuickSort(array, right + 1, end)
    }

    fun _퀵정렬2(): IntArray {
        return advancedQuickSort(orgArr.clone())
    }

    private fun advancedQuickSort(array: IntArray): IntArray {
        if (array.size <= 1) return array
        val pivot = array[0]
        val tail = array.slice(1 until array.size)
        val leftSide = tail.filter { it <= pivot }.toIntArray()
        val rightSide = tail.filter { it > pivot }.toIntArray()
        println("left = ${leftSide.joinToString()}, right = ${rightSide.joinToString()}")
        println((leftSide + pivot + rightSide).joinToString())
        return advancedQuickSort(leftSide) + pivot + advancedQuickSort(rightSide)
    }

    fun _계수정렬(): IntArray {
        val array = intArrayOf(7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2)
        val map = HashMap<Int, Int>()
        val min = array.minOrNull()
        val max = array.maxOrNull()
        if (min == null || max == null) return array
        for (i in min..max) {
            map[i] = 0
        }
        array.forEach { map[it] = (map[it] ?: 0) + 1 }
        val result = IntArray(array.size) { -1 }
        var index = 0
        map.toSortedMap().forEach {
            repeat(it.value) { _ ->
                result[index++] = it.key
            }
        }
        return result
    }

    fun _두배열의원소교체(
        a: IntArray = intArrayOf(1, 2, 5, 4, 3),
        b: IntArray = intArrayOf(5, 5, 6, 6, 5),
        k: Int = 3
    ): Int {
        val a2 = a.sorted().toIntArray()
        val b2 = b.sortedDescending()
        repeat(k) {
            if (a2[it] < b2[it]) {
                a2[it] = b2[it]
            }
        }
        return a2.sum()
    }

    fun _위에서아래로(nums: IntArray = intArrayOf(15, 27, 12)): List<Int> {
        return nums.sortedDescending()
    }

    fun _성적이낮은순서로학생출력하기(
        s: Array<String> = arrayOf("홍길동", "이순신"),
        scores: IntArray = intArrayOf(95, 77)
    ): String {
        return scores.indices.sortedBy { scores[it] }.map { s[it] }.joinToString()
    }

    /**
     * 1. 국어 점수 내림차순
     * 2. 영어 점수 올림차순
     * 3. 수학 점수 내림차순
     * 4. 이름 올림차순
     */
    fun _국영수(
        s: Array<String> = arrayOf(
            "Junkyu 50 60 100",
            "Sangkeun 80 60 50",
            "Sunyoung 80 70 100",
            "Soong 50 60 90",
            "Haebin 50 60 100",
            "Kangsoo 60 80 100",
            "Donghyuk 80 60 100",
            "Sei 70 70 70",
            "Wonseob 70 70 90",
            "Sanghyun 70 70 80",
            "nsj 80 80 80",
            "Taewhan 50 60 90"
        )
    ): String {
        val names = Array(s.size) { "" }
        return s.mapIndexed { index, it ->
            val splits = it.split(" ")
            names[index] = splits[0]
            intArrayOf(index, splits[1].toInt(), splits[2].toInt(), splits[3].toInt())
        }.sortedWith { o1, o2 ->
            return@sortedWith (o2[1] - o1[1]).takeIf { it != 0 }
                ?: (o1[2] - o2[2]).takeIf { it != 0 }
                ?: (o2[3] - o1[3]).takeIf { it != 0 }
                ?: (names[o1[0]].compareTo(names[o2[0]]))
        }.joinToString { names[it[0]] }
    }

    fun _안테나(n: IntArray = intArrayOf(5, 1, 7, 9)): Int {
        return n.sortedBy { current -> n.sumOf { target -> (target - current).absoluteValue } }[0]
    }
}