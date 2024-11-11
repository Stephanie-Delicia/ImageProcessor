package controller;

import model.IImage;
import model.ImageProcessorModel;
import model.IntensityGreyScale;
import view.ImageProcessorView;

/**
 * Represents a command that performs an intensity-grayscale transformation
 * on an image.
 */
public class IntensityCommand extends ACommandTransform {

  /**
   * Constructor for a IntensityCommand.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public IntensityCommand(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new IntensityGreyScale());
  }
}
