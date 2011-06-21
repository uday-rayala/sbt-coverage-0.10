import sbt._

import Keys._

object CoveragePlugin extends Plugin {
  val coverageTask  = TaskKey[Unit]("coverage")
  val instrumentDirectory = SettingKey[File]("instrument-directory")


  override val settings = Seq(
    instrumentDirectory <<= (target) { (targetDir:File) => targetDir / "classes-instrumented"  },

    coverageTask <<= (classDirectory in Compile, instrumentDirectory) map { (classDir:File, instrDir:File) =>
      println("Class directory: " + classDir)
      println("test directory: " + classDir)
      println("Instr directory: " + instrDir)

      val instrument = new undercover.instrument.OfflineInstrument
      val paths = new java.util.ArrayList[java.io.File]
      paths.add(classDir.asFile)
      instrument.setInstrumentPaths(paths)
      instrument.setOutputDirectory(instrDir.asFile)

      val globFilter = new undercover.instrument.filter.GlobFilter(Array(), Array())
      instrument.setFilter(globFilter)

      val metaFile = (instrDir / "undercover.md").asFile
      instrument.setMetaDataFile(metaFile)
      instrument.fullcopy()
    }
  )

}
