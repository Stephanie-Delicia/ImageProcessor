package controller;

import org.junit.Before;

import java.io.FileNotFoundException;

import view.IView;
import view.ImageGraphicsView;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;

/**
 * Represents the test class for an ImageControllerImpl. Some tests use mocks to prevent unecessary
 * image loadings.
 */
public class ProcessorGUIControllerTest {

  ImageProcessorModel model;
  IView view;
  ImageProcessorController controller;

  @Before
  public void init() throws FileNotFoundException {
    model = new ImageProcessorModelImpl();
    view = new ImageGraphicsView();
    controller = new ProcessorControllerGUI(model, view);
  }

  /*
  @Test
  public void testManipulateImage() throws IOException {
    ImageProcessorModel model1 = new ImageProcessorModelImpl();
    IView view1 = new ImageGraphicsView();
    ImageProcessorController controller1 = new ProcessorGUIControllerMock(model1, view1);
    controller1.manipulateImage();
  }

  @Test
  public void testActionListener() throws FileNotFoundException {
    ImageProcessorModel model1 = new ImageProcessorModelImpl();
    IView view1 = new ImageGraphicsView();
    ImageProcessorController controller1 = new ProcessorGUIControllerMock(model1, view1);
  }*/


}