package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an image processing model. Provides the functionality of saving images in a
 * map or obtaining an IImage from the map. Each IImage in the map has an associated String name.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {

  private final Map<String, IImage> storedImages;

  /**
   * Constructor for an ImageProcessingModelImpl. Does not take inputs and initiates the map for
   * storing images.
   */
  public ImageProcessorModelImpl() {
    this.storedImages = new HashMap<String, IImage>();
  }

  /**
   * Constructor for an ImageProcessingModelImpl. Takes in a map of Images.
   *
   * @param storedImages the map of IImages associated with a String name
   * @throws IllegalArgumentException if the map of Images is null
   */
  public ImageProcessorModelImpl(Map<String, IImage> storedImages)
      throws IllegalArgumentException {
    if (storedImages == null) {
      throw new IllegalArgumentException("Map of Images cannot be null.");
    }
    this.storedImages = storedImages;
  }

  /**
   * Adds an IImage with a String name to the map of stored images.
   *
   * @param image     the image to be added to map.
   * @param nameImage the name of the image to be added to map.
   */
  @Override
  public void addImage(String nameImage, IImage image) {
    storedImages.put(nameImage, image);
  }

  /**
   * Returns the IImage if present in map with given string name.
   *
   * @param nameImage the name of the image wanted to be obtained.
   * @return an Image with the given image name
   */
  @Override
  public IImage obtainImage(String nameImage) {
    return storedImages.get(nameImage);
  }

  /**
   * Count the number of IImage's stored in the map.
   * @return number of images
   */
  @Override
  public int countImages() {
    return storedImages.size();
  }
}
