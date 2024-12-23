package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell

class NoOpenCellStrategy : OpenCellStrategy {
    override fun open(
        originalCells: Cells,
        targetCell: Cell,
    ): Cells {
        return originalCells
    }
}
