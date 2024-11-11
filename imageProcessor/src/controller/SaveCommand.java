package controller;

import java.io.IOException;
import java.util.Scanner;
import model.IImage;
import model.ImageProcessorModel;
import utils.ImageUtil;
import view.ImageProcessorView;

/**
 * Represents a save command or operation for an image processing controller.
 * Saves an IImage with a specified name to the model.
 */
public class SaveCommand extends ACommandController {

  /**
   * Constructor for a SaveCommand.
   * @param controller the image processing controller
   * @param model the image processing model
   * @param view the image processing view
   */
  public SaveCommand(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view ) {
    super(controller, model, view);
  }

  @Override
  public void imageCommand(Scanner scan) throws IOException {
    String pathName;
    String imageName;

    pathName = scan.next();
    imageName = scan.next();

    try {
      IImage mapImage = model.obtainImage(imageName);
      new ImageUtil().saveIImageAsFile(mapImage, pathName);
      view.writeMessage("\nImage saved.");
    } catch (IOException e) {
      view.writeMessage("\nFile not found. Please enter a valid file path.");
    }
  }
}
