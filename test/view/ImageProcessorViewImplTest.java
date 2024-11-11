package view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * A testing class for the ImageProcessorViewImpl.
 */
public class ImageProcessorViewImplTest {

  Appendable destination;
  ImageProcessorView view1;

  @Before
  public void setup() {
    destination = new StringBuilder();
    view1 = new ImageProcessorViewImpl(destination);
  }

  /**
   * Tests if the constructor for an ImageProcessorViewImpl throws an exception if the appendable
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInValidDestination() {
    ImageProcessorView nullView = new ImageProcessorViewImpl(null);
  }

  /**
   * Tests if a message can be transmitted correctly to the appendable.
   */
  @Test
  public void testWriteMessageToDestination() throws IOException {
    assertEquals("", destination.toString());
    view1.writeMessage("Hello");
    assertEquals("Hello", destination.toString());
    view1.writeMessage("\n");
    assertEquals("Hello\n", destination.toString());
  }
}
