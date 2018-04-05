package tictactoe

import tictactoe.io.{ConsoleReader, ConsoleWriter}

/**
  * The executor for a game of TicTacToe
  */
object GameRunner extends App {
  val gameWriter = ConsoleWriter()
  val gameReader = ConsoleReader(gameWriter)

  var playGame = true

  gameWriter.writeIntro()

  val game = Game(gameReader, gameWriter)

  while(playGame) {
    val gameBoard = game.createBoard()

    var gameFinished = false

    while(!gameFinished) {
      val currentPlayer = game.getNextPlayer
      gameBoard.updateAndCheckWinConditions(game.requestMove(currentPlayer, gameBoard), currentPlayer) match {
        case Some(true) =>
          gameWriter.writeWinner(currentPlayer)
          gameFinished = true
        case None =>
          gameWriter.writeDraw()
          gameFinished = true
        case _ =>
      }
    }

    playGame = gameReader.requestKeepPlaying()
  }
}
