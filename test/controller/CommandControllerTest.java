package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import view.ImageProcessorView;
import view.ImageProcessorViewImpl;

/**
 * Tests the command function objects used in the controller implementation.
 */
public class CommandControllerTest {

  ImageProcessorModel model;
  Readable read;
  Appendable destination;
  ImageProcessorView view;
  ImageProcessorController controller;
  String menuAndWelcome;
  ICommandController blue;
  ICommandController blur;
  ICommandController green;
  ICommandController greyscale;
  ICommandController horiztonal;
  ICommandController luma;
  ICommandController red;
  ICommandController sepia;
  ICommandController sharpen;
  ICommandController value;
  ICommandController vertical;

  @Before
  public void init() {

    model = new ImageProcessorModelImpl();
    destination = new StringBuilder();
    read = new StringReader("");
    view = new ImageProcessorViewImpl(destination);
    controller = new ImageProcessorControllerImplMock(model, read, view);
    blue = new BlueTransform(controller, model, view);
    blur = new BlurTransform(controller, model, view);
    green = new GreenTransform(controller, model, view);
    greyscale = new GrayScaleTransform(controller, model, view);
    horiztonal = new HorizontalTransform(controller, model, view);
    luma = new LumaTransform(controller, model, view);
    red = new RedTransform(controller, model, view);
    sepia = new SepiaTransform(controller, model, view);
    sharpen = new SharpenTransform(controller, model, view);
    value = new ValueTransform(controller, model, view);
    vertical = new VerticalTransform(controller, model, view);

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

  @Test(expected = IllegalArgumentException.class)
  public void testBlueNull() {
    ICommandController nullConstructor = new BlueTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurNull() {
    ICommandController nullConstructor = new BlurTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrigthenNull() {
    ICommandController nullConstructor = new BrightenTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGrayScaleNull() {
    ICommandController nullConstructor = new GrayScaleTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenNull() {
    ICommandController nullConstructor = new GreenTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHorizontalNull() {
    ICommandController nullConstructor = new HorizontalTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLumaNull() {
    ICommandController nullConstructor = new LumaTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedNull() {
    ICommandController nullConstructor = new RedTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaNull() {
    ICommandController nullConstructor = new SepiaTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpen() {
    ICommandController nullConstructor = new SharpenTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueNull() {
    ICommandController nullConstructor = new ValueTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVerticalTransform() {
    ICommandController nullConstructor = new VerticalTransform(null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    ICommandController nullConstructor = new SepiaTransform(controller, null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    ICommandController nullConstructor = new SharpenTransform(controller, model, null);
  }
  // NOTE: the mock is calling the commands and the ImageControllerImpl uses the mock
}