package tdd.minesweeper.domain

data class Board(val area: Area, val cells: Cells) {
    fun open(location: Location): Board {
        validateLocation(location)

        val shouldOpen = findAllShouldOpen(location)

        return this.copy(cells = applyOpen(shouldOpen))
    }

    private fun validateLocation(location: Location) {
        require(location.isValid(area)) {
            "보드 내의 위치가 아닙니다: location=$location"
        }
    }

    private fun findAllShouldOpen(location: Location): Set<Location> {
        val result = mutableSetOf<Location>()
        val queue = ArrayDeque<Location>().apply { add(location) }
        val visited = mutableSetOf<Location>().apply { add(location) }

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            // 현재 위치의 셀 상태 확인
            val currentCell = cells[current.toIndex(area.width)]
            // 이미 열린 셀
            if (currentCell.isOpen()) {
                continue
            }

            // 셀 열기
            result.add(current)

            // 인접 지뢰 수가 0이면 인접 셀 추가 탐색
            if (currentCell.isExpandableToAdjacent()) {
                addExpandableCandidateToQueue(current, visited, queue)
            }
        }
        return result.toSet()
    }

    private fun addExpandableCandidateToQueue(
        current: Location,
        visited: MutableSet<Location>,
        queue: ArrayDeque<Location>,
    ) {
        AdjacentDirection.allAdjacentLocations(current)
            .filter { it.isValid(area) && it !in visited }
            .forEach { adjacent ->
                queue.add(adjacent)
                visited.add(current)
            }
    }

    private fun applyOpen(shouldOpen: Set<Location>): Cells {
        val shouldOpenIndexes = shouldOpen.map { it.toIndex(area.width) }.toSet()
        val result = cells.toMutableList()

        shouldOpenIndexes.forEach { index -> result[index] = cells[index].open() }

        return Cells(result.toList())
    }
}
