package com.proinnovate.sysconfws

import scala.sys.process._

object JavaConfig {

  lazy val countryConfig = System.getProperty("user.country")

  lazy val runtimeVersion = System.getProperty("java.runtime.version")

  lazy val javaVendor = System.getProperty("java.vm.vendor")

}

