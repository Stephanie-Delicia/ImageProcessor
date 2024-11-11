package controller;

import java.io.IOException;
import java.util.Scanner;
import model.BlueGrayScale;
import model.BlurImageFilter;
import model.GreenGrayScale;
import model.GreyScaleColorTransform;
import model.ICommandTransformation;
import model.IImage;
import model.ImageProcessorModel;
import model.IntensityGreyScale;
import model.LumaGrayScale;
import model.RedGrayScale;
import model.SepiaColorTransform;
import model.SharpenImageFilter;
import model.ValueGreyScale;
import view.ImageProcessorView;

/**
 * Represents an image transformation for a mask transform.
 */
public class MaskTransform extends ACommandTransform {

  /**
   * Constructor for a MaskTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public MaskTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  public void imageCommand(Scanner scan) throws IOException {
    String imageNewName;
    String imageName;
    String imageMask;
    IImage newImage;
    IImage mask;
    String command;

    imageName = scan.next();
    imageMask = scan.next();
    imageNewName = scan.next();
    command = scan.next();

    newImage = model.obtainImage(imageName);
    mask = model.obtainImage(imageMask);

    newImage = newImage.maskImage(mask, returnCommand(command));
    model.addImage(imageNewName, newImage);

    view.writeMessage("\nTransformation performed.");
    System.out.println("\nTransformation performed.");
  }

  private ICommandTransformation returnCommand(String command) {
    switch (command) {
      case ("intensity-component"):
        return new IntensityGreyScale();
      case ("luma-component"):
        return new LumaGrayScale();
      case ("value-component"):
        return new ValueGreyScale();
      case ("red-component"):
        return new RedGrayScale();
      case ("green-component"):
        return new GreenGrayScale();
      case ("blue-component"):
        return new BlueGrayScale();
      case ("blur"):
        return new BlurImageFilter();
      case ("sharpen"):
        return new SharpenImageFilter();
      case ("sepia-transform"):
        return new SepiaColorTransform();
      case ("grayscale-transform"):
        return new GreyScaleColorTransform();
      default:
        System.out.println("Not a valid transformation!");
    }
    return new LumaGrayScale();
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return null;
  }
}
