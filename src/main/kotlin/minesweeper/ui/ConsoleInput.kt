package minesweeper.ui

import minesweeper.domain.cell.Location

object ConsoleInput {
    fun inputHeight(): Int {
        val height =
            validatedInput(
                prompt = "높이를 입력하세요.\n",
                validate = { input ->
                    val height = input.trim().toIntOrNull()
                    when {
                        height == null -> "숫자를 입력해주세요!"
                        height <= 0 -> "높이는 양수만 입력 가능합니다: $height"
                        else -> null
                    }
                },
                transform = { input -> input.trim().toInt() },
            )
        println()
        return height
    }

    fun inputWidth(): Int {
        val width =
            validatedInput(
                prompt = "너비를 입력하세요.\n",
                validate = { input ->
                    val width = input.trim().toIntOrNull()
                    when {
                        width == null -> "숫자를 입력해주세요!"
                        width <= 0 -> "너비는 양수만 입력 가능합니다: $width"
                        else -> null
                    }
                },
                transform = { input -> input.trim().toInt() },
            )
        println()
        return width
    }

    fun inputCountOfLandmines(): Int {
        val countOfLandmines =
            validatedInput(
                prompt = "지뢰는 몇 개인가요?\n",
                validate = { input ->
                    val countOfLandmines = input.trim().toIntOrNull()
                    when {
                        countOfLandmines == null -> "숫자를 입력해주세요!"
                        countOfLandmines <= 0 -> "지뢰 개수는 양수만 입력 가능합니다: $countOfLandmines"
                        else -> null
                    }
                },
                transform = { input -> input.trim().toInt() },
            )
        println()
        return countOfLandmines
    }

    fun inputSelectLocation(): Location {
        return validatedInput(
            prompt = "open: ",
            validate = { input ->
                val split = input.split(",")
                if (split.size != 2) return@validatedInput "입력 형식이 잘못되었습니다. 쉼표로 구분된 두 개의 숫자를 입력하세요. 예: 2, 3"

                val row = split[0].trim().toIntOrNull()
                val column = split[1].trim().toIntOrNull()

                when {
                    row == null || column == null -> "숫자를 입력해주세요!"
                    row <= 0 || column <= 0 -> "위치는 양수만 입력 가능합니다 : $row, $column"
                    else -> null
                }
            },
            transform = { input ->
                val split = input.split(",")
                val row = split[0].trim().toInt()
                val column = split[1].trim().toInt()
                Location(row = row, column = column)
            },
        )
    }

    private fun <T> validatedInput(
        prompt: String,
        validate: (String) -> String?,
        transform: (String) -> T,
    ): T {
        return generateSequence { readAttempt(prompt, validate, transform) }
            .first { it.isSuccess }
            .getOrThrow()
    }

    private fun <T> readAttempt(
        prompt: String,
        validate: (String) -> String?,
        transform: (String) -> T,
    ): Result<T> {
        print(prompt)
        val input = readln().trim()
        val errorMessage = validate(input)

        if (errorMessage != null) {
            return handleErrorMessage(errorMessage)
        }

        return handleTransform(transform, input)
    }

    private fun <T> handleErrorMessage(errorMessage: String?): Result<T> {
        println(errorMessage)
        return Result.failure(IllegalArgumentException(errorMessage))
    }

    private fun <T> handleTransform(
        transform: (String) -> T,
        input: String,
    ): Result<T> {
        return runCatching { transform(input) }
            .onFailure {
                println("알 수 없는 오류가 발생했습니다: ${it.message}")
            }
    }
}
