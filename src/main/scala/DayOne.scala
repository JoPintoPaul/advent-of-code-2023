import scala.annotation.tailrec
import scala.io.Source
import scala.util.Try

trait DayOne {
  def calibrationValuesSum(filename: String): Int = {
    val calibrationLines: Seq[String] = FileReader.getLinesFromFile(filename)
    val valuesPerLine: Seq[Int] = calibrationLines.map(valuePerLine)
    valuesPerLine.sum
  }

  protected def valuePerLine(line: String): Int
}

case class DayOneTaskOne() extends DayOne {

  protected def valuePerLine(line: String): Int = {
    val allIntegers = line.filter(_.isDigit).map(_.toString)
    s"${allIntegers.head}${allIntegers.last}".toInt
  }
}

case class DayOneTaskTwo() extends DayOne {
  private val numbersMap = {
    List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zip(1 to 9).toMap
  }

  protected def valuePerLine(line: String): Int = {
    val numberRegex = "(one|two|three|four|five|six|seven|eight|nine|[1-9])".r

    @tailrec
    def findNumbers(found: List[String], remaining: String): List[String] = {
      numberRegex.findFirstIn(remaining) match {
        case None => found
        case Some(number) =>
          val updatedRemaining = {
            if (number.length == 1) remaining.tail
            else remaining.substring(remaining.indexOf(number) + 1)
          }
          findNumbers(found :+ number, updatedRemaining)
      }
    }

    val numbersAsDigits = findNumbers(List.empty, line) map { number =>
      if (number.length == 1) number
      else numbersMap(number).toString
    }

    s"${numbersAsDigits.head}${numbersAsDigits.last}".toInt
  }
}

object FileReader {
  def getLinesFromFile(filename: String): List[String] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines().toList
    source.close()
    lines
  }
}

object DayOne_PartOne {
  def main(args: Array[String]): Unit = {
    val sum = DayOneTaskOne().calibrationValuesSum("day-one-input.txt")
    println(sum)
  }
}

object DayOne_PartTwo {
  def main(args: Array[String]): Unit = {
    val sum = DayOneTaskTwo().calibrationValuesSum("day-one-input.txt")
    println(sum)
  }
}
