trait DayOne {
  def calibrationValuesSum(filename: String): Int = {
    val calibrationLines: Seq[String] = FileReader.getLinesFromFile(filename)
    val valuesPerLine: Seq[Int] = calibrationLines.map(valuePerLine)
    calibrationLines.zip(valuesPerLine).foreach(println)
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
  protected def valuePerLine(line: String): Int = {
    val numberRegex = "(one|two|three|four|five|six|seven|eight|nine|[1-9])".r
    val numbersAsWordsAndDigits: List[String] = numberRegex.findAllIn(line).toList

    val numbersAsDigits: List[String] =  numbersAsWordsAndDigits map { number =>
      if (number.length == 1) number
      else convertWordToDigit(number)
    }
    s"${numbersAsDigits.head}${numbersAsDigits.last}".toInt
  }

  private def convertWordToDigit(word: String): String = {
    word match {
      case "one" => "1"
      case "two" => "2"
      case "three" => "3"
      case "four" => "4"
      case "five" => "5"
      case "six" => "6"
      case "seven" => "7"
      case "eight" => "8"
      case "nine" => "9"
      case _ => throw new Exception(s"This is not a valid digit!!!! $word")
    }
  }
}
