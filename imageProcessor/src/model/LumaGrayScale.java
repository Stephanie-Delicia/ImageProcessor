package model;

/**
 * Represents a command to convert an image to luma-grayscale. An implementation of
 * ICommandGrayScaleImage.
 */
public class LumaGrayScale extends ACommandGrayScale {

  @Override
  protected int returnNewValue(IPixel[][] pixels, int row, int col) {
    return pixels[row][col].lumaPixel();
  }
}
