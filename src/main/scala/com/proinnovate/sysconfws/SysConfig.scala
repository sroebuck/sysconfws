package com.proinnovate.sysconfws

import scala.sys.process._

object SysConfig {

  lazy val operatingSystem = "uname -s".!!.trim.toLowerCase match {
    case "darwin" => Some(OperatingSystem.macosx)
    case "linux" => Some(OperatingSystem.linux)
    case _ => None
  }

  lazy val is64bit = "uname -m".!!.trim.toLowerCase match {
    case "x86" => Some(false)
    case "x86_64" => Some(true)
    case _ => None
  }

}

