import org.scalatest.wordspec.AnyWordSpec

class DayOneSpec extends AnyWordSpec {

  "Calculating the calibration values sum" should {
    "return the expected total" in {
      val expectedTotal = 142

      val dayOne = DayOne()
      val actualTotal = dayOne.calibrationValuesSum("./day-one-input-test.txt")

      assert(actualTotal == expectedTotal)
    }
  }

}
