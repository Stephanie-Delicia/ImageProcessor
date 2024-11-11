package controller;

import view.IView;
import model.IImage;
import model.ImageProcessorModel;

/**
 * Command for a brighten transform.
 */
public class BrightenTransformGUI extends ACommandGUITransform {

  /**
   * Public constructor for a ACommandGUI. Takes in a view, model, and the number
   * of images added to
   * the model.
   *
   * @param view     the image processing view.
   * @param model    the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public BrightenTransformGUI(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    int increment = (int) view.brightenFactor();
    return image.darkenBrightenImage(increment);
  }
}
