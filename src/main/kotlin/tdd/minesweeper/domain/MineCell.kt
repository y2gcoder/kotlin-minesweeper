package tdd.minesweeper.domain

class MineCell : Cell {
    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
