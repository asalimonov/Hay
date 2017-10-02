import sbt.Keys.{libraryDependencies, organization, _}
import sbt._
import Dependencies._

//Root project
name := "Hay"
description := "The Hay library provides an easy way to create health checks and metrics (counters, timers, histograms, etc.) in Scala."

// Global settings
lazy val commonSettings = Seq(
  version := "0.0.1",
  scalaVersion := "2.12.3",
  organization := "com.github.asalimonov",
  test in assembly := {}
)

lazy val core = project.in(file("hay-core"))
  .settings(commonSettings: _*)
  .settings(
    name := "hay-core",
    description := "Core Hay library to create health checks and metrics",
    libraryDependencies ++=  coreDeps,
    assemblyJarName in assembly := s"${name.value}-${version.value}.jar"
  )

lazy val app = project.in(file("examples/app")).dependsOn(core % "compile->compile")
  .settings(commonSettings: _*)
  .settings(
    name := "app",
    description := "Test application for Hay library",
    libraryDependencies ++=  appDeps,
    mainClass in assembly := Some("examples.Main"),
    assemblyJarName in assembly := s"${name.value}.jar"
  )


resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += Resolver.bintrayRepo("jcenter", "releases")
//resolvers += Resolver.bintrayRepo("jvican", "releases")
