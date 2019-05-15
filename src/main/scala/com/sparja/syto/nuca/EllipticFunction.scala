package com.sparja.syto.nuca


import breeze.math.Complex
import com.sparja.syto.common.Math._

import scala.math.{asin, cos, sin, tan}

object EllipticFunction {

  private def nextTheta(phi: Double) = asin(tan(phi / 2) * tan(phi / 2))

  private def nextAmpl(theta: Double, am: Double) = {
    val a = cos(theta) * tan(am)
    val b = math.atan(a)
    //As tan is periodic function we have to adjust phi1 to correct b + Pi*n
    def adjustPhi1(phi: Double, phi1: Double): Double = {
      if (phi1 > phi - math.Pi / 2)
        phi1
      else
        adjustPhi1(phi, phi1 + math.Pi)
    }
    am + adjustPhi1(am, b)
  }

  private def printAngle(name: String, angle: Double) = {
    val degrees = angle * 180 / math.Pi
    val d = degrees.asInstanceOf[Int] // Truncate the decimals
    val t1 = (degrees - d) * 60
    val m = t1.asInstanceOf[Int]
    val s = (t1 - m) * 60

    //println(s"Radians = $angle, DegreesDecimal=$degrees, Degrees=$d $m' $s''")
    println(s"$name has degrees=$d $m' ")
  }

  def ellipInc(k: Double, am: Double) = {
    def calculateKam(kam: List[(Double, Double)]): List[(Double, Double)] = {
      val currentKAM = kam.head
      val k = nextTheta(currentKAM._1)
      val am = nextAmpl(currentKAM._1, currentKAM._2)
      if (math.abs(k - currentKAM._1) < 0.0000001)
        kam
      else {
        //printAngle("moduli", k)
        //printAngle("amplitude", am)
        //println("----------------------------------")
        calculateKam((k, am) :: kam)
      }
    }
    val kam = calculateKam(List((k, am))).dropRight(1)
    //println(kam)
    //println(s"${kam.head._2} * ${cos(kam.head._1)} * Math.sqrt(${kam.tail.map(a => cos(a._1)).product}/${cos(k)})/${Math.pow(2, kam.size)}")
    kam.head._2 * cos(kam.head._1) * Math.sqrt(kam.tail.map(a => cos(a._1)).product / cos(k)) / Math.pow(2, kam.size)
  }



  def K(k: Double) = F(PI/2, k)

  def F(z: Double, k: Double) = ellipInc(asin(k), z)

}
