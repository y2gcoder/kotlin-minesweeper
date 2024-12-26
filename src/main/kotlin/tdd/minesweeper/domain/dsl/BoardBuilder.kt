package tdd.minesweeper.domain.dsl

import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.Board
import tdd.minesweeper.domain.BoardCellsCreator
import tdd.minesweeper.domain.Builder
import tdd.minesweeper.domain.DefaultBoardCellsCreator
import tdd.minesweeper.domain.Location

@BoardDslMaker
class BoardBuilder(private val boardCellsCreator: BoardCellsCreator = DefaultBoardCellsCreator()) : Builder<Board> {
    private var height: Int = 0
    private var width: Int = 0
    private var countOfMines: Int = 0
    private val manualMineLocations: MutableSet<Location> = mutableSetOf()

    fun height(value: Int) =
        apply {
            require(value > 0) { "높이는 양수여야 합니다! input=$value" }
            this.height = value
        }

    fun width(value: Int) =
        apply {
            require(value > 0) { "높이는 양수여야 합니다! input=$value" }
            this.width = value
        }

    fun countOfMines(value: Int) = apply { this.countOfMines = value }

    fun mineAt(
        row: Int,
        col: Int,
    ) = apply { this.manualMineLocations.add(Location(row, col)) }

    override fun build(): Board {
        require(height > 0) { "높이는 양수여야 합니다! height=$height" }
        require(width > 0) { "높이는 양수여야 합니다! width=$width" }
        require(countOfMines <= width * height) { "최대 지뢰 개수는 모든 셀의 수입니다! max=${width * height}, countOfMines=$countOfMines" }

        val area = Area(height = height, width = width)

        val cells =
            boardCellsCreator.createCells(
                area = area,
                countOfMines = countOfMines,
                inputManualMineLocations = manualMineLocations.toSet(),
            )

        return Board(
            area = area,
            cells = cells,
        )
    }
}
