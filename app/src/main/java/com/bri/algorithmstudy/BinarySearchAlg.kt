@file:Suppress("FunctionName", "NonAsciiCharacters", "unused")
package com.bri.algorithmstudy

object BinarySearchAlg {
    fun _부품찾기(
        arr: IntArray = intArrayOf(8, 3, 7, 9, 2),
        targets: IntArray = intArrayOf(5, 7, 9)
    ): String {
        arr.sort()
        val sb = StringBuilder("")
        for (target in targets) {
            val result = binarySearch(arr, target)?.let { "yes " } ?: "no "
            sb.append(result)
        }
        return sb.toString().trim()
    }

    private fun binarySearch(arr: IntArray, target: Int): Int? {
        var start = 0
        var end = arr.lastIndex
        while (start <= end) {
            val mid = (start + end) / 2
            when {
                arr[mid] == target -> return mid
                arr[mid] > target -> end = mid - 1
                arr[mid] < target -> start = mid + 1
            }
        }
        return null
    }

    fun _부품찾기2(
        arr: IntArray = intArrayOf(8, 3, 7, 9, 2),
        targets: IntArray = intArrayOf(5, 7, 9)
    ): String {
        val set = hashSetOf<Int>()
        set.addAll(arr.toList())
        return targets.map { set.contains(it) }
            .joinToString(" ") { if (it) "yes" else "no" }
    }

    fun _떡볶이떡만들기(m: Int = 6, arr: IntArray = intArrayOf(19, 15, 10, 17)): Int {
        // 1~arr의 최대 높이 이진탐색 시작
        // 자른 길이의 합을 구해 비교한다.
        val max = arr.maxOrNull() ?: m
        var start = 0
        var end = max
        var result = start
        while (start <= end) {
            val mid = (start + end) / 2
            val sum = arr.sumOf { (it - mid).coerceAtLeast(0) }
            println("mid = $mid / sum = $sum")
            when {
                sum == m -> return mid
                sum < m -> end = mid - 1
                sum > m -> {
                    result = mid
                    start = mid + 1
                }
            }
        }
        return result
    }

    fun _정렬된배열에서특정수의개수구하기(x: Int = 2, arr: IntArray = intArrayOf(1, 1, 2, 2, 2, 2, 3)): Int {
        binarySearch(arr, x)?.let {
            var left = it
            var right = it
            var start = 0
            var end = it - 1
            // 시작 위치 구하기
            while (start <= end) {
                val mid = (start + end) / 2
                when {
                    arr[mid] == x -> end = mid - 1
                    arr[mid] != x -> {
                        // arr[mid] != x && arr[mid+1] == x
                        // x 시작 위치 = mid + 1
                        if (arr[mid + 1] == x) {
                            left = mid + 1
                            break
                        } else {
                            start = mid + 1
                        }
                    }
                }
            }
            start = it + 1
            end = arr.lastIndex
            // 종료 위치 구하기
            while (start <= end) {
                val mid = (start + end) / 2
                when {
                    arr[mid] == x -> start = mid + 1
                    // arr[mid] != x && arr[mid-1] == x
                    // x 종료 위치 = mid - 1
                    arr[mid] != x -> {
                        if (arr[mid - 1] == x) {
                            right = mid - 1
                            break
                        } else {
                            end = mid - 1
                        }
                    }
                }
            }
            return right - left + 1
        } ?: run { return -1 }
    }

    // 시간복잡도 비교용
    fun _정렬된배열에서특정수의개수구하기2(x: Int = 2, arr: IntArray = intArrayOf(1, 1, 2, 2, 2, 2, 3)): Int {
        return arr.count { it == x }.takeIf { it > 0 } ?: -1
    }

    // 이진탐색으로 인덱스와 값을 비교하여 찾음
    fun _고정점찾기(arr: IntArray = intArrayOf(-15, -6, 1, 3, 7)): Int {
        var start = 0
        var end = arr.lastIndex
        while (start <= end) {
            val mid = (start + end) / 2
            when {
                mid == arr[mid] -> return mid
                mid < arr[mid] -> end = mid - 1
                mid > arr[mid] -> start = mid + 1
            }
        }
        return -1
    }

    /**
     * 거리를 기준으로 이진탐색 (최소거리~최대거리)
     * mid(거리) 이상일 때 공유기를 설치하고, 공유기 개수가 부족하면 거리를 줄여서 재검색
     * 공유기 개수가 많으면 거리를 늘려서 재검색
     */
    fun _공유기설치(n: Int = 3, arr: IntArray = intArrayOf(1, 2, 8, 4, 9)): Int {
        arr.sort()
        var result = -1
        var start = arr.first()
        var end = arr.last() - arr.first()
        while (start <= end) {
            val mid = (start + end) / 2
            var prev = arr.first()
            var count = 1
            // 이전 공유기 위치와 비교하여 mid 거리 이상일 때 공유기 설치
            for (home in arr) {
                if (home - prev >= mid) {
                    prev = home
                    count++
                }
            }
            // 공유기 개수에 따라 거리를 줄이거나 늘려서 재검색
            when {
                count < n -> end = mid - 1
                else -> {
                    result = mid
                    start = mid + 1
                }
            }
        }
        return result
    }

    // query, word 순차탐색 -> TimeOut
    fun _가사검색(
        words: Array<String> = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"),
        queries: Array<String> = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")
    ): IntArray {
        return queries.map { query ->
            words.count { word ->
                if (query.length != word.length) return@count false
                if (query.startsWith("?")) {
                    word.endsWith(query.filter { it in 'a'..'z' })
                } else {
                    word.startsWith(query.filter { it in 'a'..'z' })
                }
            }
        }.toIntArray()
    }

    // 예전에 풀었던 방식
    // words를 미리 map으로 생성
    fun _가사검색2(
        words: Array<String> = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"),
        queries: Array<String> = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")
    ): IntArray {
        val lengthMap = HashMap<Int, Int>()
        val map = HashMap<String, Int>()
        val reverseMap = HashMap<String, Int>()
        words.forEach { word ->
            for (i in word.indices) {
                val target = word.substring(0, i + 1)
                map["${word.length}$target"] = map["${word.length}$target"]?.plus(1) ?: 1
            }
            lengthMap[word.length] = (lengthMap[word.length] ?: 0) + 1
        }
        words.map { it.reversed() }.forEach { word ->
            for (i in word.indices) {
                val target = word.substring(0, i + 1)
                reverseMap["${word.length}$target"] = reverseMap["${word.length}$target"]?.plus(1)
                    ?: 1
            }
        }

        val result = IntArray(queries.size)
        queries.forEachIndexed { index, query ->
            if (query.endsWith("?")) {
                //모두물음표
                if (query.startsWith("?")) {
                    result[index] = lengthMap[query.length] ?: 0
                }
                //시작이정해져있을때
                else {
                    result[index] = map[query.length.toString() + query.replace("?", "")] ?: 0
                }
            }
            //끝이정해져있을때
            else {
                result[index] =
                    reverseMap[query.length.toString() + query.reversed().replace("?", "")]
                        ?: 0
            }
        }
        return result
    }

    /** 이진탐색으로 풀려면
     * words 길이+알파벳 정렬
     * words 역순 길이+알파벳 정렬
     * queries를 이진탐색
     * 시작 위치와 종료 위치를 찾아 개수를 확인한다.
     */
    val test = arrayOf("zafdz", "frodo", "front", "frost", "frozen", "frame", "kakao")
    fun _가사검색3(
        words: Array<String> = test,
        queries: Array<String> = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")
    ): IntArray {
        val list: List<String> = words.sortedWith(object : Comparator<String> {
            override fun compare(o1: String, o2: String): Int {
                return (o1.length - o2.length).takeIf { it != 0 } ?: o1.compareTo(o2)
            }
        })
        val reversedList: List<String> =
            words.map { it.reversed() }.sortedWith(object : Comparator<String> {
                override fun compare(o1: String, o2: String): Int {
                    return (o1.length - o2.length).takeIf { it != 0 } ?: o1.compareTo(o2)
                }
            })

        return queries.map { query ->
            val filteredQuery = query.filter { it != '?' }
            if (query.startsWith("?")) {
                querySearch(reversedList, filteredQuery.reversed(), query.length)
            } else {
                querySearch(list, filteredQuery, query.length)
            }
        }.toIntArray()
    }

    private fun querySearch(array: List<String>, query: String, length: Int): Int {
        var start = 0
        var end = array.lastIndex
        var left = end
        var right = start
        while (start <= end) {
            val mid = (start + end) / 2
            if(array[mid].length < length) {
                start = mid + 1
                continue
            }
            val target = array[mid].substring(0, query.length)
            when {
                array[mid].length > length -> end = mid - 1
                target == query -> {
                    left = mid
                    end = mid - 1
                }
                target < query -> start = mid + 1
                target > query -> end = mid - 1
            }
        }

        start = 0
        end = array.lastIndex
        while (start <= end) {
            val mid = (start + end) / 2
            if(array[mid].length < length) {
                start = mid + 1
                continue
            }
            val target = array[mid].substring(0, query.length)
            when {
                array[mid].length > length -> end = mid - 1
                target == query -> {
                    right = mid
                    start = mid + 1
                }
                target < query -> start = mid + 1
                target > query -> end = mid - 1
            }
        }
        return (right - left + 1).coerceAtLeast(0)
    }
}