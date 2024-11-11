package controller;

import model.IImage;
import model.ImageProcessorModel;
import view.IView;

/**
 * Represents a command to perform a mosaic transformation on an image.
 */
public class MosaicCommand extends ACommandGUITransform {

  /**
   * Public constructor for MosaicCommand. Takes in a view, model, and the number of
   * images added to the model.
   * @param view the image processing view.
   * @param model the image processing model.
   * @param numImage the number of images stored in model thus far.
   * @param seeds the number of seeds
   */
  public MosaicCommand(IView view, ImageProcessorModel model, int numImage) {
    super(view, model, numImage);
  }

  @Override
  protected IImage returnTransformedImage(IImage image) {
	  int seeds = (int) view.numberSeeds();
	  return image.createMosaic(seeds);
  }
}
