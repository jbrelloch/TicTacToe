package tictactoe.models

/**
  * A utility class used to generate a static grid of offsets around a location on a TicTacToe board
  *
  * @param rowOffset - the row offset of the coordinate
  * @param columnOffset - the column offset of the coordinate
  */
case class BoardCoordinateOffset(rowOffset: Int, columnOffset: Int) {
  def getOppositeRowOffset: Int = -rowOffset
  def getOppositeColumnOffset: Int = -columnOffset
  def getOffsetRow(startingRow: Int, offsetMultiplier: Int = 1): Int = startingRow + (rowOffset * offsetMultiplier)
  def getOffsetColumn(startingColumn: Int, offsetMultiplier: Int = 1): Int = startingColumn + (columnOffset * offsetMultiplier)
}