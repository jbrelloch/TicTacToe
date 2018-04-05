package tictactoe.models

import org.scalatest.{FlatSpec, Matchers}

class PlayerTests extends FlatSpec with Matchers {
  "Player" should "successfully construct itself" in {
    val testPlayer = Player("name", 'x')

    assert(testPlayer.name == "name")
    assert(testPlayer.symbol == 'x')
  }
}
