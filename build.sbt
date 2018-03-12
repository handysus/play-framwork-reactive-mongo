name := """reactive-mongo-learn"""
organization := "com.handy.learn"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

/* ReactiveMongo */
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.12.7-play26"
libraryDependencies += "org.reactivemongo" %% "reactivemongo-akkastream" % "0.12.7"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.handy.learn.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.handy.learn.binders._"

import play.sbt.routes.RoutesKeys

RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"