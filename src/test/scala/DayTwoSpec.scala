import org.scalatest.wordspec.AnyWordSpec

class DayTwoSpec extends AnyWordSpec {

  "Calculating the game values" should {
    "return the expected total for task one" in {
      val expectedTotal = 8

      val actualTotal = DayTwo().calculate("./day-two-task-one-input-test.txt")

      assert(actualTotal == expectedTotal)
    }

  }

}
