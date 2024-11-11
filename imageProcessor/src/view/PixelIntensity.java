package view;

import model.IPixel;

/**
 * Represents a command to obtain the value of the value of a pixel.
 */
public class PixelIntensity implements HistogramValueCommand {
  @Override
  public int returnPixelValue(IPixel pixel) {
    return pixel.intensityPixel();
  }
}
