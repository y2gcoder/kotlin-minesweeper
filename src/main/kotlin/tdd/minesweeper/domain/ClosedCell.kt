package tdd.minesweeper.domain

class ClosedCell(
    val hasLandmine: Boolean = false,
    override val adjacentMines: Int = 0,
) : Cell {
    override fun isOpen(): Boolean = false

    override fun open(): Cell {
        if (hasLandmine) {
            return MineCell()
        }
        return NumberCell(adjacentMines)
    }
}
