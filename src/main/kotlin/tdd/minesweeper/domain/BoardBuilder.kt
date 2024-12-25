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
            require(value <= width * height) { "최대 지뢰 개수는 모든 셀의 수입니다! max=${width * height}, input=$value" }
            this.countOfMines = value
        }

    override fun build(): Board {
        val cells = Cells((0 until height * width).map { ClosedCell() })
        return Board(
            area = Area(height = height, width = width),
            cells = cells,
        )
    }
}
