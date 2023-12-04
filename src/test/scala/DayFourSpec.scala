import org.scalatest.wordspec.AnyWordSpec

class DayFourSpec extends AnyWordSpec {

  "Calculating the matched points sum" should {
    "return the expected total for task one" in {
      val expectedTotal = 13

      val challenge = DayFour("./day-four-input-test.txt")
      val actualTotal = challenge.calculatePoints()

      assert(actualTotal == expectedTotal)
    }

    "return the expected total for task two" in {
      val expectedTotal = 30

      val challenge = DayFour("./day-four-input-test.txt")
      val actualTotal = challenge.calculateCopies()

      assert(actualTotal == expectedTotal)
    }
  }
}
