name := "sbt-coverage-0.10"

organization := "com.ruk"

scalaVersion := "2.8.1"

retrieveManaged := true

version := "0.1"

sbtPlugin := true

resolvers += "undercover-repo" at "http://undercover.googlecode.com/svn/maven/repository/"

libraryDependencies +=  "undercover" % "undercover" % "0.8.3" % "compile"
