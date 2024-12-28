package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LocationTest : BehaviorSpec({
    given("보드의 너비를 받아") {
        val width = 3
        val sut = Location(row = 3, col = 3)

        `when`("위치를") {
            val result = sut.toIndex(width)

            then("인덱스로 바꿀 수 있다") {
                result shouldBe 8
            }
        }
    }

    given("인덱스와 너비를 받아") {
        val index = 8
        val width = 3

        `when`("위치를") {
            val result = Location.from(index = index, width = width)

            then("만들 수 있다") {
                result shouldBe Location(row = 3, col = 3)
            }
        }
    }
})
