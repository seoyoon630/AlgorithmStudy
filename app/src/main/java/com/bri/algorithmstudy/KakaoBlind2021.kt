package com.bri.algorithmstudy

object KakaoBlind2021 {
    fun _순위검색(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size) { 0 }
        val infos = HashMap<String, ArrayList<Int>>()
        info.forEach {
            val key = makeKey(it)
            val score = getScore(it)
            if (!infos.containsKey(key)) infos[key] = ArrayList()
            infos[key]?.add(score)
        }
        // 정렬
        infos.keys.forEach { infos[it]?.sort() }

        query.forEachIndexed { index, s ->
            val key = makeKey(s)
            val score = getScore(s)
            val regex = key.replace("0", "[0-9]").toRegex()
            answer[index] = infos.keys.filter { regex.matches(it) }
                .map { infos[it] }
                .sumOf {
                    val size = it?.size ?: 0
                    val first = binarySearch(it, score).takeIf { it > -1 } ?: size
                    size - first
                }
        }

        return answer
    }

    private fun makeKey(s: String): String {
        val split = s.split(" ").filter { it != "and" }
        val lang = listOf("-", "cpp", "java", "python").indexOf(split[0])
        val group = listOf("-", "backend", "frontend").indexOf(split[1])
        val career = listOf("-", "junior", "senior").indexOf(split[2])
        val food = listOf("-", "chicken", "pizza").indexOf(split[3])
        return "$lang$group$career$food"
    }

    private fun getScore(s: String): Int {
        return s.split(" ").filter { it != "and" }[4].toInt()
    }

    private fun binarySearch(arr: ArrayList<Int>?, target: Int): Int {
        if (arr == null) return -1
        var start = 0
        var end = arr.lastIndex
        var mid : Int
        var answer = -1
        while (start <= end) {
            mid = (start + end) / 2
            when {
//                arr[mid] == target -> return mid
                arr[mid] >= target -> {
                    answer = mid
                    end = mid - 1
                }
                arr[mid] < target -> start = mid + 1
            }
        }
        return answer
    }
}