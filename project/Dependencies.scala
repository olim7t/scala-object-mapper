import sbt._

object Dependencies {
  val javaDriverVersion = "4.9.0-SNAPSHOT"

  lazy val javaDriverMapperRuntime = "com.datastax.oss" % "java-driver-mapper-runtime" % javaDriverVersion
  lazy val javaDriverMapperProcessor = "com.datastax.oss" % "java-driver-mapper-processor" % javaDriverVersion
  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
}
