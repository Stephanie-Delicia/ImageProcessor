package controller;

import model.GreyScaleColorTransform;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to grey-scale an image from the model by matrix
 * operation.
 */
public class GrayScaleTransform extends ACommandTransform {
  
  /**
   * Constructor for a GrayScaleTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public GrayScaleTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.colorTransform(new GreyScaleColorTransform());
  }
}
