package controller;

import java.io.IOException;
import java.util.Scanner;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to brighten an image from the model.
 */
public class BrightenTransform extends ACommandTransform {

  /**
   * Constructor for a BrightenTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public BrightenTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  public void imageCommand(Scanner scan) throws IOException {
    String imageNewName;
    String imageName;
    IImage newImage;

    int increment = scan.nextInt();
    imageName = scan.next();
    imageNewName = scan.next();

    newImage = model.obtainImage(imageName);
    newImage = newImage.darkenBrightenImage(increment);
    model.addImage(imageNewName, newImage);
    view.writeMessage("\nTransformation performed.");
    System.out.println("\nTransformation performed.");
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return null;
  }
}
