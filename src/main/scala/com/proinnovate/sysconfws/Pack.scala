package com.proinnovate.sysconfws

/**
 * Trait capturing the components of a package definition.
 */
abstract class Pack(val name: String) {

  val operatingSystem: OperatingSystem.Value

  def install(): Boolean

}
