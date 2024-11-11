package model;

/**
 * Represents a command to convert an image to intensity-grayscale. An implementation of
 * ICommandGrayScaleImage.
 */
public class IntensityGreyScale extends ACommandGrayScale {

  @Override
  protected int returnNewValue(IPixel[][] pixels, int row, int col) {
    return pixels[row][col].intensityPixel();
  }
}
