package model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import utils.ImageUtil;

/**
 * A test class for testing if an image is correctly filtered by operations offered by the
 * ICommandFilterImage interface. Operations include sharpening and blurring an IImage.
 */
public class ICommandFilterImageTest {

  ImageUtil utils = new ImageUtil();
  IImage manhattanImage;
  IImage manhattanImageBlurred1;
  IImage manhattanImageBlurred2;
  IImage manhattanImageSharpened1;
  IImage manhattanImageSharpened2;

  @Before
  public void setup() {
    manhattanImage = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small.png"));
    manhattanImageBlurred1 = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small-blurred.png"));
    manhattanImageBlurred2 = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small-blurred-2.png"));
    manhattanImageSharpened1 = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small-sharpen.png"));
    manhattanImageSharpened2 = utils.bufferedImageToIImage(
        utils.fileToBufferedImage("manhattan-small-sharpen-2.png"));
  }

  /**
   * Tests that if the original manhattan photo, when blurred once and twice, is equal to the given
   * blurred examples.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testImageBlurred() throws IOException {
    // First Blur
    IImage exampleBlurred = manhattanImage.filterImage(new BlurImageFilter());
    assertTrue(exampleBlurred.equals(manhattanImageBlurred1));

    // Second Blur
    IImage exampleBlurred2 = exampleBlurred.filterImage(new BlurImageFilter());
    assertTrue(exampleBlurred2.equals(manhattanImageBlurred2));
  }

  /**
   * Tests that if the original manhattan photo, when sharpened once and twice, is equal to the
   * given sharpened examples.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testImageSharpened() throws IOException {
    // First sharpen
    IImage exampleSharpened = manhattanImage.filterImage(new SharpenImageFilter());
    assertTrue(exampleSharpened.equals(manhattanImageSharpened1));

    // Second sharpen
    IImage exampleSharpened2 = exampleSharpened.filterImage(new SharpenImageFilter());
    assertTrue(exampleSharpened2.equals(manhattanImageSharpened2));
  }
}