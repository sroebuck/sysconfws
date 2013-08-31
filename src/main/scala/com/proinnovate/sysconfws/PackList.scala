package com.proinnovate.sysconfws

import language.reflectiveCalls
import java.io.{InputStreamReader, BufferedReader}
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.{universe => ru}

object PackList {

  lazy val packageNames = packages.keys

  lazy val packages: Map[String,Pack] = packClassNames.map(packObjectByClassName).map(x => (x.name, x)).toMap

  // PRIVATE

  private lazy val packClassNames: Seq[String] = {
    val is = java.lang.Thread.currentThread.getContextClassLoader.getResourceAsStream("com/proinnovate/sysconfws/pack")

    val reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))
    val classes = ListBuffer[String]()
    var line = reader.readLine()
    while (line != null) {
      classes += line
      line = reader.readLine()
    }
    is.close()
    classes.map(x => x.substring(0, x.length - 6)).filter(_.endsWith("$")).map(x => x.substring(0,x.length - 1))
  }

  private def packObjectByClassName(name: String): Pack = {
    val rm = ru.runtimeMirror(getClass.getClassLoader)
    val module = rm.staticModule(s"com.proinnovate.sysconfws.pack.$name")
    val obj = rm.reflectModule(module)
    obj.instance.asInstanceOf[Pack]
  }



}
