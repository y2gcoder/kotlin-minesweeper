package tdd.minesweeper.ui

import tdd.minesweeper.domain.Game
import tdd.minesweeper.domain.GameState

class MineSweeperApp(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val game: Game = initializeGame()

        playContinue(game)

        handleGameEnd(game)
    }

    private fun initializeGame(): Game {
        val height = inputView.inputHeight()

        val width = inputView.inputWidth()

        val countOfMines = inputView.inputCountOfMines()

        outputView.printGameStarted()

        return Game.from(
            height = height,
            width = width,
            countOfMines = countOfMines,
        )
    }

    private fun playContinue(game: Game) {
        while (game.state() == GameState.CONTINUE) {
            outputView.displayBoard(game.board)

            val location = inputView.inputLocation()

            game.open(location)
        }
    }

    private fun handleGameEnd(game: Game) {
        outputView.printGameEnded(game.state())
    }
}
