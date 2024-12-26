package tdd.minesweeper.domain

class DefaultBoardCellsCreator : BoardCellsCreator {
    override fun createCells(
        area: Area,
        countOfMines: Int,
        inputManualMineLocations: Set<Location>,
    ): Cells {
        val allLocations =
            (0 until area.height * area.width)
                .map {
                    Location(
                        row = (it / area.width) + 1,
                        col = (it % area.width) + 1,
                    )
                }

        var validManualMineLocations = allLocations.intersect(inputManualMineLocations)

        var manualMineCount = validManualMineLocations.size

        if (manualMineCount > countOfMines) {
            validManualMineLocations = validManualMineLocations.take(countOfMines).toMutableSet()
        }

        manualMineCount = validManualMineLocations.size

        val randomMineLocations: Set<Location> =
            (allLocations - validManualMineLocations)
                .shuffled()
                .take(countOfMines - manualMineCount)
                .toSet()

        val mineLocations = validManualMineLocations + randomMineLocations

        val closedCells =
            allLocations
                .map { location ->
                    if (location in mineLocations) ClosedCell(hasMine = true) else ClosedCell()
                }

        val updatedCells =
            closedCells
                .mapIndexed { index, cell ->
                    if (cell.hasMine()) {
                        cell
                    } else {
                        val location = allLocations[index]
                        val directions = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
                        val adjacentMineCount =
                            directions
                                .count { (dx, dy) ->
                                    val adjacentLocation = Location(location.row + dx, location.col + dy)
                                    adjacentLocation in mineLocations
                                }
                        cell.copy(adjacentMines = AdjacentMines(adjacentMineCount))
                    }
                }

        return Cells(updatedCells)
    }
}
