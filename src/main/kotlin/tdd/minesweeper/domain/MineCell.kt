package tdd.minesweeper.domain

class MineCell : Cell {
    override val adjacentMines: AdjacentMines? = null

    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
