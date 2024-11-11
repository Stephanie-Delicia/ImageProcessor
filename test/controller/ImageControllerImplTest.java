package controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import org.junit.Before;
import org.junit.Test;
import view.ImageProcessorView;
import view.ImageProcessorViewImpl;

/**
 * Represents the test class for an ImageControllerImpl. Some tests use mocks to prevent unecessary
 * image loadings.
 */
public class ImageControllerImplTest {

  ImageProcessorModel model;
  Readable read;
  Appendable destination;
  ImageProcessorView view;
  ImageProcessorController controller;
  String menuAndWelcome;

  @Before
  public void init() {
    model = new ImageProcessorModelImpl();
    destination = new StringBuilder();
    read = new StringReader("");
    view = new ImageProcessorViewImpl(destination);
    controller = new ImageProcessorControllerImplMock(model, read, view);

    menuAndWelcome = "Supported user instructions are: \n"
        + "load image-path image-name\n"
        + "save image-path image-name\n"
        + "value-component image-name dest-image-name\n"
        + "luma-component image-name dest-image-name\n"
        + "intensity-component image-name dest-image-name\n"
        + "green-component image-name dest-image-name\n"
        + "blue-component image-name dest-image-name\n"
        + "red-component image-name dest-image-name\n"
        + "horizontal-flip image-name dest-image-name\n"
        + "vertical-flip image-name dest-image-name\n"
        + "brighten increment image-name dest-image-name\n"
        + "sepia-transform image-name dest-image-name\n"
        + "grayscale-transform image-name dest-image-name\n"
        + "blur image-name dest-image-name\n"
        + "sharpen image-name dest-image-name\n"
        + "q or quit (quit the program) \n"
        + "Welcome!\n"
        + "Type instruction: ";
  }

  /**
   * Tests if constructor throws an exception if the model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInValidModel() {
    ImageProcessorController nullcontroller = new ImageProcessorControllerImpl(null,
        read, view);
  }

  /**
   * Tests if constructor throws an exception if the readable is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInValidReadable() {
    ImageProcessorController nullcontroller = new ImageProcessorControllerImpl(model,
        null, view);
  }

  /**
   * Tests if constructor throws an exception if the view is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInValidView() {
    ImageProcessorController nullcontroller = new ImageProcessorControllerImpl(model, read,
        null);
  }

  /**
   * Tests if the constructor correctly creates am ImageControllerImpl.
   */
  @Test
  public void testCorrectConstructor() {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);

    // The appendable should be empty.
    assertEquals("", out1.toString());
  }

  /**
   * Tests if the controller correctly displays the menu and welcome message after quitting.
   */
  @Test
  public void testMenuAndWelcome() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome, out1.toString());
  }

  /**
   * Tests if the controller correctly transmits to view that an image has been loaded.
   */
  @Test
  public void testLoadedImage() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded.", out1.toString());
  }

  /**
   * Tests if the controller correctly transmits to view that an image has been loaded and saved
   * afterwards without transformations. Image is loaded with name "koala" and saved afterwards.
   */
  @Test
  public void testLoadedSavedImageNoTransformation() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala " // load
        + "save /Users/stephaniemartinez/Documents/OOD/CS3500/hw-4/koala.ppm koala quit"); // save
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nImage saved.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if controller correctly transmits to view that a photo was attempted to be loaded,
   * but an unrecognizable file path was given instead.
   */
  @Test
  public void testLoadedInvalidPathName() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/bkjkjb.ppm koala quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImpl(model1, read1, view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
            + "\nFile not found. Please enter a valid file path.",
        out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * vertical-flip was performed on it.
   */
  @Test
  public void testLoadedVerticalTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "vertical-flip koala koala-blue quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied vertical-flip.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * horizontal-flip was performed on it.
   */
  @Test
  public void testLoadedHorizontalTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "horizontal-flip koala koala-blue quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied horizontal-flip.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * luma-transformation was performed on it.
   */
  @Test
  public void testLoadedLumaTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "luma-component koala koala-luma quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied luma.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * value-transformation was performed on it.
   */
  @Test
  public void testLoadedValueTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "value-component koala koala-value quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied value.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * intensity-transformation was performed on it.
   */
  @Test
  public void testLoadedIntensityTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "intensity-component koala koala-intensity quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied intensity.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * brighten was performed on it.
   */
  @Test
  public void testLoadedBrigthenTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "brighten 10 koala koala-brighter quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied brighten.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * red-transformation was performed on it.
   */
  @Test
  public void testLoadedRedTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "red-component koala koala-red quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied red.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * green-transformation was performed on it.
   */
  @Test
  public void testLoadedGreenTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "green-component koala koala-green quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied green.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an image was loaded and a
   * blue-transformation was performed on it.
   */
  @Test
  public void testLoadedBlueTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/Koala.ppm koala "
        + "blue-component koala koala-blue quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied blue.", out1.toString()); // saved into computer
  }

  @Test
  public void testSepiaTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/manhattan-small.png manhattan"
        + "sepia-transform manhattan manhattan-sepia quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied sepia.", out1.toString()); // saved into computer
  }

  @Test
  public void testBlurTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/manhattan-small.png manhattan"
        + "blur manhattan manhattan-blur quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied blur.", out1.toString()); // saved into computer
  }

  @Test
  public void testSharpenTransform() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("load /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/images/manhattan-small.png manhattan"
        + "sharpen manhattan manhattan-sharpen quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nImage loaded." // loaded
        + "\nApplied sharpen.", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that an invalid command was
   * input by the user.
   */
  @Test
  public void testInValidCommand() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("orange-component quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "\nPlease input a valid command.\n", out1.toString()); // saved into computer
  }

  /**
   * Tests to see if the controller correctly transmits to the view that a save command was
   * attempted but no image with that name was stored in the model.
   */
  @Test
  public void testNoSavedImage() throws IOException {
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("save /Users/stephaniemartinez"
        + "/Documents/OOD/CS3500/hw-4/koala.ppm koala quit");
    ImageProcessorModelImpl model1 = new ImageProcessorModelImpl();
    ImageProcessorView view1 = new ImageProcessorViewImpl(out1);
    ImageProcessorController controller1 = new ImageProcessorControllerImplMock(model1, read1,
        view1);
    controller1.manipulateImage(new Scanner(this.read));

    // Check if welcome and menu messages are displayed
    assertEquals(menuAndWelcome
        + "Image saved.", out1.toString()); // saved into computer
  }
}