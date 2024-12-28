package tdd.minesweeper.ui

import tdd.minesweeper.domain.Location

object ConsoleInputView : InputView {
    override fun inputHeight(): Int {
        println("높이를 입력하세요.")
        val result = readln().trim().toInt()
        println()
        return result
    }

    override fun inputWidth(): Int {
        println("너비를 입력하세요.")
        val result = readln().trim().toInt()
        println()
        return result
    }

    override fun inputCountOfMines(): Int {
        println("지뢰는 몇 개인가요?")
        val result = readln().trim().toInt()
        println()
        return result
    }

    override fun inputLocation(): Location {
        print("open: ")
        val input = readln().trim()
        val split = input.split(",")
        val numbers = split.map { it.trim().toInt() }

        return Location(
            row = numbers[0],
            col = numbers[1],
        )
    }
}
