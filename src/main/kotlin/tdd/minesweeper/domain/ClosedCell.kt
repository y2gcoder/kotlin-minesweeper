package tdd.minesweeper.domain

class ClosedCell : Cell {
    override fun isOpen(): Boolean = false
}