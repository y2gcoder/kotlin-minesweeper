package minesweeper.domain.cell

import minesweeper.domain.strategy.NoOpenCellStrategy
import minesweeper.domain.strategy.OpenCellStrategy

data class LandmineCell(override val location: Location) : Cell {
    override val symbol: Symbol
        get() = Symbol.LANDMINE

    override fun findOpenStrategy(): OpenCellStrategy = NoOpenCellStrategy()
}
