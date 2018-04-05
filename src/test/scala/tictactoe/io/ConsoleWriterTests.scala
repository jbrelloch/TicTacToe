package tictactoe.io

import org.scalatest.{FlatSpec, Matchers}
import tictactoe.models.{GameBoard, Player}

class ConsoleWriterTests extends FlatSpec with Matchers {

  trait MockWriter extends PreDefWriter {
    var messages: Seq[String] = Seq()

    override def write(output: String = ""): Unit = messages = messages :+ output
  }

  "ConsoleWriter.writeIntro" should "print the intro" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter

    testConsoleWriter.writeIntro()

    assert(testConsoleWriter.messages.contains("TIC-TAC-TOE"))
    assert(testConsoleWriter.messages.count(_ == "") == 2)
    assert(testConsoleWriter.messages.size == 3)
  }

  "ConsoleWriter.writeBoard" should "print the board" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter

    val testPlayer = Player("Name", 's')
    val testGameBoard = GameBoard(3, 3)
    testGameBoard.updateAndCheckWinConditions(1, testPlayer)
    testGameBoard.updateAndCheckWinConditions(9, testPlayer)

    testConsoleWriter.writeBoard(testGameBoard)

    assert(testConsoleWriter.messages.contains("s | 2 | 3"))
    assert(testConsoleWriter.messages.contains("4 | 5 | 6"))
    assert(testConsoleWriter.messages.contains("7 | 8 | s"))
    assert(testConsoleWriter.messages.count(_ == "-----------") == 2)
    assert(testConsoleWriter.messages.size == 5)
  }

  "ConsoleWriter.writeWinner" should "print the winner" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter

    val testPlayer = Player("Name", 's')

    testConsoleWriter.writeWinner(testPlayer)

    assert(testConsoleWriter.messages.contains("Congratulations Name! You have won."))
    assert(testConsoleWriter.messages.size == 1)
  }

  "ConsoleWriter.writeError" should "print the error" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter

    testConsoleWriter.writeError("Error")

    assert(testConsoleWriter.messages.contains("Error"))
    assert(testConsoleWriter.messages.count(_ == "-------") == 2)
    assert(testConsoleWriter.messages.size == 3)
  }
}
