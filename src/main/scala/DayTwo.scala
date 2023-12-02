import scala.io.Source

case class DayTwo() {

  private val maxCubesPerColour = Map(
    "red" -> 12,
    "green" -> 13,
    "blue" -> 14
  )

  def calculate(filename: String): Int = {
    val lines = Source.fromResource(filename).getLines().toList
    lines.map(possibleGameNumbers).sum
  }

  private def possibleGameNumbers(line: String): Int = {
    val gameNumberAndSets = line.split(":").toList

    val gameNumber =
      gameNumberAndSets.head.split(" ").last.toInt

    val numberOfCubesAndColour =
      parseLineToNumbersAndColour(gameNumberAndSets.last)

    val impossibles = numberOfCubesAndColour.filter  { gameSet =>
      gameSet._1 > maxCubesPerColour(gameSet._2)
    }

    if (impossibles.isEmpty) gameNumber else 0
  }

  private def parseLineToNumbersAndColour(line: String): List[(Int, String)] = {
    val setsOfCubes = line.split(":").toList.last

    setsOfCubes.split(";").toList flatMap  { setOfCubes =>
      setOfCubes.split(",") map { numberAndColour =>
        val numberAndColourAsList = numberAndColour.trim.split(" ").toList
        (numberAndColourAsList.head.toInt, numberAndColourAsList.last)
      }
    }
  }
}

object DayTwo {
  def main(args: Array[String]): Unit = {
    println(DayTwo().calculate("day-two-input.txt"))
  }
}

