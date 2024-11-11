package controller;

import view.IView;
import model.ImageProcessorModel;

/**
 * Represents the abstract class for an implementation of CommandControllerGUI.
 * Contains the abstract constructor
 */
public abstract class ACommandGUI implements CommandControllerGUI {
  protected IView view;
  protected final ImageProcessorModel model;
  protected final int numImage;

  /**
   * Public constructor for a ACommandGUI. Takes in a view, model, and the number of
   * images added to the model.
   * @param view the image processing view.
   * @param model the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public ACommandGUI(IView view, ImageProcessorModel model, int numImage) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }

    this.view = view;
    this.model = model;
    this.numImage = numImage;
  }
}