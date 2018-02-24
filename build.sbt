organization := "com.oschrenk.spacetime"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-deprecation",
    "-Ypartial-unification",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:experimental.macros",
    "-unchecked",
    "-Ywarn-nullary-unit",
    "-Xfatal-warnings",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Xfuture")
