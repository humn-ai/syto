package com.sparja.syto.filter


import junit.framework.TestCase.assertEquals
import org.junit.Test

class FilterImplementationTest {

  @Test
  def shouldFilterOneSignalThreeOrder() = {
    val a = List(1, -1.16191748, 0.69594276, -0.1377613)
    val b = List(0.049533, 0.14859899, 0.14859899, 0.049533)
    val x = List(1.0)
    val y = filter(b, a, x)

    assertEquals(y(0), 0.049533, 0.00001)
    assertEquals(y.size, 1)
  }



  @Test
  def shouldFilterTenSignalsThreeOrder() = {
    val a = List(1, -1.16191748, 0.69594276, -0.1377613)
    val b = List(0.049533, 0.14859899, 0.14859899, 0.049533)
    val x = (1 to 10).map(_.toDouble).toList
    val y = filter(b, a, x)

    assertEquals(y(0), 0.049533, 0.00001)
    assertEquals(y(1), 0.30521824, 0.00001)
    assertEquals(y(2), 0.91456223, 0.00001)
    assertEquals(y(9), 8.03207878, 0.00001)
    assertEquals(y.size, 10)
  }

  @Test
  def shouldFilterOneSignalFourOrder() = {
    val a = List(1, -1.57039885,  1.27561332, -0.48440337,  0.07619706)
    val b = List(0.01856301, 0.07425204, 0.11137806, 0.07425204, 0.01856301)
    val x = List(1.0)
    val y = filter(b, a, x)

    assertEquals(y(0), 0.01856301, 0.00001)
    assertEquals(y.size, 1)
  }

  @Test
  def shouldFilterTenSignalsFourOrder() = {
    val a = List(1, -1.57039885,  1.27561332, -0.48440337,  0.07619706)
    val b = List(0.01856301, 0.07425204, 0.11137806, 0.07425204, 0.01856301)
    val x = (1 to 30).map(_.toDouble).toList
    val y = filter(b, a, x)

    assertEquals(y(0), 0.01856301, 0.00001)
    assertEquals(y(10), 8.4127196, 0.00001)
    assertEquals(y(20), 1.84363287e+01, 0.00001)
    assertEquals(y(29), 2.74357709e+01, 0.00001)
    assertEquals(y.size, 30)
  }








}
