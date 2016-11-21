name := "implicitlogic"

organization := "com.jasperm"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.0"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.0")

import de.heikoseeberger.sbtheader.license.Apache2_0

headers := Map(
  "scala" -> Apache2_0("2016", "Jasper Moeys")
)

