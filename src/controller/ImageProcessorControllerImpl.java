package controller;

import java.io.IOException;
import java.util.Scanner;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents a controller for an ImageProcessorModel. An implementation of ImageController
 * interface. The controller is able to manipulate an image by loading, saving, flipping
 * horizontally or vertically, brightening or darkening it, returning its red-, blue-,
 * green-components, or converting it to luma-, intensity-, or value-grayscale. Finally, the
 * controller allows the user to transform an image with commands such as blur, sharpen,
 * sepia-transform, and grayscale-transform.
 *
 * <p>Communicates with view by transmitting messages about the user's transformations.
 * Also transmits error messages to the view about invalid user inputs.
 *
 * <p>Obtains user-requested photos from the model's stored images and saves images to the model.
 * Allows image formats such as .ppm, .png, .jpeg, and .bmp to be loaded and also saved.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {

  private final ImageProcessorModel model;
  private final Readable read;
  private final ImageProcessorView view;

  /**
   * Constructor for an ImageControllerImpl. Given a model, readable, and a view, returns an
   * ImageControllerImpl.
   *
   * @param model the image model
   * @param read  the readable
   * @param view  the view for the processor
   * @throws IllegalArgumentException if either model, readable, or appendable are null.
   */
  public ImageProcessorControllerImpl(ImageProcessorModel model, Readable read,
      ImageProcessorView view)
      throws IllegalArgumentException {
    if (model == null || read == null || view == null) {
      throw new IllegalArgumentException("Inputs are null.");
    }

    this.model = model;
    this.read = read;
    this.view = view;
  }

  @Override
  public void manipulateImage(Scanner scan) throws IllegalStateException, IOException {
    boolean quit = false;
    Readable read = this.read;

    // print both menu and welcome message.
    welcomeMessage();
    printMenu();
    System.out.println("Welcome: ");
    System.out.println("\nType instruction: ");
    writeMessageController("Type instruction: ");

    while (!quit) {
      String userInput = scan.next();

      switch (userInput) {
        case ("load"):
          new LoadCommand(this, model, view).imageCommand(scan);
          break;
        case ("save"):
          new SaveCommand(this, model, view).imageCommand(scan);
          break;
        case ("intensity-component"):
          new IntensityCommand(this, model, view).imageCommand(scan);
          break;
        case ("luma-component"):
          new LumaTransform(this, model, view).imageCommand(scan);
          break;
        case ("value-component"):
          new ValueTransform(this, model, view).imageCommand(scan);
          break;
        case ("brighten"):
          new BrightenTransform(this, model, view).imageCommand(scan);
          break;
        case ("vertical-flip"):
          new VerticalTransform(this, model, view).imageCommand(scan);
          break;
        case ("horizontal-flip"):
          new HorizontalTransform(this, model, view).imageCommand(scan);
          break;
        case ("red-component"):
          new RedTransform(this, model, view).imageCommand(scan);
          break;
        case ("green-component"):
          new GreenTransform(this, model, view).imageCommand(scan);
          break;
        case ("blue-component"):
          new BlueTransform(this, model, view).imageCommand(scan);
          break;
        case ("blur"):
          new BlurTransform(this, model, view).imageCommand(scan);
          break;
        case ("sharpen"):
          new SharpenTransform(this, model, view).imageCommand(scan);
          break;
        case ("sepia-transform"):
          new SepiaTransform(this, model, view).imageCommand(scan);
          break;
        case ("grayscale-transform"):
          new GrayScaleTransform(this, model, view).imageCommand(scan);
          break;
        case ("downscale"):
          new DownScaleImage(this, model, view).imageCommand(scan);
          break;
        case ("mask"):
          new MaskTransform(this, model, view).imageCommand(scan);
          break;
          // Add down-sizing command, take in first command, photo, width, height
        case "q":
        case "quit":
          quit = true;
          break;
        default:
          writeMessageController("Please input a valid command.\n");
          System.out.println("\nPlease input a valid command.");
      }
    }
  }

  /**
   * Prints the menu or list of commands offered by controller.
   *
   * @throws IllegalStateException if an IOException occurs.
   */
  private void printMenu() throws IllegalStateException {
    try {
      view.writeMessage("Supported user instructions are: " + System.lineSeparator());
      view.writeMessage(
          "load image-path image-name" + System.lineSeparator());
      view.writeMessage(
          "save image-path image-name" + System.lineSeparator());
      view.writeMessage("value-component image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("luma-component image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("intensity-component image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("green-component image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("blue-component image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("red-component image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("horizontal-flip image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("vertical-flip image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("brighten increment image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("sepia-transform image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("grayscale-transform image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("blur image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("sharpen image-name dest-image-name" + System.lineSeparator());
      view.writeMessage("q or quit (quit the program) " + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission failed.");
    }
  }

  /**
   * Creates the welcome message for the controller.
   *
   * @throws IllegalStateException if an IOException occurs.
   */
  private void welcomeMessage() throws IllegalStateException {
    try {
      view.writeMessage("Welcome!" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission failed.");
    }
  }

  /**
   * Transmits a message to the view and throws an IllegalStateException if an IOException occurs.
   *
   * @param message the string message to be transmitted to view.
   * @throws IllegalStateException if an IOException occurs.
   */
  private void writeMessageController(String message) throws IllegalStateException {
    try {
      view.writeMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Transmission failed.");
    }
  }
}