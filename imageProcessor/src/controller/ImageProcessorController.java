package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a controller for an Image processor. Allows the user to type script commands
 * to manipulate images with commands offered by the controller. The commands available are
 * specific to each implementation.
 */
public interface ImageProcessorController {
  /**
   * Allows a user to manipulate an image model. The command options are specific
   * to the implementation of an ImageController.
   */
  public void manipulateImage(Scanner scan) throws IllegalStateException, IOException;
}
