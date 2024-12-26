package tdd.minesweeper.domain

interface BoardCellsCreator {
    fun createCells(
        area: Area,
        countOfMines: Int,
        inputManualMineLocations: Set<Location>,
    ): Cells
}
