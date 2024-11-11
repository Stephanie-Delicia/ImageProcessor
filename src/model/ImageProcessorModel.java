package model;

/**
 * Represents the image processing model and the functionalities it offers.
 * An ImageProcessingModel also contains the data of images to be manipulated.
 * Offers methods for adding an image or obtaining an image from the image processor.
 */
public interface ImageProcessorModel {

  /**
   * Adds an image to the map of stored images of a ImageProcessingModel model.
   *  @param image the image to be added to map.
   *  @param imageName the name of the image to be added to map.
   */
  public void addImage(String imageName, IImage image);

  /**
   * Returns the image if present in map with given string name.
   * @param imageName the name of the image wanted to be obtained.
   * @return an Image with the given image name
   */
  public IImage obtainImage(String imageName);

  /**
   * Count the number of images stored in the map.
   * @return number of images.
   */
  public int countImages();
}
