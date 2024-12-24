package tdd.minesweeper.domain

class ClosedCell(val hasLandmine: Boolean = false) : Cell {
    override fun isOpen(): Boolean = false

    override fun open(): Cell {
        if (hasLandmine) {
            return MineCell()
        }
        return NumberCell()
    }
}
