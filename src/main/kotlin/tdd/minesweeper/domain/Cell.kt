package tdd.minesweeper.domain

interface Cell {
    val adjacentMines: AdjacentMines?

    fun isOpen(): Boolean

    fun open(): Cell
}
