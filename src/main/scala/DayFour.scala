import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.io.Source

case class DayFour(filename: String) {

  private val lines = Source.fromResource(filename).getLines().toList

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

    doPoints(lines, 0)
  }

  def calculateCopies(): Int = {

    val upperBound = lines.length

    def doCopies(gameNumber: Int, unprocessedLines: Seq[String], copies: Map[Int, Int]): Map[Int, Int] = {

      @tailrec
      def updateCopies(copiesToAdd: Seq[Int], ticketCount: Map[Int, Int]): Map[Int, Int] = {
        val updatedTicketCount =
          if (ticketCount.contains(gameNumber)) ticketCount else ticketCount + (gameNumber -> 1)

        if (copiesToAdd.isEmpty) updatedTicketCount
        else {
          val numberToAdd: Int = updatedTicketCount(gameNumber)
          val updatedMap =
            updatedTicketCount + (copiesToAdd.head -> (updatedTicketCount.getOrElse(copiesToAdd.head, 1) + numberToAdd))
          updateCopies(copiesToAdd.tail, updatedMap)
        }
      }

      if (unprocessedLines.isEmpty) copies
      else {
        val numberOfWinningMatches: Int = numberOfMatches(parseWinnersAndChoices(unprocessedLines.head))
        val copiesToMake: Seq[Int] =
          if (numberOfWinningMatches == 0) Nil
          else (1 to numberOfWinningMatches) map {_ + gameNumber} filterNot(_ > upperBound)
        val updatedMap = updateCopies(copiesToMake, copies)

        doCopies(gameNumber + 1, unprocessedLines.tail, updatedMap)
      }
    }

    val result = doCopies(1, lines, Map.empty)
    result.values.sum
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
