package minesweeper.domain.cell

import minesweeper.domain.strategy.OpenCellStrategy

interface Cell {
    val location: Location

    val symbol: Symbol

    fun findOpenStrategy(): OpenCellStrategy
}
