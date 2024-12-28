package tdd.minesweeper.domain

data class Location(val row: Int, val col: Int) {
    fun toIndex(width: Int): Int = (row - 1) * width + (col - 1)

    companion object {
        fun from(
            index: Int,
            width: Int,
        ): Location =
            Location(
                row = (index / width) + 1,
                col = (index % width) + 1,
            )
    }
}
