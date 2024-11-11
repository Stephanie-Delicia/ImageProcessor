package view;

import model.IPixel;

/**
 * Represents a value computed from a pixel for the histogram to
 * represent frequencies of.
 */
public interface HistogramValueCommand {

  /**
   * Returns a specific value from a pixel such as value or RGB component
   * values.
   * @param pixel the pixel
   * @return the value of a component of a pixel.
   */
  public int returnPixelValue(IPixel pixel);
}
