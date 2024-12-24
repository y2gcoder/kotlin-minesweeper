package tdd.minesweeper.domain

data class NumberCell(override val adjacentMines: AdjacentMines = AdjacentMines(0)) : Cell {
    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
