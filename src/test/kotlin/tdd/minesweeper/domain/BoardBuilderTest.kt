package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BoardBuilderTest : BehaviorSpec({
    given("높이 5, 너비 5, 지뢰 개수 5를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .build()

            then("25개의 닫힌 셀을 만들 수 있다") {
                result.area shouldBe Area(height = height, width = width)
                result.cells.size shouldBe 25
                result.cells.all { it is ClosedCell } shouldBe true
            }

            then("25개의 셀 중 5개는 지뢰를 가지고 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
            }
        }
    }

    given("높이를") {
        val width = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.width(width).countOfMines(countOfMines).build()
                }
            }
        }
        `when`("양수로 입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.height(0).width(width).countOfMines(countOfMines)
                }
            }
        }
    }

    given("너비를 입력하지 않으면") {
        val height = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.height(height).countOfMines(countOfMines).build()
                }
            }
        }
        `when`("양수로 입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.width(0).height(height).countOfMines(countOfMines)
                }
            }
        }
    }

    given("보드의 전체 셀 수가 5x5 = 25개일 때") {
        val height = 5
        val width = 5
        val countOfMines = 26
        val sut = BoardBuilder()

        `when`("지뢰를 보드의 전체 셀 수보다 더 많이") {
            then("만들 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    sut.height(height).width(width).countOfMines(countOfMines).build()
                }
            }
        }
    }

    given("높이 5, 너비 5, 지뢰 개수 5개를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5
        val sut = BoardBuilder()

        `when`("5개의 서로 다른 지뢰위치를 수동으로 받아 보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .mineAt(1, 4)
                    .mineAt(1, 5)
                    .mineAt(2, 1)
                    .mineAt(4, 3)
                    .mineAt(5, 1)
                    .build()

            then("해당 위치에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                        Location(5, 1),
                    )

                mineLocations.forEach { location ->
                    val cell = result.cells[(location.row - 1) * width + (location.col - 1)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("4개의 서로 다른 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .mineAt(1, 4)
                    .mineAt(1, 5)
                    .mineAt(2, 1)
                    .mineAt(4, 3)
                    .build()

            then("4개의 수동 위치와 1개의 랜덤 위치에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                    )
                mineLocations.forEach { location ->
                    val cell = result.cells[(location.row - 1) * width + (location.col - 1)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("5개의 똑같은 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .mineAt(1, 4)
                    .mineAt(1, 4)
                    .mineAt(1, 4)
                    .mineAt(1, 4)
                    .mineAt(1, 4)
                    .build()

            then("1개의 수동 위치와 4개의 랜덤 위치에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                    )
                mineLocations.forEach { location ->
                    val cell = result.cells[(location.row - 1) * width + (location.col - 1)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("6개의 서로 다른 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .mineAt(1, 4)
                    .mineAt(1, 5)
                    .mineAt(2, 1)
                    .mineAt(4, 3)
                    .mineAt(5, 1)
                    .mineAt(5, 2)
                    .build()

            then("처음에 받은 지뢰 위치 5개에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                        Location(5, 1),
                    )

                mineLocations.forEach { location ->
                    val cell = result.cells[(location.row - 1) * width + (location.col - 1)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("1개의 보드 내 존재하지 않는 위치와 5개의 서로 다른 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                sut
                    .height(height)
                    .width(width)
                    .countOfMines(countOfMines)
                    .mineAt(0, 4)
                    .mineAt(1, 4)
                    .mineAt(1, 5)
                    .mineAt(2, 1)
                    .mineAt(4, 3)
                    .mineAt(5, 1)
                    .build()

            then("처음에 받은 지뢰 위치 5개에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                        Location(5, 1),
                    )

                mineLocations.forEach { location ->
                    val cell = result.cells[(location.row - 1) * width + (location.col - 1)]
                    cell.hasMine() shouldBe true
                }
            }
        }
    }
})
