package model;

/**
 * Represents a pixel of an image. Provides functionality for manipulating a pixel's values.
 * This includes returning the value, luma, or intensity of a pixel. An IPixel's parameters
 * are specific to implementation.
 */
public interface IPixel {
  /**
   * Calculates the value of a pixel which is the maximum value of a pixel's value componenets.
   * @return the value of a pixel
   */
  public int valuePixel();

  /**
   * Given an increment value, adds this increment to each component value of the pixel.
   * @param increment the amount by which the pixel changes.
   * @return a new IPixel that is brightened/darkened.
   */
  public IPixel brightenDarkenPixel(int increment);

  /**
   * Calculates the intensity (average of all value components) of a pixel.
   * @return the intensity of a pixel.
   */
  public int intensityPixel();

  /**
   * Calculates the luma (the weighted sum) of a pixel.
   * @return the luma of a pixel.
   */
  public int lumaPixel();

  /**
   * Returns the pixel represented only by the red channel.
   * @return the red channel of a pixel
   */
  public int redChannelPixel();

  /**
   * Returns the pixel represented only by the green channel.
   * @return the green channel of a pixel
   */
  public int greenChannelPixel();

  /**
   * Returns the pixel represented only by the blue channel.
   * @return the blue channel of a pixel
   */
  public int blueChannelPixel();
}
