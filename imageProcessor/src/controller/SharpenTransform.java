package controller;

import model.IImage;
import model.ImageProcessorModel;
import model.SharpenImageFilter;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to sharpen an image from the model.
 */
public class SharpenTransform extends ACommandTransform {

  /**
   * Constructor for a SharpenTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public SharpenTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.filterImage(new SharpenImageFilter());
  }
}
