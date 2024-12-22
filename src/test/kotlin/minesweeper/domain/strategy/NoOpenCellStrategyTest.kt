package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cells
import minesweeper.domain.fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell

class NoOpenCellStrategyTest : BehaviorSpec({
    given("원래의 셀 목록과 대상 셀을 받았을 때") {
        val originalCells = Cells(fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell)
        val targetCell = originalCells.random()

        `when`("오픈하지않음 전략을 사용하면") {
            val sut = NoOpenCellStrategy()
            val result = sut.open(originalCells, targetCell)

            then("원래의 셀 목록을 그대로 반환한다") {
                result shouldBe originalCells
            }
        }
    }
})