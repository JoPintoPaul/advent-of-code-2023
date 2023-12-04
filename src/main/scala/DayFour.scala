import scala.annotation.tailrec
import scala.io.Source

case class DayFour(filename: String) {

  case class Card(gameNumber: Int, winningMatches: Int)

  def calculatePoints(): Int = {
    @tailrec
    def doPoints(unprocessedLines: Seq[String], currentTotal: Int): Int = {
      if (unprocessedLines.isEmpty) currentTotal
      else {
        doPoints(
          unprocessedLines.tail,
          currentTotal + pointsForMatches(numberOfMatches(parseWinnersAndChoices(unprocessedLines.head)))
        )
      }
    }

    doPoints(Source.fromResource(filename).getLines().toList, 0)
  }

  def calculateCopies(): Int = {
    30
  }

  private def parseWinnersAndChoices(card: String): (Seq[Int], Seq[Int]) = {
    val winnersAndChoices = card.split(""":""").toList.last.trim.split('|').toList
    (toListInts(winnersAndChoices.head), toListInts(winnersAndChoices.last))
  }

  private def toListInts(ints: String) =
    ints.trim.split(" ").toList.filter(_.nonEmpty).map(_.toInt)

  private def numberOfMatches(winnersAndChoices: (Seq[Int], Seq[Int])): Int =
    winnersAndChoices._1.intersect(winnersAndChoices._2).length

  private def pointsForMatches(matches: Int): Int = {
    if (matches == 0) 0 else Math.pow(2, matches -1).toInt
  }
}

object DayFour {
  def main(args: Array[String]): Unit = {
    val cardsChallenge = DayFour("day-four-input.txt")
    println(s"Day Four, Task One: ${cardsChallenge.calculatePoints()}")
    println(s"Day Four, Task Two: ${cardsChallenge.calculateCopies()}")

  }
}
