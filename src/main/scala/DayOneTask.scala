object DayOne_PartOne {
  def main(args: Array[String]): Unit = {
    val sum = DayOneTaskOne().calibrationValuesSum("day-one-input.txt")
    println(sum)
  }
}

object DayOne_PartTwo {
  def main(args: Array[String]): Unit = {
    val sum = DayOneTaskTwo().calibrationValuesSum("day-one-input.txt")
    println(sum)
  }
}
