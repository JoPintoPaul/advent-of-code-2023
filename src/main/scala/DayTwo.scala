import scala.io.Source

case class DayTwo() {

  private val maxCubesPerColour = Map(
    "red" -> 12,
    "green" -> 13,
    "blue" -> 14
  )

  def calculate(filename: String): Int = {
    val lines = Source.fromResource(filename).getLines().toList
    val linesWithIndex = Source.fromResource(filename).getLines().toList.zip(1 to lines.length)
    linesWithIndex.map(lineWithIndex =>
      checkIfPossible(lineWithIndex._2, lineWithIndex._1)
    ).filter(!_._2).map(_._1).sum
  }

  private def checkIfPossible(gameNumber: Int, line: String): (Int, Boolean) = {
    val numberOfCubesAndColour =
      parseLineToNumbersAndColour(line.split(":").toList.last)

    val impossibles = numberOfCubesAndColour.filter  { gameSet =>
      gameSet._1 > maxCubesPerColour(gameSet._2)
    }

    (gameNumber, impossibles.nonEmpty)
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

