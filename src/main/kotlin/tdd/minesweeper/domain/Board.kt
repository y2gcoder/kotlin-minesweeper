package tdd.minesweeper.domain

data class Board(val area: Area, val cells: Cells) {
    fun open(location: Location): Board {
        validateLocation(location)

        val locationIndex = (location.row - 1) * area.width + (location.col - 1)

        val mutableCells = cells.toMutableList()
        mutableCells[locationIndex] = mutableCells[locationIndex].open()

        return this.copy(cells = Cells(mutableCells.toList()))
    }

    private fun validateLocation(location: Location) {
        require(location in allLocations()) {
            "보드 내의 위치가 아닙니다: location=$location"
        }
    }

    private fun allLocations(): List<Location> {
        return (0 until area.height * area.width)
            .map {
                Location(
                    row = (it / area.width) + 1,
                    col = (it % area.width) + 1,
                )
            }
    }
}
