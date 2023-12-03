import org.scalatest.wordspec.AnyWordSpec

class DayTwoSpec extends AnyWordSpec {

  "Calculating the game values" should {
    "return the expected total for task one" in {
      val expectedTotal = 8

      val actualTotal = DayTwo().calculateSumOfPossibleGames("./day-two-task-one-input-test.txt")

      assert(actualTotal == expectedTotal)
    }

    "return the expected total for task two" in {
      val expectedTotal = 2286

      val actualTotal = DayTwo().calculatePowerOfLowestCubes("./day-two-task-one-input-test.txt")

      assert(actualTotal == expectedTotal)
    }
  }

}
