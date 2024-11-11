package controller;

import view.IView;
import model.IImage;
import model.ImageProcessorModel;

/**
 * Represents a horizontal transformation for an image processing model with a controller
 * specially made for a GUI view.
 */
public class HorizontalTransformGUI extends ACommandGUITransform {

  /**
   * Public constructor for a HorizontalTransform. Takes in a view, model,
   * and the number of images added to
   * the model.
   *
   * @param view     the image processing view.
   * @param model    the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public HorizontalTransformGUI(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.flipImageHorizontal();
  }
}
