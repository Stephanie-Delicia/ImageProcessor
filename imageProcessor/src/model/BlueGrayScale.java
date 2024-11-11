package model;

/**
 * Represents a command to convert an image to blue-grayscale. An implementation of
 * ICommandGrayScaleImage.
 */
public class BlueGrayScale extends ACommandGrayScale {

  @Override
  protected int returnNewValue(IPixel[][] pixels, int row, int col) {
    return pixels[row][col].blueChannelPixel();
  }
}
