name := "guessing-game"

version := "0.1"

scalaVersion := "2.13.2"

maintainer := "bia@comeon.com"

// use the %%% operator for Scala.js
libraryDependencies += "io.monix" %% "minitest" % "2.8.2" % "test"

testFrameworks += new TestFramework("minitest.runner.Framework")

enablePlugins(JavaAppPackaging)