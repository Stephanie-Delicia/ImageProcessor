package model;

import java.util.Objects;

/**
 * Represents an abstract class for an IColorTransformationImage. An concrete class that
 * extends this MUST have a matrix converter of dimensions 3 by 3. An implementation
 * of IColorTransformationImage.
 */
public abstract class AColorTransform implements ICommandTransformation {
  private double[][] matrixConverter;

  /**
   * Constructor for an AColorTransform.
   */
  public AColorTransform() {
    this.matrixConverter = Objects.requireNonNull(createConversionMatrix());
    int width = 0;
    int height = matrixConverter.length;

    // Compute width
    for (Double d : matrixConverter[0]) {
      width++;
    }

    if (height != 3 || width != 3) {
      throw new IllegalArgumentException("This matrix must have dimensions 3 by 3.");
    }

    // INVARIANT : Ensure that the matrix converter has dimensions 3 by 3.
  }

  @Override
  public IImage performTransformation(IImage image) {
    int width = image.getImageWidth();
    int height = image.getImageHeight();

    IPixel[][] newPixels = new RGBPixel[height][width];
    IPixel[][] origPixels = image.getPixels();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Get original pixel
        IPixel origPixel = origPixels[i][j];
        // Compute new RGB values
        int origRed = origPixel.redChannelPixel();
        int origGreen = origPixel.greenChannelPixel();
        int origBlue = origPixel.blueChannelPixel();

        int newRed = reachBound(Math.toIntExact(
            Math.round((matrixConverter[0][0] * origRed)
                + (matrixConverter[0][1] * origGreen)
                + (matrixConverter[0][2] * origBlue))));

        int newGreen = reachBound(Math.toIntExact(
            Math.round((matrixConverter[1][0] * origRed)
                + (matrixConverter[1][1] * origGreen)
                + (matrixConverter[1][2] * origBlue))));

        int newBlue = reachBound(Math.toIntExact(
            Math.round((matrixConverter[2][0] * origRed)
                + (matrixConverter[2][1] * origGreen)
                + (matrixConverter[2][2] * origBlue))));

        // Set newPixel[i][j] to new RGB pixel
        newPixels[i][j] = new RGBPixel(newRed, newGreen, newBlue);
      }
    }

    return new ImageImpl(newPixels);
  }

  /**
   * Returns the conversion matrix specific to the color transformation implementation.
   * @return the conversion matrix for which to convert an image by.
   */
  protected abstract double[][] createConversionMatrix();

  /**
   * Should a pixel value reach its max after a conversion, it is automatically
   * set at the max bound or min bound (255 or 0), not over it.
   * @param pixelValue the value of a pixel.
   * @return the new integer value of the pixel.
   */
  protected int reachBound(int pixelValue) {
    if (pixelValue > 255) {
      return 255;
    }

    else if (pixelValue < 0) {
      return 0;
    }

    return pixelValue;
  }
}
