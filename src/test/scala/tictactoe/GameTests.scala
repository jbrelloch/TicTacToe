package tictactoe

import org.scalatest.{FlatSpec, Matchers}
import tictactoe.io.{ConsoleReader, ConsoleWriter, PreDefWriter, StdInReader}

class GameTests extends FlatSpec with Matchers {

  trait MockWriter extends PreDefWriter {
    var messages: Seq[String] = Seq()

    override def write(output: String = ""): Unit = messages = messages :+ output
  }

  trait MockReader extends StdInReader {
    var questions: Seq[String] = Seq()
    var counter = 1

    override def read(question: String): String = {
      questions = questions :+ question
      val readVal = readValues(readCounter)
      readCounter += 1
      readVal
    }

    var readCounter = 0
    var readValues = List.empty[String]
  }

  "GameRunner" should "create 2 players on construction" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("Player1", "Player2")

    val testGame = Game(testConsoleReader, testConsoleWriter)

    val player1 = testGame.getNextPlayer
    assert(player1.name == "Player1")
    assert(player1.symbol == 'X')

    val player2 = testGame.getNextPlayer
    assert(player2.name == "Player2")
    assert(player2.symbol == 'O')
  }

  //createBoard

  "GameRunner.createBoard" should "successfully create a board of the specified size" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("Player1", "Player2", "3", "4")

    val testGame = Game(testConsoleReader, testConsoleWriter)

    val testGameBoard = testGame.createBoard()

    assert(testGameBoard.getRow(0).length == 4)
    assert(testGameBoard.getRow(1).length == 4)
    assert(testGameBoard.getRow(2).length == 4)
    intercept[IndexOutOfBoundsException] {
      testGameBoard.getRow(3)
    }
  }

  "GameRunner.createBoard" should "fail on improperly sized board and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("Player1", "Player2", "2", "2", "3", "4")

    val testGame = Game(testConsoleReader, testConsoleWriter)

    val testGameBoard = testGame.createBoard()

    assert(testConsoleWriter.messages.contains("Error invalid game board size.  Please ensure the game board is big enough to allow a win condition. (>= 2x3)"))

    assert(testGameBoard.getRow(0).length == 4)
    assert(testGameBoard.getRow(1).length == 4)
    assert(testGameBoard.getRow(2).length == 4)
    intercept[IndexOutOfBoundsException] {
      testGameBoard.getRow(3)
    }
  }

  //requestMove

  "GameRunner.requestMove" should "request a move from the specified player" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("Player1", "Player2", "3", "4", "1")

    val testGame = Game(testConsoleReader, testConsoleWriter)

    val moveResponse = testGame.requestMove(testGame.getNextPlayer, testGame.createBoard())

    assert(moveResponse == 1)
    assert(testConsoleWriter.messages.contains("1 | 2 | 3 | 4"))
    assert(testConsoleWriter.messages.contains("5 | 6 | 7 | 8"))
    assert(testConsoleWriter.messages.contains("9 | 10 | 11 | 12"))
    assert(testConsoleWriter.messages.count(_ == "---------------") == 2)
    assert(testConsoleReader.questions.contains("Player1, choose a box to place an 'X' into:"))
  }
}
