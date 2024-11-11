package controller;

import model.IImage;
import model.ImageProcessorModel;
import view.IView;

/**
 * Represents a command for the GUI program to down-scale an image.
 */
public class DownScaleImageGUI extends ACommandGUITransform {

  /**
   * Public constructor for a ACommandGUI. Takes in a view, model, and the number of images added to
   * the model.
   *
   * @param view     the image processing view.
   * @param model    the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public DownScaleImageGUI(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
    double[] dims = view.obtainWidthAndHeight();
    return image.downScale(dims[0], dims[1]);
  }
}
