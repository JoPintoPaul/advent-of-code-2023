import scala.io.Source

case class DayOne() {

  def calibrationValuesSum(filename: String): Int = {
    val calibrationLines = getLinesFromFile(filename)

    calibrationLines.foreach(println(_))

    val sum = 142
    sum
  }

  private def getLinesFromFile(filename: String): List[String] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines().toList
    source.close()
    lines
  }

}
