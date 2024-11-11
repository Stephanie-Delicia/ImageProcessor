package controller;

import model.IImage;
import model.ImageProcessorModel;
import model.RedGrayScale;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to convert an image from the model to red grey-scale.
 */
public class RedTransform extends ACommandTransform {

  /**
   * Constructor for a ACommandTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public RedTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new RedGrayScale());
  }
}
