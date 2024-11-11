package view;

import model.IPixel;

/**
 * Represents a command to obtain the value of the blue component of a pixel.
 */
public class BlueValue implements HistogramValueCommand {
  @Override
  public int returnPixelValue(IPixel pixel) {
    return pixel.blueChannelPixel();
  }
}
