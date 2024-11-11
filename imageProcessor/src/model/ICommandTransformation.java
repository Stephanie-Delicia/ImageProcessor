package model;

/**
 * Acts as a shell script to run several commands to create a gray scale image.
 */
public interface ICommandTransformation {

  /**
   * Creates a grayscale version of the given image.
   * @param image an instance of an image that the macro will transform.
   * @return an image that has been converted to gray scale.
   */
  public IImage performTransformation(IImage image);
}