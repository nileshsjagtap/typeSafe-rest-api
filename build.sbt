lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """typesafe-REST-service""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.12.3",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.specs2" %% "specs2-core" % "4.8.3" % Test,
      "org.specs2" %% "specs2-mock" % "4.8.3" % Test,
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
      "com.typesafe.play" %% "play-json" % "2.6.0",
      "com.typesafe.play" %% "play-ahc-ws" % "2.6.7",
      "com.typesafe.play" %% "routes-compiler" % "2.6.7",
      "com.h2database" % "h2" % "1.4.196" % "test",
      "com.typesafe.play" %% "play-test" % "2.6.7" % "test",
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
