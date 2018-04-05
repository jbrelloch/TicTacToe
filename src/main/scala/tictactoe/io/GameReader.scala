package tictactoe.io

import tictactoe.models.{GameBoard, Player}

/**
  * A reader for a game of TicTacToe
  */
trait GameReader {
  def requestRows(): Int
  def requestColumns(): Int

  def requestPlayerName(symbol: Char): String

  def requestMove(player: Player, gameBoard: GameBoard): Int

  def requestKeepPlaying(): Boolean
}
