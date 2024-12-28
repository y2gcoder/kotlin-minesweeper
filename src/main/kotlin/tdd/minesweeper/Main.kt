package tdd.minesweeper

import tdd.minesweeper.ui.ConsoleInputView
import tdd.minesweeper.ui.ConsoleOutputView
import tdd.minesweeper.ui.MineSweeperApp

fun main() {
    val app =
        MineSweeperApp(
            inputView = ConsoleInputView,
            outputView = ConsoleOutputView,
        )

    app.play()
}
