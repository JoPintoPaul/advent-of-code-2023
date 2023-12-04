import scala.io.Source

case class DayFour() {

  def calculate(filename: String) = {
    val cards: Seq[String] = Source.fromResource(filename).getLines().toList
    calculateTotalPointsValue(cards)
  }

  private def calculateTotalPointsValue(cards: Seq[String]): Int = {
    cards.map { card =>
      pointsForMatches(numberOfMatches(parseWinnersAndChoices(card)))
    }.sum
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
    println(DayFour().calculate("day-four-input.txt"))
  }
}
