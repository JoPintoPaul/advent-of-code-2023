import scala.io.Source
import scala.util.Try

case class DayThree() {

  def calculate(filename: String) = {
    val schematicLines: Seq[String] = Source.fromResource(filename).getLines().toList

    val linesIndices: Seq[Int] = schematicLines.indices
    val columnIndices = schematicLines.head.indices

    val symbolCoordinates = (for {
      verticalIndex: Int <- linesIndices
      horizontalIndex: Int <- columnIndices
    } yield {
      val char = schematicLines(verticalIndex)(horizontalIndex)
      val charIsASymbol = !char.isDigit && char.toString != "."
      if (charIsASymbol) Some(verticalIndex, horizontalIndex) else None
    }).flatten

    symbolCoordinates foreach { symbol =>
      val (vertical, horizontal) = symbol
      val neighbours = {
        val aboveLeft: Try[Char] = Try(schematicLines(vertical -1)(horizontal -1))
        val above = Try(schematicLines(vertical -1)(horizontal))
        val aboveRight = Try(schematicLines(vertical -1)(horizontal))
      }
    }
  }

}
