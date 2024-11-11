package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.util.Scanner;

import view.IView;

/**
 * Represents a processor gui controller mock to help test the controller's communication withe the
 * GUI view.
 */
public class ProcessorGUIControllerMock implements ImageProcessorController, ActionListener {

  private final IView view;
  public String output; // made public to test string outputs in tests

  /**
   * Constructor for a ProcessorControllerGUI. Takes in an image processing model and view.
   *
   * @param view the image processing GUI view
   */
  public ProcessorGUIControllerMock(IView view) {
    if (view == null) {
      throw new IllegalArgumentException("These inputs are null or invalid!");
    }

    this.view = view;
    this.output = "";
    view.setListener(this);
  }

  @Override
  public void manipulateImage(Scanner scan) throws IOException {
    view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      // Loading an image
      case "Open file": {
        output += "Open file\n";
      }
      break;
      case "Save file": {
        output += "Save file\n";
      }
      break;
      case "Transformation": {
        output += "Transformation\n";
      }
      break;
      default:
        System.out.println("\nPlease input a valid command.");
    }
  }

  /**
   * Given a string input, executes the given command for the controller. Acts like a mini
   * controller for image transformations.
   *
   * @param command the given command as string
   */
  private void imageTransformation(String command) {
    switch (command) {
      case "value-component":
        output += "value-component\n";
        break;
      case "intensity-component":
        output += "intensity-component\n";
        break;
      case "luma-component":
        output += "luma-component\n";
        break;
      case "red-component":
        output += "red-component\n";
        break;
      case "green-component":
        output += "green-component\n";
        break;
      case "blue-component":
        output += "blue-component\n";
        break;
      case "horizontal-flip":
        output += "horizontal-flip\n";
        break;
      case "vertical-flip":
        output += "vertical-flip\n";
        break;
      case "sharpen":
        output += "sharpen\n";
        break;
      case "blur":
        output += "blur\n";
        break;
      case "sepia-transform":
        output += "sepia-transform\n";
        break;
      case "grayscale-transform":
        output += "grayscale-transform\n";
        break;
      case "brighten":
        output += "brighten\n";
        break;
      default:
        System.out.println("\nPlease input a valid command!");
    }
  }

}