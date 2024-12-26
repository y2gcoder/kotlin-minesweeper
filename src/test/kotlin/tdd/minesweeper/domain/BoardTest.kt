package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.dsl.board

class BoardTest : BehaviorSpec({
    given("영역과 셀 목록을 받아") {
        val area = Area(height = 3, width = 3)
        val cells = closedCellsBy9

        `when`("보드를") {
            val sut = Board(area = area, cells = cells)

            then("생성할 수 있다") {
                sut.cells shouldBe cells
                sut.area shouldBe area
            }
        }
    }

    given("5 x 5 크기, 지뢰 개수 5개의 닫힌 셀만 존재하는 보드에서") {
        val sut =
            board {
                height(5)
                width(5)
                countOfMines(5)
                mineAt(1, 4)
                mineAt(1, 5)
                mineAt(2, 1)
                mineAt(4, 3)
                mineAt(5, 1)
            }

        `when`("보드 내에 없는 위치의 셀을 열려고 하면") {
            val location = Location(0, 0)

            then("예외를 던진다") {
                shouldThrow<IllegalArgumentException> { sut.open(location) }
            }
        }
    }
})
