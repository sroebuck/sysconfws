package com.proinnovate.sysconfws

import scala.sys.process._
import com.proinnovate.sysconfws.tools.LinuxTools

/**
 * Trait capturing everything that is unique to Linux in installing packages.
 */
trait LinuxPack extends Pack {

  val operatingSystem = OperatingSystem.Linux

  def installPackage(name: String): Boolean = {
    LinuxTools.updatePackagesList()
    s"apt-get install -y $name".! == 0
  }

}
