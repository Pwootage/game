name := "game"

version := "1.0"

scalaVersion := "2.11.6"

unmanagedJars in Compile := (
  baseDirectory.value / "lib" ** "*.jar" +++
    baseDirectory.value / "lib" ** (OS.getCurrent match {
      case OS.Windows => "*.dll"
      case OS.Osx => "*.dylib"
      case OS.Linux => "*.so"
    })
  ).classpath

