import sbt.Keys.libraryDependencies

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2023",
    scalaVersion := "2.13.12",
    version := "0.1",
    libraryDependencies ++= Dependencies.compile ++ Dependencies.test,
)
