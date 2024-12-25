package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

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
})
