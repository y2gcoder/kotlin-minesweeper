package tdd.minesweeper.domain

data class NumberCell(val adjacentMines: Int = 0) : Cell {
    init {
        require(adjacentMines in (0..8)) { "인접한 지뢰 수는 0 ~ 8까지만 가능하다: $adjacentMines" }
    }

    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
