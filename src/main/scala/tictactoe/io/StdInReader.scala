package tictactoe.io

/**
  * A reader using the scala.io StdIn library
  */
trait StdInReader {
  val writer: PreDefWriter

  def read(question: String): String = {
    writer.write(question)
    scala.io.StdIn.readLine(">> ")
  }
}
