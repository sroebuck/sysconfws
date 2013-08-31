package com.proinnovate.sysconfws.pack

import com.proinnovate.sysconfws.{LinuxPack, SysConfig, Pack}
import scala.sys.process._

object Eclipse extends Pack("eclipse") with LinuxPack {

  def install() = {
    var success = installPackage("eclipse")

    // Fix issue with SWT links when running Eclipse with Oracle Java 7 on linux.
    // Reference <http://stackoverflow.com/a/10251453/67159>.
    success && (if (SysConfig.is64bit) {
      "mkdir -p ~/.swt/lib/linux/x86_64".! == 0 &&
          "ln -s /usr/lib/jni/libswt-* ~/.swt/lib/linux/x86_64/".! == 0
    } else {
      "mkdir -p ~/.swt/lib/linux/x86".! == 0 &&
          "ln -s /usr/lib/jni/libswt-* ~/.swt/lib/linux/x86/".! == 0
    })
  }


}
