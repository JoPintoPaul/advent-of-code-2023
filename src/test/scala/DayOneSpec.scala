import org.scalatest.wordspec.AnyWordSpec

class DayOneSpec extends AnyWordSpec {

  "Calculating the calibration values sum" should {
    "return the expected total" in {
      val expectedTotal = 7

      val dayOne = DayOne()
      val actualTotal = dayOne.calibrationValuesSum("some-file-name")

      assert(actualTotal == expectedTotal)
    }
  }

}
