name := "spark-streaming-bigdata"

version := "1.0"

scalaVersion := "2.11.8"  // 2.12.2

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq("org.apache.spark" % "spark-streaming_2.11" % "1.6.1",
  "org.scalaj" %% "scalaj-http" % "2.2.1",
  "org.jfarcand" % "wcs" % "1.5",
  "net.liftweb" %% "lift-json" % "2.6+")