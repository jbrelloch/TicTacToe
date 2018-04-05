package tictactoe.io

import tictactoe.models.{GameBoard, Player}

/**
  * A writer for a game of TicTacToe
  */
trait GameWriter {
  def writeIntro(): Unit

  def writeBoard(gameBoard: GameBoard): Unit

  def writeWinner(player: Player): Unit

  def writeError(error: String): Unit
}
