package minesweeper.domain.service

import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location

class GameBoardCellOpener {
    fun openGameBoardCell(
        gameBoard: GameBoard,
        location: Location,
    ): GameBoard {
        val targetCell = gameBoard.find(location) ?: throw IllegalArgumentException("해당 위치를 가진 셀이 존재하지 않습니다: location=$location")
        val openCellStrategy = targetCell.findOpenStrategy()
        return GameBoard.from(openCellStrategy.open(gameBoard.cells, targetCell))
    }
}
