package tictactoe.io

import tictactoe.models.{GameBoard, Player}

/**
  * A writer for a game of TicTacToe that uses predef's println function
  */
case class ConsoleWriter() extends GameWriter with PreDefWriter {

  /**
    * Writes the intro to TicTacToe
    */
  def writeIntro(): Unit = {
    write()
    write("TIC-TAC-TOE")
    write()
  }

  /**
    * Writes the entire game board of a TicTacToe game
    *
    * @param gameBoard
    */
  def writeBoard(gameBoard: GameBoard): Unit = {
    (0 until gameBoard.rows - 1).foreach(x => {
      writeRow(gameBoard.getRow(x), x * gameBoard.columns)
      writeSeperator(gameBoard)
    })
    writeRow(gameBoard.getRow(gameBoard.rows - 1), (gameBoard.rows - 1) * gameBoard.columns)
  }

  /**
    * Writes a row of the game board
    *
    * @param row - the row of the board currently being written
    * @param offsetValue - the 0 based offset of the row
    */
  private def writeRow(row: Array[Option[Char]], offsetValue: Int): Unit = {
    var counter = 0
    write(row.map({
      case null | None => counter += 1
        counter + offsetValue
      case Some(playerSymbol) => counter += 1
        playerSymbol.toString
    }).mkString(" | "))
  }

  /**
    * Writes a row seperator used when drawing the game board
    *
    * @param gameBoard - the board currently being written
    */
  private def writeSeperator(gameBoard: GameBoard): Unit = {
    write((0 until gameBoard.columns).map(_ => "---").mkString("-"))
  }

  /**
    * Writes the winner of the game of TicTacToe
    *
    * @param player -
    */
  def writeWinner(player: Player): Unit = {
    write(s"Congratulations ${player.name}! You have won.")
  }

  /**
    * Writes that the game ended in a draw
    */
  def writeDraw(): Unit = {
    write(s"The game is over and there is no winner.")
  }

  /**
    * Writes an error to the user
    *
    * @param error - the error message to write
    */
  def writeError(error: String): Unit = {
    write("-------")
    write(error)
    write("-------")
  }
}
