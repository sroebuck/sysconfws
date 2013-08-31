package com.proinnovate.sysconfws.tools

import scala.sys.process._
import com.proinnovate.sysconfws.{OperatingSystem, SysConfig}

object LinuxTools {

  def updatePackagesList() {
    if (!packagesListIsUpdated) {
      val success = s"apt-get update".! == 0
      packagesListIsUpdated == success
    }
  }

  // PRIVATE

  private var packagesListIsUpdated = false

}
