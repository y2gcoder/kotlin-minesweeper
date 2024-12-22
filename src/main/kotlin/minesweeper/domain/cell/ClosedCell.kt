package minesweeper.domain.cell

import minesweeper.domain.strategy.CascadingOpenCellStrategy
import minesweeper.domain.strategy.OpenCellStrategy
import minesweeper.domain.strategy.SingleOpenCellStrategy

data class ClosedCell(
    override val location: Location,
    val hasLandmine: Boolean = false,
    override val numberOfAdjacentLandmines: NumberOfAdjacentMines = NumberOfAdjacentMines.ZERO,
) : Cell, HasAdjacentLandmines {
    override val symbol: Symbol
        get() = Symbol.CLOSED

    override fun findOpenStrategy(): OpenCellStrategy =
        when {
            hasLandmine -> SingleOpenCellStrategy()
            numberOfAdjacentLandmines > NumberOfAdjacentMines.ZERO -> SingleOpenCellStrategy()
            else -> CascadingOpenCellStrategy()
        }

    fun open(): Cell {
        if (hasLandmine) {
            return LandmineCell(location)
        }
        return NumberCell(location, numberOfAdjacentLandmines)
    }

    fun withNumberOfAdjacentLandmines(newNumberOfAdjacentMines: NumberOfAdjacentMines): ClosedCell {
        return this.copy(numberOfAdjacentLandmines = newNumberOfAdjacentMines)
    }

    fun plantMine(): ClosedCell = this.copy(hasLandmine = true)
}
