package tictactoe.io

/**
  * A writer using the scala predef defined println function
  */
trait PreDefWriter {
  def write(output: String = ""): Unit = println(output)
}
