package tdd.minesweeper.domain

import tdd.minesweeper.domain.dsl.board

class Game(val board: Board) {
    companion object {
        fun from(
            height: Int,
            width: Int,
            countOfMines: Int,
        ): Game {
            val board =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                }
            return Game(board)
        }
    }
}
