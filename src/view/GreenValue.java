package view;

import model.IPixel;

/**
 * Represents a command to obtain the value of the green component of a pixel.
 */
public class GreenValue implements HistogramValueCommand {
  @Override
  public int returnPixelValue(IPixel pixel) {
    return pixel.greenChannelPixel();
  }
}
