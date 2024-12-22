package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.oneByOneLocation
import minesweeper.domain.strategy.CascadingOpenCellStrategy
import minesweeper.domain.strategy.SingleOpenCellStrategy

class ClosedCellTest : BehaviorSpec({
    given("row = 1, column = 1인 위치를 받아") {
        val location = oneByOneLocation

        `when`("닫힌 셀을 생성하면") {
            val sut = ClosedCell(location)

            then("닫힌 셀의 위치는 row = 1, column = 1 이다") {
                sut.location shouldBe location
            }

            then("닫힌 셀의 심볼은 닫힘이다") {
                sut.symbol shouldBe Symbol.CLOSED
            }

            then("기본적으로는 지뢰를 갖고 있지 않다") {
                sut.hasLandmine shouldBe false
            }

            then("기본적으로는 인접 지뢰 개수가 0개이다") {
                sut.numberOfAdjacentLandmines shouldBe NumberOfAdjacentMines.ZERO
            }
        }
    }

    given("지뢰를 가지고 있는 ClosedCell 은") {
        val sut = ClosedCell(oneByOneLocation, hasLandmine = true)

        `when`("열게 되면") {
            val result = sut.open()

            then("지뢰 셀로 변한다") {
                result.shouldBeInstanceOf<LandmineCell>()
            }
        }
    }

    given("지뢰를 가지고 있지 않은 닫힌 셀은") {
        val sut = ClosedCell(oneByOneLocation)

        `when`("열게 되면") {
            val result = sut.open()

            then("숫자 셀로 변한다") {
                result.shouldBeInstanceOf<NumberCell>()
            }
        }
    }

    given("새로운 인접 지뢰 개수 8로") {
        val location = oneByOneLocation
        val sut = ClosedCell(location)
        val newNumberOfAdjacentMines = NumberOfAdjacentMines(8)

        `when`("닫힌 셀을 업데이트하여") {
            val result = sut.withNumberOfAdjacentLandmines(newNumberOfAdjacentMines)

            then("인접 지뢰 개수 8을 가진 닫힌 셀을 획득할 수 있다") {
                sut.location shouldBe result.location
                sut.symbol shouldBe result.symbol
                sut.hasLandmine shouldBe result.hasLandmine

                sut.numberOfAdjacentLandmines shouldBe NumberOfAdjacentMines.ZERO
                result.numberOfAdjacentLandmines shouldBe newNumberOfAdjacentMines
            }
        }
    }

    given("지뢰가 심어지지 않은 닫힌 셀에") {
        val location = oneByOneLocation
        val sut = ClosedCell(location)

        `when`("지뢰를 심으면") {
            val result = sut.plantMine()

            then("지뢰를 가진 닫힌 셀이 된다") {
                sut.location shouldBe result.location
                sut.symbol shouldBe result.symbol
                sut.numberOfAdjacentLandmines shouldBe result.numberOfAdjacentLandmines

                sut.hasLandmine shouldBe false
                result.hasLandmine shouldBe true
            }
        }
    }

    given("지뢰가 있는 닫힌 셀은") {
        val location = oneByOneLocation
        val sut = ClosedCell(location = location, hasLandmine = true)

        `when`("오픈 셀 전략으로") {
            val result = sut.findOpenStrategy()

            then("단일 셀 오픈 전략을 가지고 있다") {
                result.shouldBeInstanceOf<SingleOpenCellStrategy>()
            }
        }
    }

    given("지뢰가 없고 인접한 지뢰가 있는 닫힌 셀은") {
        val sut =
            ClosedCell(
                location = oneByOneLocation,
                hasLandmine = false,
                numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
            )

        `when`("오픈 셀 전략으로") {
            val result = sut.findOpenStrategy()

            then("단일 셀 오픈 전략을 가지고 있다") {
                result.shouldBeInstanceOf<SingleOpenCellStrategy>()
            }
        }
    }

    given("지뢰가 없고 인접한 지뢰가 없는 닫힌 셀은") {
        val sut =
            ClosedCell(
                location = oneByOneLocation,
                hasLandmine = false,
                numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO,
            )

        `when`("오픈 셀 전략으로") {
            val result = sut.findOpenStrategy()

            then("단일 셀 오픈 전략을 가지고 있다") {
                result.shouldBeInstanceOf<CascadingOpenCellStrategy>()
            }
        }
    }
})
