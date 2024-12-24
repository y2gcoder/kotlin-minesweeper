package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.types.shouldBeInstanceOf

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

    given("지뢰가 없는 닫힌 셀을") {
        val sut = ClosedCell()

        `when`("열면") {
            val result = sut.open()

            then("숫자 셀이 된다") {
                result.shouldBeInstanceOf<NumberCell>()
            }
        }
    }

    given("지뢰가 있는 닫힌 셀을") {
        val sut =
            ClosedCell(
                hasLandmine = true,
            )

        `when`("열면") {
            val result = sut.open()

            then("지뢰 셀이 된다") {
                result.shouldBeInstanceOf<MineCell>()
            }
        }
    }
})
