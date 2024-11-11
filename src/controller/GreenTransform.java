package controller;

import model.GreenGrayScale;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to green grey-scale an image from the model.
 */
public class GreenTransform extends ACommandTransform {

  /**
   * Constructor for a GreenTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public GreenTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new GreenGrayScale());
  }
}
