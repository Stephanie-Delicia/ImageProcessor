package controller;

import model.IImage;
import model.ImageProcessorModel;
import model.LumaGrayScale;
import view.ImageProcessorView;

/**
 * Represents the controller's operation to convert an image from the model to luma grey-scale.
 */
public class LumaTransform extends ACommandTransform {

  /**
   * Constructor for a LumaTransform.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public LumaTransform(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    super(controller, model, view);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new LumaGrayScale());
  }
}
