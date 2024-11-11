package controller;

import view.IView;
import model.GreyScaleColorTransform;
import model.IImage;
import model.ImageProcessorModel;

/**
 * Represents a greyscale transformation for an image processing model with a controller
 * specially made for a GUI view.
 */
public class GrayScaleTransformGUI extends ACommandGUITransform {

  /**
   * Public constructor for a SepiaTransform. Takes in a view,
   * model, and the number of images added to
   * the model.
   *
   * @param view     the image processing view.
   * @param model    the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public GrayScaleTransformGUI(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    return image.colorTransform(new GreyScaleColorTransform());
  }
}
