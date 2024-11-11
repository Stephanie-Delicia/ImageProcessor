package controller;

import view.ImageGraphicsView;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import utils.ImageUtil;
import view.ImageProcessorView;
import view.ImageProcessorViewImpl;

/**
 * Represents the GUI for the image processor program.
 */
public class ImageProcessorGuiRun {

  /**
   * Main class to test run the GUI for the image processor.
   *
   * @param args the arguments.
   */
  public static void main(String[] args) throws IOException {
    ImageProcessorModel model1;
    ImageProcessorView view1;
    ImageProcessorController controller1;
    Appendable out1 = new StringBuffer();
    Readable read1 = new StringReader("");

    // Read in a text file
    if (args.length == 2) {
      if (args[0].equals("-file")) {
        // Read in text file contents
        String filepath = args[1];
        String input = new ImageUtil().textfileToString(filepath);
        read1 = new StringReader(input);

        model1 = new ImageProcessorModelImpl();
        view1 = new ImageProcessorViewImpl(out1);
        controller1 = new ImageProcessorControllerImpl(model1, read1, view1);
        controller1.manipulateImage(new Scanner(read1));

        System.out.println(out1.toString());
      }
    }

    // Interactive Script mode
    if (args.length == 1) {
      if (args[0].equals("-text")) {
        model1 = new ImageProcessorModelImpl();
        view1 = new ImageProcessorViewImpl(out1);
        controller1 = new ImageProcessorControllerImpl(model1, read1, view1);
        controller1.manipulateImage(new Scanner(System.in));

        System.out.println(out1.toString());
      }
    }

    // Open the GUI
    if (args.length == 0) {
      ImageGraphicsView.setDefaultLookAndFeelDecorated(false);
      ImageGraphicsView frame = new ImageGraphicsView();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      ImageProcessorModelImpl model = new ImageProcessorModelImpl();
      ProcessorControllerGUI controller = new ProcessorControllerGUI(model, frame);
      controller.manipulateImage(new Scanner(System.in));

      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
          }
        }
      } catch (UnsupportedLookAndFeelException e) {
        // handle exception
      } catch (ClassNotFoundException e) {
        // handle exception
      } catch (InstantiationException e) {
        // handle exception
      } catch (IllegalAccessException e) {
        // handle exception
      } catch (Exception e) {
        System.out.println("Unable to run program.");
      }
    }
  }
}
