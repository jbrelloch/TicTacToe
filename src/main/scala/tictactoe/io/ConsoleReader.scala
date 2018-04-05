package tictactoe.io

import tictactoe.models.{GameBoard, Player}

import scala.util.Try

/**
  * A reader for a game of TicTacToe that uses scala.io's StdIn library
  *
  * @param writer - the writer used to output error messages to the user
  */
case class ConsoleReader(writer: ConsoleWriter) extends GameReader with StdInReader {

  def requestRows(): Int = {
    val input = read("How many rows?")

    Try(input.toInt).toOption match {
      case Some(numRows) => numRows
      case None =>
        writer.writeError("Error parsing number of rows! Please enter a positive integer.")
        requestRows()
    }
  }

  def requestColumns(): Int = {
    val input = read("How many columns?")

    Try(input.toInt).toOption match {
      case Some(numColumns) =>
        writer.write()
        numColumns
      case None =>
        writer.writeError("Error parsing number of columns! Please enter a positive integer.")
        requestColumns()
    }
  }

  def requestPlayerName(symbol: Char): String = {
    val input = read(s"What is the name for player $symbol?")

    if(input.nonEmpty) {
      writer.write()
      input
    } else {
      writer.writeError("Error! Please enter a valid name.")
      requestPlayerName(symbol)
    }
  }

  def requestMove(player: Player, gameBoard: GameBoard): Int = {
    val input = read(s"${player.name}, choose a box to place an '${player.symbol}' into:")

    Try(input.toInt).toOption match {
      case Some(boxSelected) =>
        if(gameBoard.isValidGameCoordinate(boxSelected)) {
          writer.write()
          boxSelected
        } else {
          writer.writeError("Error! That is not a valid board coordinate.")
          requestMove(player, gameBoard)
        }
      case None =>
        writer.writeError("Error parsing box selection! Please enter a positive integer.")
        requestMove(player, gameBoard)
    }
  }

  def requestKeepPlaying(): Boolean = {
    val input = read(s"Would you like to play again? (Y)es/(N)o").toLowerCase
    writer.write()

    if(input.nonEmpty && (input == "yes" || input == "y")) {
      true
    } else if(input == "no" || input == "n") {
      false
    } else {
      writer.writeError("Error! That is not a valid answer.")
      requestKeepPlaying()
    }
  }
}
