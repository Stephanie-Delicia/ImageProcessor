package controller;

import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents an image processor controller's operations when an image
 * must be horizontally flipped.
 */
public class HorizontalTransform extends ACommandTransform {

  /**
   * Constructor for a HorizontalTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public HorizontalTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.flipImageHorizontal();
  }
}
