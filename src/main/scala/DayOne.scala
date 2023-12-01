import scala.annotation.tailrec
import scala.io.Source

case class DayOne(taskTwo: Boolean = false) {
  private val numbersMap = {
    List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zip(1 to 9).toMap
  }

  def calculate(filename: String): Int = {
    val lines: Seq[String] = getLinesFromFile(filename)
    val calculation: String => Int  = if (taskTwo) valuePerLineTwo else valuePerLineOne
    val values: Seq[Int] = lines.map(calculation(_))
    values.sum
  }

  private def getLinesFromFile(filename: String): List[String] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines().toList
    source.close()
    lines
  }

  protected def valuePerLineOne(line: String): Int = {
    val allIntegers = line.filter(_.isDigit).map(_.toString)
    s"${allIntegers.head}${allIntegers.last}".toInt
  }

  protected def valuePerLineTwo(line: String): Int = {
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

    val asDigits = findNumbers(List.empty, line) map { number =>
      if (number.length == 1) number
      else numbersMap(number).toString
    }

    s"${asDigits.head}${asDigits.last}".toInt
  }
}

object DayOne_PartOne {
  def main(args: Array[String]): Unit = {
    val sum = DayOne().calculate("day-one-input.txt")
    println(sum)
  }
}

object DayOne_PartTwo {
  def main(args: Array[String]): Unit = {
    val sum = DayOne(taskTwo = true).calculate("day-one-input.txt")
    println(sum)
  }
}
