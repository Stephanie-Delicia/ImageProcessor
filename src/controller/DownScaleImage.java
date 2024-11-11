package controller;

import java.io.IOException;
import java.util.Scanner;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents a command for the script program to down-scale an image.
 */
public class DownScaleImage extends ACommandTransform {

  /**
   * Constructor for a ACommandTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public DownScaleImage(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  public void imageCommand(Scanner scan) throws IOException {
    String imageNewName;
    String imageName;
    IImage newImage;

    imageName = scan.next();
    double width = scan.nextInt() * 1.00;
    double height = scan.nextInt() * 1.00;
    imageNewName = scan.next();

    newImage = model.obtainImage(imageName);
    newImage = newImage.downScale(width, height);
    model.addImage(imageNewName, newImage);

    view.writeMessage("\nTransformation performed.");
    System.out.println("\nTransformation performed.");
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return null;
  }
}
