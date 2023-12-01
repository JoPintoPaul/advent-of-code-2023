import org.scalatest.wordspec.AnyWordSpec

class DayOneSpec extends AnyWordSpec {

  "Calculating the calibration values sum" should {
    "return the expected total for task one" in {
      val expectedTotal = 142

      val actualTotal = DayOne().calculate("./day-one-task-one-input-test.txt")

      assert(actualTotal == expectedTotal)
    }

    "return the expected total for task two" in {
      val expectedTotal = 281

      val actualTotal = DayOne(taskTwo = true).calculate("./day-one-task-two-input-test.txt")

      assert(actualTotal == expectedTotal)
    }
  }

}
