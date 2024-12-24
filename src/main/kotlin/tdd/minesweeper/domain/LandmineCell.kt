package tdd.minesweeper.domain

class LandmineCell : Cell {
    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
