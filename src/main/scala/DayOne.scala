import scala.annotation.tailrec
import scala.io.Source

case class DayOne(taskTwo: Boolean = false) {
  private val numbersMap = {
    List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zip(1 to 9).toMap
  }

  def calculate(filename: String): Int = {
    val lines: Seq[String] = Source.fromResource(filename).getLines().toList
    val calculation: String => Int  = if (taskTwo) valuePerLineTwo else valuePerLineOne
    lines.map(calculation(_)).sum
  }

  private def valuePerLineOne(line: String): Int = {
    val digits = line.filter(_.isDigit).map(_.toString)
    s"${digits.head}${digits.last}".toInt
  }

  private def valuePerLineTwo(line: String): Int = {
    val regex = "(one|two|three|four|five|six|seven|eight|nine|[1-9])".r

    @tailrec
    def findNumbers(found: List[String], remaining: String): List[String] = {
      regex.findFirstIn(remaining) match {
        case None => found
        case Some(number) =>
          val updatedRemaining = {
            if (number.length == 1) remaining.tail
            else remaining.substring(remaining.indexOf(number) + 1)
          }
          findNumbers(found :+ number, updatedRemaining)
      }
    }

    val digits = findNumbers(List.empty, line) map { number =>
      if (number.length == 1) number
      else numbersMap(number).toString
    }

    s"${digits.head}${digits.last}".toInt
  }
}

object DayOne_PartOne {
  def main(args: Array[String]): Unit = {
    println(DayOne().calculate("day-one-input.txt"))
  }
}

object DayOne_PartTwo {
  def main(args: Array[String]): Unit = {
    println(DayOne(taskTwo = true).calculate("day-one-input.txt"))
  }
}
