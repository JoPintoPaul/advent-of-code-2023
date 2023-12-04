import org.scalatest.wordspec.AnyWordSpec

class DayFourSpec extends AnyWordSpec {

  "Calculating the matched points sum" should {
    "return the expected total for task one" in {
      val expectedTotal = 13

      val actualTotal = DayFour().calculate("./day-four-input-test.txt")

      assert(actualTotal == expectedTotal)
    }
  }
}
