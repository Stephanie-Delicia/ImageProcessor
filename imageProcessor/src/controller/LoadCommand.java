package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.IImage;
import model.ImageProcessorModel;
import utils.ImageUtil;
import view.ImageProcessorView;

/**
 * Represents a load command or operation for an image processing controller.
 * Loads an image of either format .jpeg, .png, .ppm, or .bbm and stores
 * the image in the image processing model.
 */
public class LoadCommand extends ACommandController {

  /**
   * Constructor for a LoadCommand.
   * @param controller the image processing controller
   * @param model the image processing model
   * @param view the image processing view
   */
  public LoadCommand(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  public void imageCommand(Scanner scan) throws IOException {
    String pathName;
    String imageName;
    IImage loadedImage;

    pathName = scan.next();
    imageName = scan.next();

    try {
      loadedImage = new ImageUtil().convertStringFileToPhoto(pathName);
      model.addImage(imageName, loadedImage);
      view.writeMessage("\nImage loaded.");
    } catch (FileNotFoundException e) {
      view.writeMessage("\nFile not found. Please enter a valid file path.");
    }
  }
}
