package controller;

import model.IImage;
import model.ImageProcessorModel;
import model.SepiaColorTransform;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to convert an image from the model to sepia.
 */
public class SepiaTransform extends ACommandTransform {

  /**
   * Constructor for a SepiaTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public SepiaTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.colorTransform(new SepiaColorTransform());
  }
}
