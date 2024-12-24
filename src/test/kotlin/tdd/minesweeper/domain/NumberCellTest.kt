package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue

class NumberCellTest : BehaviorSpec({
    given("숫자 셀은") {
        val sut = NumberCell()

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("맞다") {
                result.shouldBeTrue()
            }
        }
    }
})
