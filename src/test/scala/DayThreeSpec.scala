import org.scalatest.wordspec.AnyWordSpec

class DayThreeSpec extends AnyWordSpec {

  "Calculating the symbol coordinates" should {
    "return the expected cordinations for task one" in {
      val expectedTotal = 8

      DayThree().calculate("./day-three-input-test.txt")

    }

  }

}
