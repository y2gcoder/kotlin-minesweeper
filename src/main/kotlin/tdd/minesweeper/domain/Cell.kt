package tdd.minesweeper.domain

interface Cell {
    fun isOpen(): Boolean

    fun open(): Cell
}