package com.proinnovate.sysconfws

import java.io.File
import scala.sys.process._

object UserConfig {

  lazy val username = System.getProperty("user.name")

  lazy val homeDirectory = new File(System.getProperty("user.home"))

}

