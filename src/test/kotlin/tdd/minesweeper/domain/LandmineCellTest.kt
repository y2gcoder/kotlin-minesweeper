package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.types.shouldBeInstanceOf

class LandmineCellTest : BehaviorSpec({
    given("지뢰 셀은") {
        val sut = LandmineCell()

        `when`("열린 상태가") {
            val result = sut.isOpen()

            then("맞다") {
                result.shouldBeTrue()
            }
        }

        `when`("열면") {
            val result = sut.open()

            then("지뢰 셀이다") {
                result.shouldBeInstanceOf<LandmineCell>()
            }
        }
    }
})
