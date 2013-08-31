package com.proinnovate.sysconfws

import scala.sys.process._

object Install {
	
  def installEclipse() {
    SysConfig.operatingSystem map {
      case OperatingSystem.linux =>
        installPackage("eclipse")
        // TODO: Make this test for 32 or 64 bit linux and act accordingly.  Currently it is just assuming 64 bit.

        // Fix issue with SWT links when running Eclipse with Oracle Java 7 on 64 bit linux.
        // Reference <http://stackoverflow.com/a/10251453/67159>.
        "mkdir -p ~/.swt/lib/linux/x86_64".!
        "ln -s /usr/lib/jni/libswt-* ~/.swt/lib/linux/x86_64".!
      case _ => false
    }
  }

  def installPackage(name: String): Boolean = {
    SysConfig.operatingSystem match {
      case Some(OperatingSystem.linux) =>
        println(s"==> Attempting to install $name")
        packagesAreUpdated && s"apt-get install -y $name".! == 0
      case _ => false
    }
  }

  lazy val packagesAreUpdated: Boolean = {
    SysConfig.operatingSystem match {
      case Some(OperatingSystem.linux) =>
        s"apt-get update".! == 0
      case _ => false
    }
  }

}