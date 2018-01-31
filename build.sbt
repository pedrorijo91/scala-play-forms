name := """scala-play-forms"""
organization := "com.pedrorijo91"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.pedrorijo91.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.pedrorijo91.binders._"
