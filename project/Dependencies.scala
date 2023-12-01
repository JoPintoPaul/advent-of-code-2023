import sbt._

object Dependencies {
  val compile = Seq(
    "org.scalactic" %% "scalactic" % "3.2.17"

  )

  val test = Seq(
    "org.scalatest" %% "scalatest" % "3.2.17"         % Test
  )
}
