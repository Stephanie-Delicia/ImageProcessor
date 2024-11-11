package controller;

import view.IView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.IImage;
import model.ImageProcessorModel;
import utils.ImageUtil;

/**
 * Represents a controller for an image processor program with a GUI. This controller is composed of
 * an image processing model and a GUI view for the image processor. The controller acts as the
 * action listener for the GUI and executes commands that a user requests from the GUI view. The
 * transformations provided by the GUI are listed below:
 * - flip vertically
 * - flip horizontally
 * - brighten/darken
 * - red-component
 * - green-component
 * - blue-component
 * - value-component
 * - intensity-component
 * - luma-component
 * - grayscale
 * - sepia
 * - blur
 * - sharpen
 * - save an image
 * - load an image
 */
public class ProcessorControllerGUI implements ImageProcessorController, ActionListener {

  private final ImageProcessorModel model;
  private final IView view;

  /**
   * Constructor for a ProcessorControllerGUI. Takes in an image processing model and view.
   *
   * @param model the image processing model
   * @param view  the image processing GUI view
   */
  public ProcessorControllerGUI(ImageProcessorModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Inputs are null.");
    }
    this.model = model;
    this.view = view;
    view.setListener(this);
  }

  @Override
  public void manipulateImage(Scanner scan) throws IOException {
    view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	System.out.println("Action performed.");
    String pathName = "";

    switch (e.getActionCommand()) {
      // Load an image
      case "Open file": {
        pathName = returnFilePath();
        openImageFile();
      }
      break;
      // Save an image
      case "Save file": {
        saveImage();
      }
      break;
      // Perform an image transformation
      case "Transformation": {
        performTransformation(e);
      }
      break;
      default:
        System.out.println("\nUnsupported action selected.");
    }
  }

  /**
   * Given a string input, executes the given command for the controller. Acts like a mini
   * controller for image transformations.
   *
   * @param command the given command as string
   */
  private void imageTransformation(String command, int numFileAdded) {
	System.out.println("Action performed.");
    switch (command) {
      case "value-component":
        new ValueTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "intensity-component":
        new IntensityTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "luma-component":
        new LumaTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "red-component":
        new RedTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "green-component":
        new GreenTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "blue-component":
        new BlueTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "horizontal-flip":
        new HorizontalTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "vertical-flip":
        new VerticalTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "sharpen":
        new SharpenTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "blur":
        new BlurTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "sepia-transform":
        new SepiaTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "grayscale-transform":
        new GrayScaleTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "brighten":
        new BrightenTransformGUI(view, model, numFileAdded).imageCommand();
        break;
      case "downscale":
        new DownScaleImageGUI(view, model, numFileAdded).imageCommand();
        break;
      case "mosaic":
        new MosaicCommand(view, model, numFileAdded).imageCommand();
        break;
      default:
        System.out.println("\nPlease input a valid command.");
    }
  }



  /**
   * Opens panel for user to select an image to load. Returns string of image path.
   *
   * @return
   */
  private String returnFilePath() {
    String pathName = "";
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Images",
        "jpg",
        "png",
        "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog((Component) view);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      // Save file
      File f = fchooser.getSelectedFile();
      pathName = f.getAbsolutePath();
    }

    return pathName;
  }

  /**
   * Opens file panel and updates current image and histogram displayed.
   */
  private void openImageFile() {
    updateImageAndHistogram(returnFilePath());
  }

  /**
   * Updates the current image displayed on view as well as the histogram.
   *
   * @param pathName the pathname of the new image to be displayed
   */
  private void updateImageAndHistogram(String pathName) {
    IImage loadedImage = new ImageUtil().convertStringFileToPhoto(pathName);
    model.addImage("image", loadedImage);
    view.setImageIcon(pathName);
    view.setCurrentImage(loadedImage);
    view.displayHistogram();
  }

  /**
   * Save an image given an IImage.
   */
  private void saveImage() {
    String pathName = returnFilePath();
    try {
      new ImageUtil().saveIImageAsFile(view.returnCurrentImage(), pathName);
    } catch (IOException ioException) {
      throw new IllegalStateException("Unable to save file!");
    }
  }

  /**
   * Performs correct transformation depending on action of user.
   * @param e the action event
   */
  private void performTransformation(ActionEvent e) {
	System.out.println("Got to performTransformation.");
    if (e.getSource() instanceof JComboBox) {
      JComboBox<String> box = (JComboBox<String>) e.getSource();
      String option = (String) box.getSelectedItem();
      imageTransformation(option, 1);
      view.returnComboBox().setText("You selected: " + (String) box.getSelectedItem());
    }
  }
}

