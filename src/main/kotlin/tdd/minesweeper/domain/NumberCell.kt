package tdd.minesweeper.domain

data class NumberCell(val adjacentLandmines: Int = 0) : Cell {
    init {
        require(adjacentLandmines in (0..8)) { "인접 지뢰 수는 0 ~ 8까지만 가능하다: $adjacentLandmines" }
    }

    override fun isOpen(): Boolean = true

    override fun open(): Cell = this
}
