import scala.io.Source

object FileReader {
  def getLinesFromFile(filename: String): List[String] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines().toList
    source.close()
    lines
  }
}
