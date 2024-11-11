
package view;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import model.IImage;

/**
 * The view interface. To motivate the methods here
 * think about all the operations that the controller
 * would need to invoke on the view
 */
public interface IView {

  /**
   * Opens two box panels in which the user can type first the width then
   * the height for a new down-scaled image.
   * @return
   */
  public double[] obtainWidthAndHeight();

  /**
   * Opens a box panel in which a user can type the amount they want to brighten
   * an image by. Returns the amount the user inputs.
   * @return the amount user wants to brighten an image by
   */
  public int brightenFactor();
  
  /**
   * Opens a box panel in which a user can type the amount of seeds for mosaic.
   * @return the amount of seeds
   */
  public int numberSeeds();

  /**
   * Sets the specific image icon to the current image with the filepath name.
   * @param namepath the filepath of the image to set the image icon to.
   */
  public void setImageIcon(String namepath);

  /**
   * Sets the specific image icon to the current IImage.
   * @param image the IImage to set the image icon to.
   */
  public void setImageIcon(IImage image);

  /**
   * Returns the combobox to read its inputs for the controller that contains an implementation
   * of this view interface.
   * @return the combobox with list of transformations
   */
  public JLabel returnComboBox();

  /**
   * Set an object to be the action listener for any actions with buttons.
   * @param listener the listener
   */
  void setListener(ActionListener listener);

  /**
   * Sets the current image to be displayed for the view.
   * @param image the current image for the view.
   */
  void setCurrentImage(IImage image);

  /**
   * Returns the current image of the view.
   * @return the current image
   */
  public IImage returnCurrentImage();

  /**
   * Makes the view visible to the user.
   */
  void makeVisible();

  /**
   * Displays the histogram of the view with data on the current image of the view.
   */
  public void displayHistogram();
}
