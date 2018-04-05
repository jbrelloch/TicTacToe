package tictactoe

import tictactoe.io.{GameReader, GameWriter}
import tictactoe.models.{GameBoard, Player}

/**
  * The class defining the steps and attributes of a game of TicTacToe
  *
  * @param gameReader - the game reader used to retrieve user input
  * @param gameWriter - the game writer used to output information to the user
  */
case class Game(gameReader: GameReader, gameWriter: GameWriter) {
  private val players = List(createPlayer('X'), createPlayer('O'))
  private val playersLoop = Iterator.continually(players).flatten // Warning! Lazy loaded!

  def getNextPlayer: Player = playersLoop.next()

  private def createPlayer(symbol: Char): Player = Player(gameReader.requestPlayerName(symbol), symbol)

  /***
    * Creates a game board for a single game of TicTacToe by requesting user input on size of the board
    *
    * @return The newly created GameBoard
    */
  def createBoard(): GameBoard = {
    val rows = gameReader.requestRows()
    val columns = gameReader.requestColumns()

    if((rows * columns) > 5) {
      GameBoard(rows, columns)
    } else {
      gameWriter.writeError("Error invalid game board size.  Please ensure the game board is big enough to allow a win condition. (>= 2x3)")
      createBoard()
    }
  }

  /**
    * Outputs the current state of the gameboard and request a move from the player specified
    *
    * @param player - the player to request input on their move
    * @param gameBoard - the current game board
    * @return - the players move
    */
  def requestMove(player: Player, gameBoard: GameBoard): Int = {
    gameWriter.writeBoard(gameBoard)
    gameReader.requestMove(player, gameBoard)
  }
}
