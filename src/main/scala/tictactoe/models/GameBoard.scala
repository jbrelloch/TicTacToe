package tictactoe.models

/**
  * A game board for a single game of TicTacToe
  *
  * @param rows - the number of rows in the game
  * @param columns - the number of columns in the game
  */
case class GameBoard(rows: Int, columns: Int) {
  private val board = Array.ofDim[Option[Char]](rows, columns)
  private var currentMoves = 0

  def updateAndCheckWinConditions(gameCoordinate: Int, player: Player): Option[Boolean] = {
    currentMoves += 1
    val (row, column) = calculateBoardCoordinates(gameCoordinate)

    board(row).update(column, Some(player.symbol))

    val winner = checkForWinner(row, column)
    if(currentMoves == rows*columns && !winner) None else Some(winner)
  }

  def isValidGameCoordinate(gameCoordinate: Int): Boolean = {
    val (row, column) = calculateBoardCoordinates(gameCoordinate)
    isValidBoardCoordinate(row, column) && (board(row)(column) == null || board(row)(column).isEmpty)
  }

  def getRow(row: Int): Array[Option[Char]] = {
    board(row)
  }

  private def isValidBoardCoordinate(row: Int, column: Int): Boolean = {
    row < rows && row >= 0 && column < columns && column >= 0
  }

  private def calculateBoardCoordinates(gameCoordinate: Int): (Int, Int) = {
    val (row, column) = BigInt(gameCoordinate - 1) /% columns
    (row.toInt, column.toInt)
  }

  private def checkForWinner(updatedRow: Int, updatedColumn: Int): Boolean = {
    val matchingNearbyCoordinates = generatePotentialCoordinates(updatedRow, updatedColumn)

    if(containsOppositeCoordinate(matchingNearbyCoordinates)) {
      true
    } else {
      checkOuterCoordinates(updatedRow, updatedColumn, matchingNearbyCoordinates)
    }
  }

  private val surroundingCoordinates = Array(
    BoardCoordinateOffset(-1, -1),
    BoardCoordinateOffset(-1, 0),
    BoardCoordinateOffset(-1, 1),
    BoardCoordinateOffset(0, -1),
    BoardCoordinateOffset(0, 1),
    BoardCoordinateOffset(1, -1),
    BoardCoordinateOffset(1, 0),
    BoardCoordinateOffset(1, 1)
  )

  private def generatePotentialCoordinates(updatedRow: Int, updatedColumn: Int): Array[BoardCoordinateOffset] = {
    surroundingCoordinates.filter(coord => {
      val offsetRow = coord.getOffsetRow(updatedRow)
      val offsetColumn =coord.getOffsetColumn(updatedColumn)
      isValidBoardCoordinate(offsetRow, offsetColumn) &&
        board(offsetRow)(offsetColumn) == board(updatedRow)(updatedColumn) // filter other teams symbols
    })
  }

  private def containsOppositeCoordinate(potentialCoordinates: Array[BoardCoordinateOffset]): Boolean = {
    potentialCoordinates.exists(currentCoordinate => {
      potentialCoordinates.exists(comparisonCoordinate => {
        comparisonCoordinate.rowOffset == currentCoordinate.getOppositeRowOffset &&
          comparisonCoordinate.columnOffset == currentCoordinate.getOppositeColumnOffset
      })
    })
  }

  private def checkOuterCoordinates(updatedRow: Int, updatedColumn: Int, potentialCoordinates: Array[BoardCoordinateOffset]): Boolean = {
    potentialCoordinates.exists(currentCoordinate => {
      val outerRowCoord = currentCoordinate.getOffsetRow(updatedRow, 2)
      val outerColumnCoord = currentCoordinate.getOffsetColumn(updatedColumn, 2)

      isValidBoardCoordinate(outerRowCoord, outerColumnCoord) && board(updatedRow)(updatedColumn) == board(outerRowCoord)(outerColumnCoord)
    })
  }
}
