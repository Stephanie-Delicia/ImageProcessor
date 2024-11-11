package model;

/**
 * Represents a command to convert an image to red-grayscale. An implementation of
 * ICommandGrayScaleImage.
 */
public class RedGrayScale extends ACommandGrayScale {

  @Override
  protected int returnNewValue(IPixel[][] pixels, int row, int col) {
    return pixels[row][col].redChannelPixel();
  }
}
