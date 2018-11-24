package com.sparja.syto.filter.butterworth

import com.sparja.syto.filter.core.{BandPassTransferFunction, TransferFunction}

object DigitalButterworthBandPassFilter {

  def apply(order: Int, lowCutoffFrequency: Float, highCutoffFrequency: Float, sampleFrequency: Float): TransferFunction = {
    val approximation = new ButterworthApproximation(order)
    new BandPassTransferFunction(approximation, order, lowCutoffFrequency, highCutoffFrequency, sampleFrequency)
  }

}


