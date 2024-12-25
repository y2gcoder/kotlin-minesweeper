package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BoardBuilderTest : BehaviorSpec({
    given("높이 5, 너비 5, 지뢰 개수 5를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .build()

            then("25개의 닫힌 셀을 만들 수 있다") {
                result.area shouldBe Area(height = height, width = width)
                result.cells.size shouldBe 25
                result.cells.all { it is ClosedCell } shouldBe true
            }
        }
    }

    given("높이를") {
        val width = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.width(width).countOfMines(countOfMines)
                }
            }
        }
        `when`("양수로 입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.height(0).width(width).countOfMines(countOfMines)
                }
            }
        }
    }

    given("너비를 입력하지 않으면") {
        val height = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.height(height).countOfMines(countOfMines)
                }
            }
        }
        `when`("양수로 입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.width(0).height(height).countOfMines(countOfMines)
                }
            }
        }
    }
})
