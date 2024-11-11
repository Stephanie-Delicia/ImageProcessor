package view;

import java.io.IOException;

/**
 * This class represents the view for an image processor. An implementation of the
 * ImageProcessorView interface. Allows image processor messages to be displayed to the user.
 */
public class ImageProcessorViewImpl implements ImageProcessorView {

  private final Appendable destination;

  /**
   * Constructor for ImageProcessorViewImpl. Takes in an appendable destination.
   *
   * @param destination the appendable destination
   * @throws IllegalArgumentException if the destination is null.
   */
  public ImageProcessorViewImpl(Appendable destination)
      throws IllegalArgumentException {
    if (destination == null) {
      throw new IllegalArgumentException("The destination is null.");
    }

    this.destination = destination;
  }

  /**
   * Writes a message, the given string, to the appendable.
   *
   * @param message the message to be transmitted to destination
   * @throws IOException if transmission to destination fails.
   */
  public void writeMessage(String message) throws IOException {
    destination.append(message);
  }
}