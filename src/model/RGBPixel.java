package model;

/**
 * Represents an RGB pixel, an implementation of IPixel. An RGBPixel has three integer values
 * representing its red, green, and blue components. The maximum value of an RGB pixel is 255
 * and the minimum value is 0.
 */
public class RGBPixel implements IPixel {

  private int red;
  private int green;
  private int blue;

  /**
   * Constructor for RGB Pixel. Takes in the values for red, green, and blue components.
   *
   * @param red   red value of pixel.
   * @param green green value of pixel.
   * @param blue  blue value of pixel.
   * @throws IllegalArgumentException if any value is not between 0 and 255.
   */
  public RGBPixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255) {
      throw new IllegalArgumentException("The red value must be between 0 and 255");
    }
    if (green < 0 || green > 255) {
      throw new IllegalArgumentException("The green value must be between 0 and 255");
    }
    if (blue < 0 || blue > 255) {
      throw new IllegalArgumentException("The blue value must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Return the maximum value of the three components red, green, blue.
   *
   * @return maximum value.
   */
  @Override
  public int valuePixel() {
    return Math.max(red, Math.max(green, blue));
  }

  /**
   * Given an increment value, adds this increment to the RGB value of the pixel.
   *
   * @param increment the amount by which the pixel changes.
   * @return IPixel new pixel that is brigthened/darkened.
   */
  @Override
  public IPixel brightenDarkenPixel(int increment) {
    int newRed = returnNewComponentValue(increment, this.red);
    int newGreen = returnNewComponentValue(increment, this.green);
    int newBlue = returnNewComponentValue(increment, this.blue);

    return new RGBPixel(newRed, newGreen, newBlue);
  }

  /**
   * If a component reaches its max/min value, return 255/0. Else, return the incremented new
   * value.
   *
   * @param increment  the amount to be added to RGB value
   * @param currentInt the current value the component is at
   * @return the new component value
   */
  private int returnNewComponentValue(int increment, int currentInt) {
    if (currentInt + increment > 255) {
      return 255;
    } else if (currentInt + increment < 0) {
      return 0;
    }

    return currentInt + increment;
  }

  /**
   * Calculates the intensity (average of the three components) of an rgb pixel.
   *
   * @return the intensity of a pixel.
   */
  public int intensityPixel() {
    return (red + green + blue) / 3;
  }

  /**
   * Calculates the luma (the weighted sum = 0.2126𝑟 + 0.7152𝑔 + 0.0722𝑏) of a pixel.
   *
   * @return the luma of a pixel.
   */
  public int lumaPixel() {
    return Math.toIntExact(Math.round(0.2126 * red + 0.7152 * green + 0.0722 * blue));
  }

  /**
   * Obtains the red component of the pixel.
   *
   * @return the red component of the pixel
   */
  @Override
  public int redChannelPixel() {
    int redComponent = this.red;
    return redComponent;
  }

  /**
   * Obtains the green component of the pixel.
   *
   * @return the green component of the pixel
   */
  @Override
  public int greenChannelPixel() {
    int greenComponent = this.green;
    return greenComponent;
  }

  /**
   * Obtains the blue component of the pixel.
   *
   * @return the blue component of the pixel
   */
  @Override
  public int blueChannelPixel() {
    int blueComponent = this.blue;
    return blueComponent;
  }
}
