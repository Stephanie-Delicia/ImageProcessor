package model;


/**
 * Represents an image.
 */
public interface IImage {

  /**
   * Flips an image horizontally.
   * @return an Image flipped horizontally
   */
  public IImage flipImageHorizontal();

  /**
   * Flips an image vertically.
   * @return an Image flipped vertically
   */
  public IImage flipImageVertical();

  /**
   * Given an increment value, adds this increment to each RGB value of each
   * pixel in an image.
   * @param increment the amount by which the pixel changes.
   * @return an Image that has been brightened or darkened.
   */
  public IImage darkenBrightenImage(int increment);

  /**
   * Creates a grayscale image specified by string input.
   * @param command a command that specifies how exactly an image may be grayscaled.
   * @return a new grayscaled image
   */
  public IImage grayScaleImage(ACommandGrayScale command);

  /**
   * Obtains the width of an image.
   * @return the width of the image
   */
  public int getImageWidth();
  
  /**
   * Returns the mosaic transformation of given image.
   * @return the width of the image
   */
  public IImage createMosaic(int seeds);

  /**
   * Obtains the height of an image.
   * @return the height of the image
   */
  public int getImageHeight();

  /**
   * Obtains the 2d array of pixels that make up an image.
   * @return 2d array of pixels.
   */
  public IPixel[][] getPixels();

  /**
   * Given an operation/command to filter an image, returns the
   * new filtered image.
   * @param command the command to filter an image
   * @return a new filtered image
   */
  public IImage filterImage(ICommandTransformation command);

  /**
   * Given an operation/command to color transform an image, returns the
   * new transformed image.
   * @param command the command to color transform an image
   * @return a new transformed image
   */
  public IImage colorTransform(AColorTransform command);

  /**
   * Returns a down-scaled version of an image given smaller width and height dimensions.
   * (new dimensions must be less than or equal to the original dimensions and non-negative).
   * @param width the new (smaller) width of the image
   * @param height the new (smaller) width of the image
   * @return a down-scaled IImage
   * @throws IllegalArgumentException if dimensions are greater than original or negative.
   */
  public IImage downScale(double width, double height) throws IllegalArgumentException ;

  /**
   * Given another image of the same dimension as the original, applies a transformation
   * only to black pixels of the mask.
   * @param mask the mask image
   * @param transform the transformation to be performed on black pixels
   * @return a new image with transformations applies only to black areas of the mask
   * @throws IllegalArgumentException if mask and base image do not have same dimensions
   */
  public IImage maskImage(IImage mask, ICommandTransformation transform)
      throws IllegalArgumentException;
}
