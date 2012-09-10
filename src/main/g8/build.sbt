organization := "$organization$"

name := "$name$"

version := "$version$"

// scalaVersion := "$scala_version$"

crossScalaVersions := Seq("2.9.1", "2.9.2")

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

resolvers += "Asami Maven Repository" at "http://www.asamioffice.com/maven"

libraryDependencies <+= scalaVersion { "org.scala-lang" % "scala-compiler" % _ }

libraryDependencies += "org.scalaz" %% "scalaz-core" % "6.0.4"

libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.1-seq"

libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1-seq"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

mainClass := Some("$organization$.$main_class$")

// conscript
seq(conscriptSettings :_*)

// onejar
seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

mainClass in oneJar := Some("$package$.Main")

//
publishTo := Some(Resolver.file("asamioffice", file("target/maven-repository")))
