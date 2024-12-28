package tdd.minesweeper.domain

data class Board(val area: Area, val cells: Cells) {
    fun open(location: Location): Board {
        validateLocation(location)

        val locationIndex = location.toIndex(area.width)

        val openedCells = cells.toMutableList()
        openedCells[locationIndex] = openedCells[locationIndex].open()

        return this.copy(cells = Cells(openedCells.toList()))
    }

    private fun validateLocation(location: Location) {
        require(location in allLocations()) {
            "보드 내의 위치가 아닙니다: location=$location"
        }
    }

    private fun allLocations(): List<Location> {
        return (0 until area.height * area.width)
            .map {
                Location.from(
                    index = it,
                    width = area.width,
                )
            }
    }
}
