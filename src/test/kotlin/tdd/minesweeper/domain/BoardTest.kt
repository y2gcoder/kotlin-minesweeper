package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BoardTest : BehaviorSpec({
    given("셀 목록을 받아") {
        val cells = closedCellsBy3x3

        `when`("보드를") {
            val sut = Board(cells = cells)

            then("생성할 수 있다") {
                sut.cells shouldBe cells
            }
        }
    }
})
