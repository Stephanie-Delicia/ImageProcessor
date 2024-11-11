package model;

/**
 * Represents an operation to convert an image to Greyscale.
 * The matrix converter has dimensions 3 by 3 and contains values to convert pixel values
 * into greyscale.
 */
public class GreyScaleColorTransform extends AColorTransform {

  protected double[][] createConversionMatrix() {
    return new double[][]
        {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};
  }
}
