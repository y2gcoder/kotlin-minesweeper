package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue

class LandmineCellTest : BehaviorSpec({
    given("지뢰 셀은") {
        val sut = LandmineCell()

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("맞다") {
                result.shouldBeTrue()
            }
        }
    }
})
