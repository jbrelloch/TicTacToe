package tictactoe.io

import org.scalatest.{FlatSpec, Matchers}
import tictactoe.models.{GameBoard, Player}

class ConsoleReaderTests extends FlatSpec with Matchers {

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

  //requestRows

  "ConsoleReader.requestRows" should "return number of rows" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("3")

    val rowResponse = testConsoleReader.requestRows()

    assert(testConsoleReader.questions.contains("How many rows?"))
    assert(rowResponse == 3)
  }

  "ConsoleReader.requestRows" should "fail and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("bad value", "3")

    val rowResponse = testConsoleReader.requestRows()

    assert(testConsoleReader.questions.count(_ == "How many rows?") == 2)
    assert(testConsoleWriter.messages.contains("Error parsing number of rows! Please enter a positive integer."))
    assert(rowResponse == 3)
  }

  //requestColumns

  "ConsoleReader.requestColumns" should "return number of columns" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("3")

    val columnResponse = testConsoleReader.requestColumns()

    assert(testConsoleReader.questions.contains("How many columns?"))
    assert(columnResponse == 3)
  }

  "ConsoleReader.requestColumns" should "fail and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("bad value", "3")

    val columnResponse = testConsoleReader.requestColumns()

    assert(testConsoleReader.questions.count(_ == "How many columns?") == 2)
    assert(testConsoleWriter.messages.contains("Error parsing number of columns! Please enter a positive integer."))
    assert(columnResponse == 3)
  }

  //requestPlayerName

  "ConsoleReader.requestPlayerName" should "return a valid name" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("Jason")

    val nameResponse = testConsoleReader.requestPlayerName('x')

    assert(testConsoleReader.questions.contains("What is the name for player x?"))
    assert(nameResponse == "Jason")
  }

  "ConsoleReader.requestPlayerName" should "fail and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("", "Jason")

    val nameResponse = testConsoleReader.requestPlayerName('x')

    assert(testConsoleReader.questions.count(_ == "What is the name for player x?") == 2)
    assert(testConsoleWriter.messages.contains("Error! Please enter a valid name."))
    assert(nameResponse == "Jason")
  }

  //requestMove

  "ConsoleReader.requestMove" should "return a valid move" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("4")

    val testPlayer = Player("Name", 's')
    val testGameBoard = GameBoard(3, 3)

    val moveResponse = testConsoleReader.requestMove(testPlayer, testGameBoard)

    assert(testConsoleReader.questions.contains("Name, choose a box to place an 's' into:"))
    assert(moveResponse == 4)
  }

  "ConsoleReader.requestMove" should "fail on bad input and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("bad response", "4")

    val testPlayer = Player("Name", 's')
    val testGameBoard = GameBoard(3, 3)

    val moveResponse = testConsoleReader.requestMove(testPlayer, testGameBoard)

    assert(testConsoleReader.questions.count(_ == "Name, choose a box to place an 's' into:") == 2)
    assert(testConsoleWriter.messages.contains("Error parsing box selection! Please enter a positive integer."))
    assert(moveResponse == 4)
  }

  "ConsoleReader.requestMove" should "fail on out of bounds number and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("10", "4")

    val testPlayer = Player("Name", 's')
    val testGameBoard = GameBoard(3, 3)

    val moveResponse = testConsoleReader.requestMove(testPlayer, testGameBoard)

    assert(testConsoleReader.questions.count(_ == "Name, choose a box to place an 's' into:") == 2)
    assert(testConsoleWriter.messages.contains("Error! That is not a valid board coordinate."))
    assert(moveResponse == 4)
  }

  //requestKeepPlaying

  "ConsoleReader.requestKeepPlaying" should "return true for yes" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("Yes")

    val kpResponse = testConsoleReader.requestKeepPlaying()

    assert(testConsoleReader.questions.contains("Would you like to play again? (Y)es/(N)o"))
    assert(kpResponse)
  }

  "ConsoleReader.requestKeepPlaying" should "return false for no" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("no")

    val kpResponse = testConsoleReader.requestKeepPlaying()

    assert(testConsoleReader.questions.contains("Would you like to play again? (Y)es/(N)o"))
    assert(!kpResponse)
  }

  "ConsoleReader.requestKeepPlaying" should "fail and try again" in {
    val testConsoleWriter = new ConsoleWriter with MockWriter
    val testConsoleReader = new ConsoleReader(testConsoleWriter) with MockReader

    testConsoleReader.readValues = List("bad response", "yes")

    val kpResponse = testConsoleReader.requestKeepPlaying()

    assert(testConsoleReader.questions.count(_ == "Would you like to play again? (Y)es/(N)o") == 2)
    assert(testConsoleWriter.messages.contains("Error! That is not a valid answer."))
    assert(kpResponse)
  }
}
