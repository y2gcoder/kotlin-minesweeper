package tdd.minesweeper.domain

interface Cell {
    val adjacentMines: Int?

    fun isOpen(): Boolean

    fun open(): Cell
}
