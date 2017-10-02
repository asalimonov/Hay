import sbt._
import bintray.BintrayKeys._

object Dependencies {
  // Versions


  // Libraries
  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val logging =  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
  val netty = "io.netty" % "netty-all" % "4.1.16.Final"

  //Common Libraries
  val commonLibs = Seq(

  )

  // Projects

  val coreDeps = Seq(
    netty
  ) ++ commonLibs

  val appDeps = Seq(
    logback,
    logging
  ) ++ commonLibs


}