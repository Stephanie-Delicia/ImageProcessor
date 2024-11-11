package controller;

import view.IView;
import model.IImage;
import model.ImageProcessorModel;
import model.LumaGrayScale;

/**
 * Represents a luma transformation for an image processing model with a controller
 * specially made for a GUI view.
 */
public class LumaTransformGUI extends ACommandGUITransform {

  /**
   * Public constructor for LumaTransform. Takes in a view, model, and the number of
   * images added to the model.
   * @param view the image processing view.
   * @param model the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public LumaTransformGUI(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.grayScaleImage(new LumaGrayScale());
  }
}
