import scala.io.Source

case class DayTwo() {

  private val maxCubesPerColour = Map(
    "red" -> 12,
    "green" -> 13,
    "blue" -> 14
  )

  def calculateSumOfPossibleGames(filename: String): Int = {
    val games = Source.fromResource(filename).getLines().toList
    games.map(possibleGameNumbers).sum
  }

  def calculatePowerOfLowestCubes(filename: String): Int = {
    val games = Source.fromResource(filename).getLines().toList
    val requiredPerColour = games.map(findRequiredByColour)
    requiredPerColour.map(_.values.product).sum
  }

  private def findRequiredByColour(game: String) = {
    val numberOfCubesAndColour: Seq[(Int, String)] =
      parseLineToNumbersAndColour(game.split(":").toList.last)

    val groupedByColour: Map[String, Seq[Int]] =
      numberOfCubesAndColour.groupBy(_._2).map(grouped => (grouped._1, grouped._2.map(_._1)))

    val requiredPerColour =
      groupedByColour.map(grouped => (grouped._1, grouped._2.max))

    requiredPerColour
  }

  private def possibleGameNumbers(line: String): Int = {
    val gameNumberAndSets = line.split(":").toList

    val gameNumber =
      line.split(":").toList.head.split(" ").last.toInt

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
    println(DayTwo().calculatePowerOfLowestCubes("day-two-input.txt"))
  }
}

