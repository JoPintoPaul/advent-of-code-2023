import org.scalatest.wordspec.AnyWordSpec

class DayOneSpec extends AnyWordSpec {

  "Calculating the calibration values sum" should {
    "return the expected total for task one" in {
      val expectedTotal = 142

      val actualTotal = DayOneTaskOne().calibrationValuesSum("./day-one-task-one-input-test.txt")

      assert(actualTotal == expectedTotal)
    }

    "return the expected total for task two" in {
      val expectedTotal = 281

      val actualTotal = DayOneTaskTwo().calibrationValuesSum("./day-one-task-two-input-test.txt")

      assert(actualTotal == expectedTotal)
    }
  }

}
