package tdd.minesweeper.domain

class BoardBuilder : Builder<Board> {
    private var height: Int = 0
    private var width: Int = 0
    private var countOfMines: Int = 0

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

    fun countOfMines(value: Int) =
        apply {
            this.countOfMines = value
        }

    override fun build(): Board {
        require(height > 0) { "높이는 양수여야 합니다! height=$height" }
        require(width > 0) { "높이는 양수여야 합니다! width=$width" }
        require(countOfMines <= width * height) { "최대 지뢰 개수는 모든 셀의 수입니다! max=${width * height}, countOfMines=$countOfMines" }

        val area = Area(height = height, width = width)

        val cells = createCells(area)

        return Board(
            area = area,
            cells = cells,
        )
    }

    private fun createCells(area: Area): Cells {
        val allLocations =
            (0 until area.height * area.width)
                .map {
                    Location(
                        row = (it / area.width) + 1,
                        col = (it % area.width) + 1,
                    )
                }

        val mineLocations: Set<Location> =
            allLocations
                .shuffled()
                .take(countOfMines)
                .toSet()

        return Cells(
            allLocations
                .map { location ->
                    if (location in mineLocations) ClosedCell(hasMine = true) else ClosedCell()
                },
        )
    }
}
