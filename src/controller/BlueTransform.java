package controller;

import model.BlueGrayScale;
import model.IImage;
import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to convert an image from the model to blue grey-scale.
 */
public class BlueTransform extends ACommandTransform {

  /**
   * Constructor for a BlueTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public BlueTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new BlueGrayScale());
  }
}
