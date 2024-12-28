package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameTest : BehaviorSpec({

    given("높이 5, 너비 5, 지뢰 개수 5를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5

        `when`("게임을 만들면") {
            val sut = Game.from(height = height, width = width, countOfMines = countOfMines)

            then("5 x 5의 영역, 25개의 닫힌 셀과 그 중 5개가 지뢰를 갖고 있는 보드를 갖는다") {
                sut.board.area shouldBe Area(height = height, width = width)
                sut.board.cells.size shouldBe 25
                sut.board.cells.all { cell -> !cell.isOpen() } shouldBe true
                sut.board.cells.filter { it.hasMine() }.size shouldBe 5
            }
        }
    }
})
