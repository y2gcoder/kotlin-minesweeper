package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class AdjacentMinesTest : BehaviorSpec({
    given("인접 지뢰 수는") {
        `when`("0 ~ 8 의 값만") {
            then("허용한다") {
                listOf(-1, 9).forEach { adjacentMines ->
                    shouldThrow<IllegalArgumentException> { AdjacentMines(adjacentMines) }
                }
            }
        }
    }
})
