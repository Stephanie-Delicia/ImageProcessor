package controller;

import model.IImage;
import model.ImageProcessorModel;
import model.ValueGreyScale;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to convert an image from the model to value grey-scale.
 */
public class ValueTransform extends ACommandTransform {

  /**
   * Constructor for a ValueTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public ValueTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new ValueGreyScale());
  }
}
