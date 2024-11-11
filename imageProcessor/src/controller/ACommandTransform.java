package controller;

import java.io.IOException;
import java.util.Scanner;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents an abstract class for creating a command that transforms an image. Extends the
 * ACommandController for the abstract constructor.
 */
public abstract class ACommandTransform extends ACommandController {

  /**
   * Constructor for a ACommandTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public ACommandTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  public void imageCommand(Scanner scan) throws IOException {
    String imageNewName;
    String imageName;
    IImage newImage;

    imageName = scan.next();
    imageNewName = scan.next();

    newImage = model.obtainImage(imageName);
    newImage = returnTransformedImage(newImage);
    model.addImage(imageNewName, newImage);
    view.writeMessage("\nTransformation performed.");
    System.out.println("\nTransformation performed.");
  }

  /**
   * Returns a new transformed image. The type of transformation performed is specific to each
   * implementation of ACommandTransform.
   *
   * @param image the image to be transformed
   * @return a new transformed image
   */
  protected abstract IImage returnTransformedImage(IImage image);
}
