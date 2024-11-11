package model;

/**
 * Represents an abstract class for an ICommandGrayScaleImage. A concrete class
 * that extends this performs a specific kind of grayscale operation on an image.
 */
public abstract class ACommandGrayScale implements ICommandTransformation {

  @Override
  public IImage performTransformation(IImage image) {
    IPixel[][] pixels = new RGBPixel[image.getImageHeight()][image.getImageWidth()];
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        int newValue = returnNewValue(image.getPixels(), i, j);
        pixels[i][j] = new RGBPixel(newValue, newValue, newValue);
      }
    }

    IImage newImage = new ImageImpl(pixels);
    return newImage;
  }

  protected abstract int returnNewValue(IPixel[][] pixels, int row, int col);
}
