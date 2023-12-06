import scala.annotation.tailrec
import scala.io.Source

case class DayFour(filename: String) {

  private val lines = Source.fromResource(filename).getLines().toList

  def calculatePoints(): Int = {
    @tailrec
    def doPoints(unprocessedLines: Seq[String], currentTotal: Int): Int = {
      if (unprocessedLines.isEmpty) currentTotal
      else {
        doPoints(
          unprocessedLines.tail,
          currentTotal + pointsForMatches(getWinningMatchCount(parse(unprocessedLines.head)))
        )
      }
    }

    doPoints(lines, 0)
  }

  def calculateCopies(): Int = {
    @tailrec
    def doCopies(gameNumber: Int, unprocessedLines: Seq[String], copies: Map[Int, Int]): Map[Int, Int] = {
      @tailrec
      def updateCopies(copiesToAdd: Seq[Int], previousTicketCount: Map[Int, Int]): Map[Int, Int] = {
        val ticketCountWithCurrentGame =
          if (previousTicketCount.contains(gameNumber)) previousTicketCount else previousTicketCount + (gameNumber -> 1)
        if (copiesToAdd.isEmpty) ticketCountWithCurrentGame
        else {
          val updatedTicketCount =
            ticketCountWithCurrentGame +
              (copiesToAdd.head ->
                (ticketCountWithCurrentGame.getOrElse(copiesToAdd.head, 1) + ticketCountWithCurrentGame(gameNumber))
                )

          updateCopies(copiesToAdd.tail, updatedTicketCount)
        }
      }

      if (unprocessedLines.isEmpty) copies
      else {
        val winningMatchCount: Int = getWinningMatchCount(parse(unprocessedLines.head))
        val gamesToCopy: Seq[Int] =
          if (winningMatchCount == 0) Nil
          else (1 to winningMatchCount) map {_ + gameNumber} filterNot(_ > lines.length)
        doCopies(gameNumber + 1, unprocessedLines.tail, updateCopies(gamesToCopy, copies))
      }
    }

    doCopies(1, lines, Map.empty).values.sum
  }

  private def parse(card: String): (Seq[Int], Seq[Int]) = {
    val winnersAndChoices = card.split(""":""").toList.last.trim.split('|').toList
    (toListInts(winnersAndChoices.head), toListInts(winnersAndChoices.last))
  }

  private def toListInts(ints: String) =
    ints.trim.split(" ").toList.filter(_.nonEmpty).map(_.toInt)

  private def getWinningMatchCount(winnersAndChoices: (Seq[Int], Seq[Int])): Int =
    winnersAndChoices._1.intersect(winnersAndChoices._2).length

  private def pointsForMatches(matches: Int): Int = {
    if (matches == 0) 0 else Math.pow(2, matches -1).toInt
  }
}

object DayFour {
  def main(args: Array[String]): Unit = {
    val challenge = DayFour("day-four-input.txt")
    println(s"Day Four, Task One: ${challenge.calculatePoints()}")
    println(s"Day Four, Task Two: ${challenge.calculateCopies()}")

  }
}
