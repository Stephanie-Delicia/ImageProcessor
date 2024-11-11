package model;

/**
 * Represents an operation that transforms an IImage or image to sepia. An implementation of
 * IColorTransformationImage. The matrix has dimensions 3 by 3 and contains values to convert
 * pixel values into sepia.
 */
public class SepiaColorTransform extends AColorTransform {

  protected double[][] createConversionMatrix() {
    return new double[][]
        {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
  }
}
