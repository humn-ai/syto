package com.sparja.syto.filter.elliptic.analog

import com.sparja.syto.filter.bessel.BesselMockedPrototypeRoots
import com.sparja.syto.filter.core.{Roots, TransferFunctionBuilder}
import com.sparja.syto.filter.elliptic.EllipticMockedPrototypeRoots
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BandPassFilterTest {


  def calculateCoefficients(f: () => Roots, lowFreq: Double, highFreq: Double) = {
    new TransferFunctionBuilder()
      .prototype(f)
      .transformToBandPass(lowFreq, highFreq)
      .coefficients
  }

  @Test
  def shouldCalculateTwoOrderFilter() = {
    val (b, a) = calculateCoefficients(() => EllipticMockedPrototypeRoots.twoOrder, 10.0, 20.0)
    assertEquals(a(0), 1.0, 0.001)
    assertEquals(a(1), 4.53393674199, 0.001)
    assertEquals(a(2), 461.353530237, 0.001)
    assertEquals(a(3), 906.787348398, 0.001)
    assertEquals(a(4), 40000.0, 0.001)
    assertEquals(b(0), 0.0100001249734, 0.001)
    assertEquals(b(1), 0.0, 0.001)
    assertEquals(b(2), 38.5016754881, 0.001)
    assertEquals(b(3), 0.0, 0.001)
    assertEquals(b(4), 400.004998935, 0.001)
  }

  @Test
  def shouldCalculateThreeOrderFilter() = {
    val (b, a) = calculateCoefficients(() => EllipticMockedPrototypeRoots.threeOrder, 10.0, 20.0)
    assertEquals(a(0), 1.0, 0.001)
    assertEquals(a(1), 4.22569399016, 0.001)
    assertEquals(a(2), 687.251873453, 0.001)
    assertEquals(a(3), 1885.42651228, 0.001)
    assertEquals(a(4), 137450.374691, 0.001)
    assertEquals(a(5), 169027.759606, 0.001)
    assertEquals(a(6), 8000000.0, 0.001)
    assertEquals(b(0), 0.485393536936, 0.001)
    assertEquals(b(1), 0.0, 0.001)
    assertEquals(b(2), 389.30633099, 0.001)
    assertEquals(b(3), 0.0, 0.001)
    assertEquals(b(4), 19415.7414774, 0.001)
    assertEquals(b(5), 0.0, 0.001)
  }



}
