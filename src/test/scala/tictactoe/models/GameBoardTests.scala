package tictactoe.models

import org.scalatest.{FlatSpec, Matchers}

class GameBoardTests extends FlatSpec with Matchers {
  //updateAndCheckWinConditions

  "GameBoard.updateAndCheckWinConditions" should "return Some(False) on no winner yet" in {
    val testGameBoard = GameBoard(3,3)
    val testPlayer = Player("Name", 's')

    val winnerCheck = testGameBoard.updateAndCheckWinConditions(3, testPlayer)

    assert(winnerCheck.isDefined)
    assert(!winnerCheck.get)
  }

  "GameBoard.updateAndCheckWinConditions" should "return Some(True) on winner with immediate vicinity check" in {
    val testGameBoard = GameBoard(3,3)
    val testPlayer = Player("Name", 's')

    val winnerCheck1 = testGameBoard.updateAndCheckWinConditions(1, testPlayer)

    assert(winnerCheck1.isDefined)
    assert(!winnerCheck1.get)

    val winnerCheck2 = testGameBoard.updateAndCheckWinConditions(3, testPlayer)

    assert(winnerCheck2.isDefined)
    assert(!winnerCheck2.get)

    val winnerCheck3 = testGameBoard.updateAndCheckWinConditions(2, testPlayer)

    assert(winnerCheck3.isDefined)
    assert(winnerCheck3.get)
  }

  "GameBoard.updateAndCheckWinConditions" should "return Some(False) on winner with extended range check" in {
    val testGameBoard = GameBoard(3,3)
    val testPlayer = Player("Name", 's')

    val winnerCheck1 = testGameBoard.updateAndCheckWinConditions(1, testPlayer)

    assert(winnerCheck1.isDefined)
    assert(!winnerCheck1.get)

    val winnerCheck2 = testGameBoard.updateAndCheckWinConditions(2, testPlayer)

    assert(winnerCheck2.isDefined)
    assert(!winnerCheck2.get)

    val winnerCheck3 = testGameBoard.updateAndCheckWinConditions(3, testPlayer)

    assert(winnerCheck3.isDefined)
    assert(winnerCheck3.get)
  }

  "GameBoard.updateAndCheckWinConditions" should "return None on draw" in {
    val testGameBoard = GameBoard(2,3)
    val testPlayer1 = Player("Name1", 'x')
    val testPlayer2 = Player("Name2", 'o')

    val winnerCheck1 = testGameBoard.updateAndCheckWinConditions(1, testPlayer1)

    assert(winnerCheck1.isDefined)
    assert(!winnerCheck1.get)

    val winnerCheck2 = testGameBoard.updateAndCheckWinConditions(2, testPlayer2)

    assert(winnerCheck2.isDefined)
    assert(!winnerCheck2.get)

    val winnerCheck3 = testGameBoard.updateAndCheckWinConditions(3, testPlayer1)

    assert(winnerCheck3.isDefined)
    assert(!winnerCheck3.get)

    val winnerCheck4 = testGameBoard.updateAndCheckWinConditions(4, testPlayer2)

    assert(winnerCheck4.isDefined)
    assert(!winnerCheck4.get)

    val winnerCheck5 = testGameBoard.updateAndCheckWinConditions(5, testPlayer1)

    assert(winnerCheck5.isDefined)
    assert(!winnerCheck5.get)

    val winnerCheck6 = testGameBoard.updateAndCheckWinConditions(6, testPlayer2)

    assert(winnerCheck6.isEmpty)
  }

  //isValidGameCoordinate
  "GameBoard.isValidGameCoordinate" should "return true on valid game coordinate" in {
    val testGameBoard = GameBoard(3,3)

    assert(testGameBoard.isValidGameCoordinate(3))
  }

  "GameBoard.isValidGameCoordinate" should "return false on invalid board coordinate" in {
    val testGameBoard = GameBoard(3,3)

    assert(!testGameBoard.isValidGameCoordinate(10))
  }

  "GameBoard.isValidGameCoordinate" should "return false on previously filled board coordinate" in {
    val testGameBoard = GameBoard(3,3)
    val testPlayer = Player("Name", 's')

    testGameBoard.updateAndCheckWinConditions(3, testPlayer)

    assert(!testGameBoard.isValidGameCoordinate(3))
  }

  //getRow
  "GameBoard.getRow" should "return available rows" in {
    val testGameBoard = GameBoard(3,3)

    assert(testGameBoard.getRow(0).length == 3)
    assert(testGameBoard.getRow(1).length == 3)
    assert(testGameBoard.getRow(2).length == 3)
    intercept[IndexOutOfBoundsException] {
      testGameBoard.getRow(3)
    }
  }
}
