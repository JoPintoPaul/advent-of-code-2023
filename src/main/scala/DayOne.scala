import scala.io.Source

case class DayOne() {

  def calibrationValuesSum(filename: String): Int = {
    val calibrationLines: Seq[String] = getLinesFromFile(filename)
    val valuesPerLine: Seq[Int] = calibrationLines.map(valuePerLine)
    valuesPerLine.sum
  }

  private def getLinesFromFile(filename: String): List[String] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines().toList
    source.close()
    lines
  }

  private def valuePerLine(line: String): Int = {
    val allIntegers: Seq[Char] = line.filter(_.isDigit)
    s"${allIntegers.head}${allIntegers.last}".toInt
  }

}

object DayOneTask {
  def main(args: Array[String]): Unit = {
    val sum = DayOne().calibrationValuesSum("day-one-input.txt")
    println(sum)
  }
}
