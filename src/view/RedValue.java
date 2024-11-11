package view;

import model.IPixel;

/**
 * Represents a command to obtain the value of the red component of a pixel.
 */
public class RedValue implements HistogramValueCommand {

  @Override
  public int returnPixelValue(IPixel pixel) {
    return pixel.redChannelPixel();
  }
}
