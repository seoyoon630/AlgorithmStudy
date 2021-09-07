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
}