package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse

class ClosedCellTest : BehaviorSpec({
    given("닫힌 셀은") {
        val sut = ClosedCell()

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("아니다") {
                result.shouldBeFalse()
            }
        }
    }
})
