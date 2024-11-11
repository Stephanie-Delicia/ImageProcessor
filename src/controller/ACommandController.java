package controller;

import model.ImageProcessorModel;
import view.ImageProcessorView;

/**
 * Represents the abstract class implementation of an ICommandController. Contains an abstract
 * constructor for other classes that extend this one.
 */
public abstract class ACommandController implements ICommandController {

  protected ImageProcessorController controller;
  protected ImageProcessorModel model;
  protected ImageProcessorView view;

  /**
   * Constructor for a LoadCommand.
   *
   * @param controller the image processor controller
   * @param model      the image processor model
   * @param view       the image processor view
   */
  public ACommandController(ImageProcessorController controller, ImageProcessorModel model,
      ImageProcessorView view) {
    if (controller == null || model == null || view == null) {
      throw new IllegalArgumentException("Inputs are null.");
    }
    this.controller = controller;
    this.model = model;
    this.view = view;
  }
}
