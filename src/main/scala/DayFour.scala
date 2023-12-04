import scala.io.Source

case class DayFour() {

  def calculate(filename: String) = {
    val cards: Seq[String] = Source.fromResource(filename).getLines().toList
    val totalPointsValue: Int = calculateTotalPointsValue(cards)
    totalPointsValue
  }


  private def calculateTotalPointsValue(cards: Seq[String]): Int = {
    val parsedWinnersAndChosen: Seq[(Seq[Int], Seq[Int])] =
      cards map { card =>
        val winnersAndChosen: (Seq[Int], Seq[Int]) = parseCard(card)
        winnersAndChosen
      }

    val numberOfMatchesPerCard: Seq[Int] = parsedWinnersAndChosen map { card =>
      val numberOfMatches: Int = matchWinnersAndChosen(card)
      numberOfMatches
    }

    numberOfMatchesPerCard.map(pointsForMatches).sum
  }

  private def parseCard(card: String): (Seq[Int], Seq[Int]) = ???

  private def matchWinnersAndChosen(card: (Seq[Int], Seq[Int])): Int = ???

  private def pointsForMatches(matches: Int): Int = {
    if (matches == 0) 0 else Math.pow(2, matches -1).toInt
  }
}
