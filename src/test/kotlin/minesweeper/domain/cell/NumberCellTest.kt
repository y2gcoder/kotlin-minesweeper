package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.oneByOneLocation
import minesweeper.domain.strategy.NoOpenCellStrategy

class NumberCellTest : BehaviorSpec({
    given("row = 1, column = 1인 위치와 인접 지뢰 개수 0을 받아") {
        val location = oneByOneLocation
        val numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO

        `when`("숫자 셀을 생성하면") {
            val sut = NumberCell(location, numberOfAdjacentLandmines)

            then("위치는 row = 1, column = 1이다") {
                sut.location shouldBe location
            }

            then("인접 지뢰 개수는 0개이다") {
                sut.numberOfAdjacentLandmines shouldBe NumberOfAdjacentMines.ZERO
            }
        }
    }

    given("인접 지뢰 개수은") {
        val location = oneByOneLocation

        `when`("생성된 숫자 셀의") {
            then("심볼에 표시된다") {
                listOf(
                    NumberOfAdjacentMines.ZERO,
                    NumberOfAdjacentMines(1),
                    NumberOfAdjacentMines(2),
                    NumberOfAdjacentMines(3),
                    NumberOfAdjacentMines(4),
                    NumberOfAdjacentMines(5),
                    NumberOfAdjacentMines(6),
                    NumberOfAdjacentMines(7),
                    NumberOfAdjacentMines(8),
                ).forEach { numberOfAdjacentMines ->
                    val sut = NumberCell(location, numberOfAdjacentMines)

                    sut.symbol shouldBe Symbol.from(numberOfAdjacentMines)
                }
            }
        }
    }

    given("숫자 셀은") {
        val location = oneByOneLocation
        val numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO
        val sut = NumberCell(location, numberOfAdjacentLandmines)

        `when`("셀 오픈 전략으로") {
            val result = sut.findOpenStrategy()

            then("오픈하지 않음 전략을 가지고 있다") {
                result.shouldBeInstanceOf<NoOpenCellStrategy>()
            }
        }
    }
})
