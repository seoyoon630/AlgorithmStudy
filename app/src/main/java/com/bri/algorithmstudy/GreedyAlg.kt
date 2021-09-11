package com.bri.algorithmstudy

/**
 * 그리디 알고리즘
 * = 현재 상황에서 지금 당장 좋은 것만 고르는 방법
 * 정당성 분석이 가장 중요
 * -> 단순히 가장 좋아 보이는 것을 반복적으로 선택해도 최적의 해를 구할 수 있는지 검토하는 과정이 필요
 *
 */
object GreedyAlg {
    /**
     * 500원, 100원, 50원, 10원 동전이 무한히 존재한다고 가정하고,
     * 손님에게 거슬러 주어야 할 돈이 N원일 때 거슬러 주어야 할 동전의 최소 개수를 구하세요.
     * 참고) N은 10의 배수
     * -> 가지고 있는 동전 중에서 큰 단위가 항상 작은 단위의 배수이므로
     * 작은 단위의 동전들을 종합해 다른 해가 나올 수 없기 때문에 그리디 알고리즘이 성립
     */
    fun _거스름돈(_n: Int = 1260): Int {
        var n = _n
        var count = 0
        val array = listOf(500, 100, 50, 10)

        for (coin in array) {
            count += n / coin
            n %= coin
        }
        return count.also { println("_거스름돈 = $it") }
    }

    /**
     * N에서 1을 빼거나 N을 K로 나누는 과정을 반복하여 N을 1로 만드는 최소 횟수를 구하세요.
     * 참고) 단, 나누어 떨어질 때에만 N을 K로 나눌 수 있음
     */
    fun _1이될때까지(_n: Int = 25, k: Int = 5): Int {
        var n = _n
        var count = 0
        while (true) {
            val target = (n / k) * k
            count += n - target
            n = target
            if (n < k) {
                break
            }
            count += 1
            n /= k
        }
        count += (n - 1)
        return count
    }

    /**
     * 숫자로 이루어진 문자열 S가 주어졌을 때, X 혹은 + 연산자를 통해 만들어질 수 있는 가장 큰 수를 구하세요.
     * 참고) 모든 연산은 왼쪽에서부터 순서대로 진행
     */
    fun _곱하기혹은더하기(S: String = "02984"): Int {
        if (S.isEmpty()) return 0
        // 처음 문자를 숫자로 변경하여 대입
        val start = S[0].digitToInt()
        return S.map { it.digitToInt() }.subList(1, S.length)
            .fold(start) { left, right ->
                // 두 수 모두 1보다 클 때, 곱하기 수행
                if (left > 1 && right > 1) left * right
                else left + right
            }.also { println("_곱하기혹은더하기($S) = $it") }
    }

    /**
     * 공포도가 X인 모험가는 반드시 X명 이상으로 구성한 모험가 그룹에 참여해야할 때, 모험가 그룹의 최댓값을 구하세요.
     * 참고) 모든 모험가를 참여시키지 않아도 됨
     * 1. 오름차순 정렬 이후에 공포도가 가장 낮은 모험가부터 하나씩 확인
     * 2. 공포도를 하나씩 확인하여 '현재 그룹에 포함된 모험가의 수' >= '현재 확인하고 있는 공포도' -> 그룹
     * -> 공포도가 오름차순으로 정렬되어 있다는 점에서 그리디 알고리즘이 성립
     */
    fun _모험가길드(n: Int = 5, s: String = "2 3 1 2 2"): Int {
        val scoreList = s.split(" ").map { it.toInt() }.sorted()
        var result = 0
        var number = 0
        scoreList.forEach { score ->
            if (++number >= score) {
                result++
                number = 0
            }
        }
        return result
    }

    /**
     * 주어진 N개의 숫자를 골라 M번 더하여 가장 큰 수를 만드세요.
     * 단, 같은 인덱스의 숫자는 K번을 초과하여 더할 수 없습니다.
     * 참고) N은 2 이상
     *
     * 풀이)
     * 가장 큰 두 숫자를 고른다.
     * 가장 큰 수가 제일 많이 나올 수 있는 방법은 K개씩 계속 더해가는 방법.
     * -> 2번째로 큰 숫자를 (M / k)번 더하고, 그 외에는 가장 큰 수를 더하는 것이 가장 큰 수를 만드는 방법
     * 666 5 666 5
     */
    fun _큰수의법칙(
        n: Int = 5,
        m: Int = 8,
        k: Int = 3,
        array: IntArray = intArrayOf(2, 4, 5, 4, 6)
    ): Int {
        val sorted = array.sortedDescending()
        val max = sorted[0]
        val secondMax = sorted[1]
        if (max == secondMax) return m * max
        val share = m / k
        return share * secondMax + (m - share) * max
    }

    /**
     * N X M 개의 숫자가 있을 때, 조건에 따라 뽑을 수 있는 숫자 중 제일 큰 숫자를 구하세요.
     * 1. 행을 선택한다.
     * 2. 선택한 행에 포함된 숫자 중 가장 작은 숫자를 선택한다.
     */
    fun _숫자카드게임(vararg array: IntArray): Int = array.map { it.minOrNull() ?: 0 }.maxOrNull() ?: 0

    /**
     * 0과 1로만 이루어진 문자열 s가 주어질 때, 모든 숫자를 전부 같도록 뒤집는 최소 횟수를 구하세요.
     * 연속된 숫자는 한 번에 뒤집을 수 있습니다.
     */
    fun _문자열뒤집기(s: String = "0001100"): Int {
        val array = s.toCharArray()
        // 1. 연속된 숫자를 압축한다. -> 010
        val result = array.filterIndexed { index, c ->
            if (index == 0) true
            else array[index - 1] != c
        }
        // 2. 0과 1의 개수를 각각 구해 가장 적은 개수를 리턴 -> 0 : 2개, 1 : 1개 return 1
        val countOf0 = result.count { it == '0' }
        val countOf1 = result.size - countOf0
        return Math.min(countOf0, countOf1)
    }

    /**
     *  풀이 참고했음!
     * 만들 수 없는 금액의 조건
     *  - 가장 작은 동전보다 작은 금액
     *  - 모든 동전을 더한 것보다 큰 금액
     *
     * 1. 1로 초기금액을 설정한다.
     * 2. 주어진 동전을 오름차순으로 정렬한다.
     * 3. 동전을 하나씩 꺼낸다.
     * 4. 금액이 동전보다 작으면 STOP
     * 5. 금액이 동전보다 크면 금액에 동전값을 더한다.
     */
    fun _만들수없는금액(coins: IntArray = intArrayOf(1, 1, 2, 3, 9)): Int {
        var target = 1
        coins.sorted().forEach { coin ->
            if (coin <= target) target += coin
            else return@forEach
        }
        return target
    }

    /**
     * 1. 무게별로 공의 인덱스를 그룹핑한다.
     * 2. 볼링공을 하나씩 꺼낸다.
     * 3. 꺼낸 볼링공의 무게와 일치하지 않는 인덱스 목록을 가져온다.
     * 4. 그 중 현재 선택한 볼링공의 인덱스보다 큰 공의 개수를 결과에 더한 뒤 2번부터 반복
     * 5. 모두 더한 결과를 리턴한다.
     */
    fun _볼링공고르기(m: Int = 5, balls: IntArray = intArrayOf(1, 3, 2, 3, 2)): Int {
        val groups = balls.indices.groupBy { balls[it] }
        val results = balls.mapIndexed { index, ball ->
            groups.filter { it.key != ball }.values.flatten().count { it > index }
        }
        return results.sum()
    }

    /**
     * 볼링공 고르기 시간 복잡도 개선
     * AS-IS : for문을 돌면서 매번 선택한 볼링공의 무게와 일치하지 않는 인덱스를 구함
     * 결국 구해야 하는 것은 같은 무게를 제외한 나머지 공의 인덱스 목록
     * 1. key = 1~m, value = 0~balls.lastIndex 가진 맵을 미리 생성
     * 2. 볼링공 for문을 돌면서 value에서 해당 인덱스를 삭제
     * 3. 남은 인덱스는 그 무게가 아닌 인덱스만 남으므로 이중 반복문이 없어 n*n-> 3n의 시간복잡도로 개선
     */
    fun _볼링공고르기2(m: Int = 5, balls: IntArray = intArrayOf(1, 3, 2, 3, 2)): Int {
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in 1..m) {
            map[i] = ArrayList(balls.indices.toList())
        }
        balls.forEachIndexed { index, ball -> map[ball]?.remove(index) }

        return balls.mapIndexed { index, ball -> map[ball]?.count { it > index } ?: 0 }.sum()
    }

    /**
     * 1. 현재 남은 음식을 모두 먹을 수 있는 최소 회전수를 구한다.
     * 2. if 남은 음식 수 * 최소 회전수 < 장애까지 남은 초라면
     *   모든 음식 남은 양 -= 최소 회전수, 장애까지 남은 초 -= 남은음식수 * 최소 회전수
     *   1부터 반복한다.
     * 3. else 장애까지 남은 초 안에서 가능한 최대 회전수를 구한다.
     *   모든 음식 남은 양 -= 최대 회전수, 장애까지 남은 초 -= 남은음식수 * 최대 회전수
     *   이제 남은 음식 중 나머지 시간번쨰 인덱스를 구해 리턴한다.
     */
    fun _무지의먹방라이브(foodTimes: IntArray = intArrayOf(3, 1, 2), k: Int = 5): Int {
        if (foodTimes.sum() <= k) return -1
        val map = HashMap<Int, Int>()
        foodTimes.forEachIndexed { index, i -> map[index + 1] = i }
        var left = k
        while (left > 0) {
            val minCount = map.values.minOrNull() ?: 0
            val minFoodCount = minCount * map.size
            val removeIndex = ArrayList<Int>()
            if (minFoodCount < left) {
                map.keys.forEach {
                    if (map[it] == minCount) removeIndex.add(it)
                    else map[it] = (map[it] ?: 0) - minCount
                }
                removeIndex.forEach { map.remove(it) }
                left -= minFoodCount
            } else {
                val maxCount = left / map.size
                map.keys.forEach { map[it] = (map[it] ?: 0) - maxCount }
                left -= maxCount * map.size
                return map.keys.sorted()[left]
            }
        }
        return -1
    }
}