package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberCell
import minesweeper.domain.cell.NumberOfAdjacentMines

class GameBoardTest : BehaviorSpec({
    given("높이 10, 너비 10으로") {
        val height = 10
        val width = 10

        `when`("게임판을 만들면") {
            val sut = GameBoard.of(height = height, width = width)

            then("게임판은 높이 10, 너비 10의 지역을 가진다") {
                val area = sut.area
                area shouldBe Area(height = height, width = width)
            }

            then("게임판은 row = 1 ~ 높이, column = 1 ~ 너비 의 위치를 가진 셀들을 가진다") {
                val result = sut.cells

                result shouldHaveSize height * width
                result.forEach { cell ->
                    cell.location.row in (1..height)
                    cell.location.column in (1..width)
                }
            }
        }
    }

    given("1보다 작은 높이로") {
        val height = 0
        val width = 10

        `when`("게임판을 생성하려고 하면") {
            then("예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.of(
                        height = height,
                        width = width,
                    )
                }
            }
        }
    }

    given("1보다 작은 너비로") {
        val height = 10
        val width = 0

        `when`("게임판을 생성하려고 하면") {
            then("예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.of(
                        height = height,
                        width = width,
                    )
                }
            }
        }
    }

    given("첫번째 행의 셀이 3개, 두번째 행의 셀이 2개 세번째 행의 셀이 3개인 전체 행의 길이가 일정치 않은 셀 목록으로") {
        val cells =
            listOf(
                ClosedCell(oneByOneLocation),
                ClosedCell(oneByTwoLocation),
                ClosedCell(oneByThreeLocation),
                ClosedCell(twoByOneLocation),
                ClosedCell(twoByTwoLocation),
                ClosedCell(threeByOneLocation),
                ClosedCell(threeByTwoLocation),
                ClosedCell(threeByThreeLocation),
            )

        `when`("게임판을 생성하려고 하면") {
            then("예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.from(cells)
                }
            }
        }
    }

    given("3x3 셀로 만든 게임판에서") {
        val cells = threeByThreeCells
        val sut = GameBoard.from(cells)

        `when`("(1,1)의 위치로 셀을 찾으면") {
            val location = Location(row = 1, column = 1)
            val result = sut.find(location)

            then("해당 위치를 가진 셀을 찾을 수 있다") {
                result shouldBe cells.find { it.location.row == 1 && it.location.column == 1 }
            }
        }

        `when`("게임판 내에 존재하지 않는 위치로 셀을 찾으려고 하면") {
            val notFoundLocations =
                listOf(
                    Location(row = 0, column = 0),
                    Location(row = 3, column = 4),
                    Location(row = 4, column = 1),
                )

            then("셀을 찾을 수 없다") {
                notFoundLocations.forEach { location ->
                    sut.find(location) shouldBe null
                }
            }
        }

        `when`("모든 셀을 열면") {
            val result = sut.openAll()

            then("닫힌 셀이 모두 숫자 셀로 변한 3x3 게임판을 반환한다") {
                result.cells shouldHaveSize 9
                result.cells shouldContainExactlyInAnyOrder
                    listOf(
                        NumberCell(oneByOneLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(oneByTwoLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(oneByThreeLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(twoByOneLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(twoByTwoLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(twoByThreeLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(threeByOneLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(threeByTwoLocation, NumberOfAdjacentMines.ZERO),
                        NumberCell(threeByThreeLocation, NumberOfAdjacentMines.ZERO),
                    )
            }
        }
    }

    given("게임판에 열린 지뢰 셀이 하나라도 존재하면") {
        val cells =
            listOf(
                ClosedCell(
                    location = oneByOneLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                ClosedCell(
                    location = oneByTwoLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                ClosedCell(
                    location = twoByOneLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                LandmineCell(twoByTwoLocation),
            )
        val sut = GameBoard.from(cells)

        `when`("해당 게임 상태는") {
            val result = sut.gameState()
            then("패배 상태이다") {
                result shouldBe GameState.LOSE
            }
        }
    }

    given("게임판에 닫힌 셀 수와 전체 지뢰 셀 수가 똑같으면") {
        val cells =
            listOf(
                NumberCell(
                    location = oneByOneLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                NumberCell(
                    location = oneByTwoLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                NumberCell(
                    location = twoByOneLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                ClosedCell(
                    location = twoByTwoLocation,
                    hasLandmine = true,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO,
                ),
            )
        val sut = GameBoard.from(cells)

        `when`("해당 게임 상태는") {
            val result = sut.gameState()

            then("승리 상태이다") {
                result shouldBe GameState.WIN
            }
        }
    }

    given("게임판에 닫힌 셀이 전체 지뢰 수보다 많으면") {
        val cells =
            listOf(
                NumberCell(
                    location = oneByOneLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                NumberCell(
                    location = oneByTwoLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                ClosedCell(
                    location = twoByOneLocation,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines(1),
                ),
                ClosedCell(
                    location = twoByTwoLocation,
                    hasLandmine = true,
                    numberOfAdjacentLandmines = NumberOfAdjacentMines.ZERO,
                ),
            )
        val sut = GameBoard.from(cells)

        `when`("해당 게임 상태는") {
            val result = sut.gameState()

            then("지속 상태이다") {
                result shouldBe GameState.CONTINUE
            }
        }
    }
})
