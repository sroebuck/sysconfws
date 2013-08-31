name := "sysconfws"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)
