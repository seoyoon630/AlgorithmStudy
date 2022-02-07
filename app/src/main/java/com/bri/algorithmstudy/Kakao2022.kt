package com.bri.algorithmstudy

object Kakao2022 {
    fun 신고결과받기(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val reportMap = HashMap<String, HashSet<String>>()
        val countMap = HashMap<String, Int>()
        id_list.forEach { id ->
            reportMap[id] = hashSetOf()
            countMap[id] = 0
        }
        report.forEach {
            val splits = it.split(" ")
            val id1 = splits[0]
            val id2 = splits[1]
            if (reportMap[id1]?.contains(id2) == false) {
                reportMap[id1]?.add(id2)
                countMap[id2] = (countMap[id2] ?: 0) + 1
            }
        }
        id_list.forEach { id ->
            val list = reportMap[id]?.filter { countMap[it] ?: 0 >= k }
            println("$id : ${list?.joinToString()}")
        }
        return id_list.map { id ->
            reportMap[id]?.filter { countMap[it] ?: 0 >= k }?.count() ?: 0
        }.toIntArray()
    }

    fun 신고결과받기2(id_list: Array<String>, report: Array<String>, k: Int): IntArray =
        report.map { it.split(" ") }
            .groupBy { it[1] }
            .asSequence()
            .map { it.value.distinct() }
            .filter { it.size >= k }
            .flatten()
            .map { it[0] }
            .groupingBy { it }
            .eachCount()
            .run { id_list.map { getOrDefault(it, 0) } }.toIntArray()

    fun k진수에서_소수개수구하기(n: Int, k: Int): Int {
        val num = convert(n, k)
        val nums = num.split(Regex("0+")).filter { it.isNotEmpty() }.map { it.toDouble() }
        return nums.filter { isPrime(it) }.count()
    }

    fun convert(n: Int, k: Int): String {
        if (k == 10) {
            return n.toString()
        }
        val sb = StringBuilder()
        var t = n
        while (t >= k) {
            sb.append(t % k)
            t /= k
        }
        sb.append(t)
        return sb.reverse().toString()
    }

    fun isPrime(n: Double): Boolean {
        if (n == 1.0) return false
        val max = Math.sqrt(n)
        for (i in 2..max.toInt()) {
            if (n % i == 0.0) return false
        }
        return true
    }

    fun 주차요금계산(fees: IntArray, records: Array<String>): IntArray {
        val defaultMinute = fees[0]
        val defaultFee = fees[1]
        val unitMinute = fees[2]
        val unitFee = fees[3]

        val totalTimes = HashMap<Int, Int>()
        val inTime = HashMap<Int, String>()
        records.forEach { record ->
            val splits = record.split(" ")
            val time = splits[0]
            val car = splits[1].toInt()
            inTime[car]?.let {
                inTime.remove(car)
                totalTimes[car] = (totalTimes[car] ?: 0) + (convert(time) - convert(it))
            } ?: run {
                inTime[car] = time
            }
        }
        inTime.forEach {
            totalTimes[it.key] = (totalTimes[it.key] ?: 0) + +(convert("23:59") - convert(it.value))
        }
        return totalTimes.keys
            .sorted()
            .map { key ->
                totalTimes[key]?.let {
                    val additionalFee = when {
                        it <= defaultMinute -> defaultFee
                        else -> {
                            val additionalTime = it - defaultMinute
                            val unitTime =
                                Math.ceil((additionalTime) / unitMinute.toDouble()).toInt()
                            defaultFee + unitTime * unitFee
                        }
                    }
                    additionalFee
                } ?: defaultFee
            }.toIntArray()
    }

    fun convert(time: String): Int =
        time.split(":")
            .mapIndexed { index, s ->
                s.toInt() * (if (index == 0) 60 else 1)
            }.sum()

    fun 양궁대회(n: Int, info: IntArray): IntArray {
        max = -55
        result = IntArray(11) { 0 }
        recursiveFunction(n, n, 0, 0, 0, result, info)
        return if (max <= 0) intArrayOf(-1)
        else result
    }

    var max = -55
    var result = IntArray(11) { 0 }

    fun recursiveFunction(
        n: Int,
        left: Int,
        myScore: Int,
        rivalScore: Int,
        index: Int,
        current: IntArray,
        info: IntArray
    ) {
        if (index == 10) {
            val temp = current.clone()
            temp[index] += left
            if (max < myScore - rivalScore) {
                max = myScore - rivalScore
                result = temp
            } else if (max == myScore - rivalScore) {
                for (i in 10 downTo 0) {
                    if (result[i] < temp[i]) result = temp
                    else if (result[i] > temp[i]) break
                }
            }
            return
        }
        val need = info[index] + 1
        val score = 10 - index

        if (left >= need) {
            val myNextScore = myScore + score
            current[index] += need
            recursiveFunction(n, left - need, myNextScore, rivalScore, index + 1, current, info)
            current[index] -= need
        }

        val rivalNextScore = if (info[index] == 0) rivalScore else rivalScore + score
        recursiveFunction(n, left, myScore, rivalNextScore, index + 1, current, info)
    }

    val nodes = HashMap<Int, ArrayList<Int>>()
    var maxCountOfSheeps = 0
    fun 양과늑대(info: IntArray, edges: Array<IntArray>): Int {
        nodes.clear()
        edges.forEach {
            nodes[it[0]]?.add(it[1]) ?: run {
                nodes[it[0]] = arrayListOf(it[1])
            }
        }

        val list = ArrayList<Int>()
        list.add(0)
        dfs(0, 0, 0, list, info, info.count { it == 0 })
        return maxCountOfSheeps
    }

    private fun dfs(index: Int, s: Int, w: Int, list: ArrayList<Int>, info: IntArray, max: Int) {
        if (maxCountOfSheeps == max) return
        var sheep = s
        var wolf = w
        when (info[index]) {
            0 -> sheep++
            else -> wolf++
        }
        if (sheep <= wolf) return

        maxCountOfSheeps = Math.max(maxCountOfSheeps, sheep)

        val next = ArrayList<Int>()
        next.addAll(list)
        next.remove(index)
        nodes[index]?.let { children -> next.addAll(children) }
        for (n in next) {
            dfs(n, sheep, wolf, next, info, max)
        }
    }

    var count: Double = 0.0
    fun 파괴되지않은건물1(board: Array<IntArray>, skill: Array<IntArray>): Int {
        count = 0.0
        skill.forEach {
            val type = it[0]
            val degree = it[5]
            val diff = if (type == 1) -degree else degree
            for (y in it[1]..it[3]) {
                for (x in it[2]..it[4]) {
                    board[y][x] += diff
                    count++
                }
            }
        }
        println("count = $count")
        return board.map { it.count { it > 0 } }.sum()
    }

    fun 파괴되지않은건물2(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val list = Array(board.size) { HashMap<IntRange, Int>() }
        skill.forEach {
            val type = it[0]
            val degree = it[5]
            val diff = if (type == 1) -degree else degree
            for (y in it[1]..it[3]) {
                val xRange = (it[2]..it[4])
                list[y][xRange] = (list[y][xRange] ?: 0) + diff
            }
        }

        val width = board[0].size
        val result = list.mapIndexed { index, hashMap ->
            val diffs = IntArray(width) { board[index][it] }
            hashMap.keys.forEach { range ->
                range.forEach {
                    diffs[it] += (hashMap[range] ?: 0)
                }
            }
            diffs.count { it > 0 }
        }.sum()
        return result
    }

    fun 파괴되지않은건물(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val height = board.size + 1
        val width = board[0].size + 1
        val result = Array(height) { IntArray(width) { 0 } }
        skill.forEach {
            val type = it[0]
            val degree = it[5]
            val diff = if (type == 1) -degree else degree
            // 누적합
            result[it[1]][it[2]] += diff
            result[it[1]][it[4] + 1] -= diff
            result[it[3] + 1][it[2]] -= diff
            result[it[3] + 1][it[4] + 1] += diff
        }

        (0 until height).forEach { y ->
            // 가로 누적합
            (0 until width - 1).forEach { x ->
                result[y][x + 1] += result[y][x]
            }
            // 세로 누적합
            if (y > 0) {
                (0 until width - 1).forEach { x ->
                    result[y][x] += result[y - 1][x]
                }
            }
        }

        // 기본 내구도 합
        (0 until height - 1).forEach { y ->
            (0 until width - 1).forEach { x ->
                result[y][x] += board[y][x]
            }
        }
        return result.map { it.count { v -> v > 0 } }.sum()
    }
}