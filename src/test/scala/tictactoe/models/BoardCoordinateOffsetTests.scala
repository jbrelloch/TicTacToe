package tictactoe.models

import org.scalatest.{FlatSpec, Matchers}

class BoardCoordinateOffsetTests extends FlatSpec with Matchers {
  "BoardCoordinateOffset.getOppositeRowOffset" should "return opposite row offset" in {
    val testBooardCoordOffset = BoardCoordinateOffset(1, -1)

    val oppositeRowOffset = testBooardCoordOffset.getOppositeRowOffset

    assert(oppositeRowOffset == -1)
  }

  "BoardCoordinateOffset.getOppositeColumnOffset" should "return opposite column offset" in {
    val testBooardCoordOffset = BoardCoordinateOffset(1, -1)

    val oppositeColOffset = testBooardCoordOffset.getOppositeColumnOffset

    assert(oppositeColOffset == 1)
  }

  "BoardCoordinateOffset.getOffsetRow" should "return the offset row value" in {
    val testBooardCoordOffset = BoardCoordinateOffset(1, -1)

    val offsetRow = testBooardCoordOffset.getOffsetRow(2)

    assert(offsetRow == 3)
  }

  "BoardCoordinateOffset.getOffsetColumn" should "return the offset column value" in {
    val testBooardCoordOffset = BoardCoordinateOffset(1, -1)

    val offsetColumn = testBooardCoordOffset.getOffsetColumn(2)

    assert(offsetColumn == 1)
  }
}
