package view;

import java.io.IOException;

/**
 * Represents the view for an image processor. Provides functionality for displaying messages to
 * the user.
 */
public interface ImageProcessorView {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  public void writeMessage(String message) throws IOException;
}
