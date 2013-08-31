package com.proinnovate.sysconfws


class PackTest extends org.scalatest.FunSuite {

  test("Count the number of built in packs") {

    assert(PackList.packageNames.size == 1)

  }

}
