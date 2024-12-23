package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberOfAdjacentMines

class CascadingOpenCellStrategy : OpenCellStrategy {
    override fun open(
        originalCells: Cells,
        targetCell: Cell,
    ): Cells {
        val candidates = findOpenCandidates(targetCell, originalCells)
        return Cells(originalCells.map { openCellIfNeeded(candidates, it) })
    }

    private fun findOpenCandidates(
        targetCell: Cell,
        originalCells: Cells,
    ): Set<Cell> =
        buildSet {
            val locationToCellMap = originalCells.associateBy { it.location }
            val queue = ArrayDeque<Cell>().apply { add(targetCell) }
            add(targetCell)

            while (queue.isNotEmpty()) {
                val nextCell = queue.removeFirst()

                getAdjacentCells(nextCell, locationToCellMap)
                    .filterIsInstance<ClosedCell>()
                    .filter { it !in this && !it.hasLandmine }
                    .forEach { adjacentCell ->
                        add(adjacentCell)
                        addQueueIfNumberOfAdjacentLandminesIsZero(adjacentCell, queue)
                    }
            }
        }

    private fun getAdjacentCells(
        nextCell: Cell,
        locationToCellMap: Map<Location, Cell>,
    ): List<Cell> {
        return AdjacentLocationDirection
            .allAdjacentLocations(nextCell.location)
            .mapNotNull { locationToCellMap[it] }
    }

    private fun addQueueIfNumberOfAdjacentLandminesIsZero(
        adjacentCell: ClosedCell,
        queue: ArrayDeque<Cell>,
    ) {
        if (adjacentCell.numberOfAdjacentLandmines == NumberOfAdjacentMines.ZERO) {
            queue.add(adjacentCell)
        }
    }

    private fun openCellIfNeeded(
        candidates: Set<Cell>,
        cell: Cell,
    ): Cell = if (candidates.contains(cell) && cell is ClosedCell) cell.open() else cell
}
