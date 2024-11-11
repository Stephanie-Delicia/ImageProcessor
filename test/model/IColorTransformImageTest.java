package model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import utils.ImageUtil;

/**
 * Represents a test class for testing the operations offered by the IColorTransformImage
 * interface.
 */
public class IColorTransformImageTest {

  private ImageUtil utils = new ImageUtil();
  private IImage manhattanImage;
  private IImage manhattanImageSepia;
  private IImage manhattanImageGrey;

  @Before
  public void setup() {
    manhattanImage = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small.png"));
    manhattanImageSepia = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small-sepia.png"));
    manhattanImageGrey = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small-greyscale.png"));
  }

  /**
   * Converts the manhattan example to sepia and compare to given sepia photo.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testSepiaTransform() throws IOException {
    IImage exampleSepia = manhattanImage.colorTransform(new SepiaColorTransform());
    assertTrue(exampleSepia.equals(manhattanImageSepia));
  }

  /**
   * Converts the manhattan example to gray-scale and compare to given gray-scale photo.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testGreyScaleTransform() throws IOException {
    IImage exampleGrey = manhattanImage.colorTransform(new GreyScaleColorTransform());
    assertTrue(exampleGrey.equals(manhattanImageGrey));
  }
}
