package com.proinnovate.sysconfws

import scala.sys.process._

object SysConfig {

  lazy val operatingSystem = "uname -s".!!.trim.toLowerCase match {
    case "darwin" => Some(OperatingSystem.MacOSX)
    case "linux" => Some(OperatingSystem.Linux)
    case _ => None
  }

  lazy val is64bit = "uname -m".!!.trim.toLowerCase match {
    case "x86" => false
    case "x86_64" => true
  }

}

