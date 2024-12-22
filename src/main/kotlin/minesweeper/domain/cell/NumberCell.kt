package minesweeper.domain.cell

import minesweeper.domain.strategy.NoOpenCellStrategy
import minesweeper.domain.strategy.OpenCellStrategy

data class NumberCell(
    override val location: Location,
    override val numberOfAdjacentLandmines: NumberOfAdjacentMines,
) : Cell, HasAdjacentLandmines {
    override val symbol: Symbol
        get() = Symbol.from(numberOfAdjacentLandmines)

    override fun findOpenStrategy(): OpenCellStrategy = NoOpenCellStrategy()
}
