package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the set of operations that can be done within an image processing controller
 * represented by an ImageProcessorController. Provides the functionality of a controller
 * for specific commands.
 */
public interface ICommandController {

  /**
   * Takes in a specific command and performs the appropriate operations for an
   * image processing controller.
   * @param scan the scanner of the image processor controller
   */
  public void imageCommand(Scanner scan) throws IOException;
}
