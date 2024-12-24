package tdd.minesweeper.domain

class NumberCell : Cell {
    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
