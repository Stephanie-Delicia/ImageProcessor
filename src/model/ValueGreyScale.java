package model;

/**
 * Represents a command to convert an image to value-grayscale. An implementation of
 * ICommandGrayScaleImage.
 */
public class ValueGreyScale extends ACommandGrayScale {

  @Override
  protected int returnNewValue(IPixel[][] pixels, int row, int col) {
    return pixels[row][col].valuePixel();
  }
}
