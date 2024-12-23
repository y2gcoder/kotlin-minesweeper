package minesweeper.ui

import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Symbol

object ConsoleOutput {
    private const val DELIMITER = " "

    fun announceGameStarted() = println("지뢰찾기 게임 시작")

    fun printException(exception: Throwable) = println(exception.message)

    fun announceGameWin() = println("Win Game!!🎉🎉🎉")

    fun announceGameLose() = println("Lose Game.🤯")

    fun displayCurrentGameBoard(gameBoard: GameBoard) {
        gameBoard.cells
            .chunked(gameBoard.area.width)
            .forEach { row ->
                println(row.joinToString(DELIMITER) { display(it.symbol) })
            }
        println()
    }

    private fun display(symbol: Symbol): String {
        return when (symbol) {
            Symbol.LANDMINE -> "💥"
            Symbol.CLOSED -> "🌫️"
            Symbol.ZERO -> "0️⃣"
            Symbol.ONE -> "1️⃣"
            Symbol.TWO -> "2️⃣"
            Symbol.THREE -> "3️⃣"
            Symbol.FOUR -> "4️⃣"
            Symbol.FIVE -> "5️⃣"
            Symbol.SIX -> "6️⃣"
            Symbol.SEVEN -> "7️⃣"
            Symbol.EIGHT -> "8️⃣"
        }
    }
}
