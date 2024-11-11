package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for a image processing model, ImageProcessingModelImpl which implements
 * ImageProcessingModel.
 */
public class ImageProcessingModelImplTest {

  ImageProcessorModel processor;
  IImage exampleImage;

  @Before
  public void init() {
    processor = new ImageProcessorModelImpl();
    IPixel pixel = new RGBPixel(50, 60, 70);
    exampleImage = new ImageImpl(100, 100, pixel);
  }

  /**
   * Tests if an error is thrown by constructor if the given map of images is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidMap() {
    ImageProcessorModel nullProcessor = new ImageProcessorModelImpl(null);
  }

  /**
   * Tests if an image can be correctly return from a map when given name of image.
   */
  @Test
  public void testObtainImage() {
    ImageProcessorModel example = new ImageProcessorModelImpl();
    example.addImage("Random", exampleImage);
    // Obtain image using method
    IImage obtainedImage = example.obtainImage("Random");
    assertTrue(new ImageImpl(100, 100, new RGBPixel(
        50, 60, 70)).equals(obtainedImage));
  }

  /**
   * Tests if an image can be correctly added to map of stored images given the image and its name.
   */
  @Test
  public void testAddImage() {
    ImageProcessorModel example = new ImageProcessorModelImpl();
    example.addImage("Random", exampleImage);
    // Access map information
    // Check if image with correct key is stored
    //Image obtainedImage = example.obtainImage("Random");
    assertEquals(1, example.countImages());

    // add another image
    example.addImage("Random2", exampleImage);
    assertEquals(2, example.countImages());
  }

  /**
   * Tests the number of images that are in the image processing model.
   */
  @Test
  public void testCountImages() {
    ImageProcessorModel example = new ImageProcessorModelImpl();

    assertEquals(0,example.countImages());

    example.addImage("Random", exampleImage);

    // Access map information
    // Check if image with correct key is stored
    //Image obtainedImage = example.obtainImage("Random");
    assertEquals(1,example.countImages());

    // add another image
    example.addImage("Random2", exampleImage);
    assertEquals(2,example.countImages());

    // add another image
    example.addImage("Random3", exampleImage);
    assertEquals(3,example.countImages());

    // add another image
    example.addImage("Random4", exampleImage);
    assertEquals(4,example.countImages());

  }
}
