package controller;

import model.BlurImageFilter;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to blur an image from the model.
 */
public class BlurTransform extends ACommandTransform {

  /**
   * Constructor for a BlurTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public BlurTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.filterImage(new BlurImageFilter());
  }
}
