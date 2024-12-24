package tdd.minesweeper.domain

data class ClosedCell(
    val hasLandmine: Boolean = false,
    override val adjacentMines: AdjacentMines = AdjacentMines(0),
) : Cell {
    constructor(adjacentMines: Int) : this(adjacentMines = AdjacentMines(adjacentMines))

    override fun isOpen(): Boolean = false

    override fun open(): Cell {
        if (hasLandmine) {
            return MineCell()
        }
        return NumberCell(adjacentMines)
    }
}
