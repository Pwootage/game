object OS {
  val Windows = "win"
  val Osx = "osx"
  val Linux = "linux"
  val Unknown = "unk"

  def getCurrent = {
    val os = sys.props("os.name").toLowerCase

    if (os.contains("win")) {
      Windows
    } else if (os.contains("osx")) {
      Osx
    } else if (os.contains("nix") || os.contains("nux")) {
      Linux
    } else {
      Unknown
    }
  }
}