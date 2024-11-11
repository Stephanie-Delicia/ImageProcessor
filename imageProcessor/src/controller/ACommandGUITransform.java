package controller;

import model.IImage;
import model.ImageProcessorModel;
import view.IView;

/**
 * Represents an abstract class that inherits the abstract constructor of an
 * ACommandGUI.
 */
public abstract class ACommandGUITransform extends ACommandGUI {

  /**
   * Public constructor for a ACommandGUI. Takes in a view, model,
   * and the number of images added to
   * the model.
   *
   * @param view     the image processing view.
   * @param model    the image processing model.
   * @param numImage the number of images stored in model thus far.
   */
  public ACommandGUITransform(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  public void imageCommand() {
    IImage currentImage = view.returnCurrentImage();
    IImage transformedImage;

    // Create new transformed image
    transformedImage = returnTransformedImage(currentImage);
    // Add image to model
    model.addImage("image-" + numImage, transformedImage);
    System.out.println("Transformation complete.");

    // Update new photo to GUI.
    view.setImageIcon(transformedImage);
    view.setCurrentImage(transformedImage);
    view.displayHistogram();
  }

  /**
   * Returns a new transformed image.
   * The type of transformation performed is specific to each
   * implementation of ACommandGUITransform.
   *
   * @param image the image to be transformed
   * @return a new transformed image
   */
  protected abstract IImage returnTransformedImage(IImage image);
}