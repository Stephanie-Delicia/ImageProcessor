package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;

import model.IImage;
import model.IPixel;
import utils.ImageUtil;

/**
 * This is an implementation of the IView interface that uses Java Swing to visualize an image. An
 * ImageGraphicsView serves as a GUI to an image processing program. This GUI for an image
 * processing program contains:
 * - A scrollable image
 * - A histogram of the frequency of a photo's
 * RGB and intensity values There is dropdown for the image transformations below:
 * - flip vertical
 * - flip horizontal
 * - brighten
 * - darken
 * - gray-scale
 * - sepia
 * - blur
 * - sharpen
 * Finally, the GUI has
 * buttons for:
 * - loading an image
 * - saving an image
 */
public class ImageGraphicsView extends JFrame implements IView {

  private static final long serialVersionUID = -5422867905506050133L;
  private JPanel mainPanel;
  private JPanel firstPanel;  // contain image, transforms, save/upload
  private JPanel secondPanel;  // contain graph
  private JPanel comboboxPanel;
  private JLabel comboboxDisplay;
  private JLabel displayImage;

  // Scroll-down options for transformations
  private JComboBox<String> combobox;

  // Current image being worked on in the GUI by a user
  private IImage currentImage;

  // Buttons
  private JButton fileOpenButton;
  private JButton fileSaveButton;

  // Histogram
  private JLabel histogramLabel;
  private JPanel histogramPanel;

  /**
   * Constructor for the image graphics view. Takes no inputs and automatically generates the view
   * with a panel with a scrollbar for the current image being worked on. Also, adds a dropdown for
   * image transformation options, buttons to load or save, and a histogram for frequency data on
   * the current photo.
   *
   * @throws FileNotFoundException if a file is not found
   */
  public ImageGraphicsView() throws FileNotFoundException {
    super();
    setTitle("Image Processor");
    setSize(1200, 800);

    // Set the main panel
    setMainPanel();

    // Set scroll down of transformations.
    setComboTransforms();

    // Set an image for the GUI
    setImagePanel();

    // Set the file and save option buttons
    setFileOpenAndSave();

    // Set the histogram
    setHistogram();

  }

  private void setMainPanel() {
	// last update here
    mainPanel = new JPanel();
    firstPanel = new JPanel();
    secondPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1,2)); // one row and two cols
    firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.PAGE_AXIS)); // along the vertical axis
    firstPanel.setPreferredSize(new Dimension(600, 480));
    secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.PAGE_AXIS)); // along the vertical axis
    secondPanel.setPreferredSize(new Dimension(600, 480));
    mainPanel.add(firstPanel);
    mainPanel.add(secondPanel);
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
  }

  private void setComboTransforms() {
    // image transformation dropdown menu
    comboboxPanel = new JPanel();
    comboboxPanel.setBorder(BorderFactory.createTitledBorder("Transformations"));
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    firstPanel.add(comboboxPanel);
    System.out.println("Got to here.");
    comboboxDisplay = new JLabel("Select an image transformation");
    comboboxPanel.add(comboboxDisplay);
    
    String[] options = new String[] {
    	"intensity-component", "luma-component", "value-component",
        "brighten", "vertical-flip", "horizontal-flip",
        "red-component", "green-component", "blue-component",
        "blur", "sharpen", "sepia-transform",
        "grayscale-transform", "downscale", "mosaic"};
    
    combobox = new JComboBox<String>(options);
    combobox.setUI(new BasicComboBoxUI());
    System.out.println("# items: " + combobox.getItemCount());
    combobox.setActionCommand("Transformation");
    System.out.println("Combobox type: " + combobox);
    comboboxPanel.add(combobox);
    System.out.println("Got to here.");
  }

  private void setImagePanel() {
    // show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    // a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Display"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    firstPanel.add(imagePanel);

    displayImage = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(displayImage);
    imageScrollPane.setPreferredSize(new Dimension(200, 600));
    imagePanel.add(imageScrollPane);
  }

  private void setFileOpenAndSave() {
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Load or Save Image"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    firstPanel.add(dialogBoxesPanel);

    // File open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    fileOpenButton = new JButton("Load an image");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);
    JLabel fileOpenDisplay = new JLabel("File path");
    // -> grab file path chosen by user
    fileopenPanel.add(fileOpenDisplay);

    // File save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    fileSaveButton = new JButton("Save current image");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);
    JLabel fileSaveDisplay = new JLabel("File path");
    filesavePanel.add(fileSaveDisplay);
  }

  private void setHistogram() {
    HashMap<Integer, Integer> emptymap = createEmptyFrequencyMap();
    histogramPanel = new GraphPanel(emptymap, emptymap, emptymap, emptymap);
    histogramLabel = new JLabel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("RGB and Intensity Frequency"));
    secondPanel.add(histogramPanel);
    histogramPanel.add(histogramLabel);
  }

  @Override
  public void setImageIcon(String namepath) {
    // Convert to a bufferedImage to display.
    BufferedImage buffer = null;
    try {
      buffer = new ImageUtil()
          .iImageToBufferedImage(new ImageUtil().convertStringFileToPhoto(namepath));
    } catch (IOException e) {
      throw new IllegalStateException("File not found!");
    }

    displayImage.setIcon(new ImageIcon(buffer));
  }

  @Override
  public void setImageIcon(IImage image) {

    BufferedImage buffer = null;
    try {
      buffer = new ImageUtil().iImageToBufferedImage(image);
    } catch (IOException e) {
      throw new IllegalStateException("File not found!");
    }

    displayImage.setIcon(new ImageIcon(buffer));
  }

  @Override
  public void setListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
    combobox.addActionListener(listener);
  }

  @Override
  public void setCurrentImage(IImage image) {
    this.currentImage = image;
  }

  @Override
  public IImage returnCurrentImage() {
    return this.currentImage;
  }

  @Override
  public void displayHistogram() {
    HashMap<Integer, Integer> mapRed = returnFrequencyArray(currentImage, new RedValue());
    HashMap<Integer, Integer> mapGreen = returnFrequencyArray(currentImage, new GreenValue());
    HashMap<Integer, Integer> mapBlue = returnFrequencyArray(currentImage, new BlueValue());
    HashMap<Integer, Integer> mapValue = returnFrequencyArray(currentImage, new PixelIntensity());
    histogramPanel = new GraphPanel(mapRed, mapGreen, mapBlue, mapValue);
    BufferedImage bufferHistogram = panelToBufferedImage(histogramPanel, 600, 600);
    histogramLabel.setIcon(new ImageIcon(bufferHistogram));
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public JLabel returnComboBox() {
    return this.comboboxDisplay;
  }

  @Override
  public int brightenFactor() {
    String s = JOptionPane.showInputDialog(this,
        "How much do you want to brighten the image by? (percentage)",
        "Brighten Transformation",
        JOptionPane.PLAIN_MESSAGE);
    return Integer.parseInt(s);
  }
  
  @Override
  public int numberSeeds() {
    String s = JOptionPane.showInputDialog(this,
        "How many seeds do you want?",
        "Mosaic Transformation",
        JOptionPane.PLAIN_MESSAGE);
    return Integer.parseInt(s);
  }

  @Override
  public double[] obtainWidthAndHeight() {
    double[] dims = new double[2];
    String s1 = JOptionPane.showInputDialog(this,
        "Enter the new width.",
        "Downscale",
        JOptionPane.PLAIN_MESSAGE);
    dims[0] = Integer.parseInt(s1) * 1.00;
    String s2 = JOptionPane.showInputDialog(this,
        "Enter the new height.",
        "Downscale",
        JOptionPane.PLAIN_MESSAGE);
    dims[1] = Integer.parseInt(s2) * 1.00;
    return dims;
  }

  /**
   * Returns an array of size 256 that contains the frequency of the index value for an image. The
   * first integer is the index. The second integer is the frequency
   *
   * @param image   the image
   * @param command the command for extracting a specific value from a pixel
   * @return the map of frequency of each possible pixel value from 0 - 255
   */
  private HashMap<Integer, Integer> returnFrequencyArray(IImage image,
      HistogramValueCommand command) {
    int width = image.getImageWidth();
    int height = image.getImageHeight();
    int length = 256;
    IPixel[][] pixels = image.getPixels().clone();

    HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
    // Initialize a map with keys 0 - 255 and values of 0.
    for (int i = 0; i < length; i++) {
      frequencyMap.put(i, 0);
    }

    // Recur through each pixel in the photo. For each pixel value occurence, increment to
    // the map.
    for (int j = 0; j < height; j++) {
      for (int k = 0; k < width; k++) {
        int pixelValue = valueCommand(command, pixels[j][k]); // choose what value you want
        int newFrequency = frequencyMap.get(pixelValue) + 1;
        // If key appears, then grab value and increment , then reassign in map.
        frequencyMap.put(pixelValue, newFrequency); // set new frequency
      }
    }

    return frequencyMap;
  }

  /**
   * Grabs a value from a pixel based off given command.
   *
   * @param command the command that indicates which component to grab from given pixel.
   * @param pixel   the IPixel
   * @return a value component of a pixel.
   */
  private int valueCommand(HistogramValueCommand command, IPixel pixel) {
    return command.returnPixelValue(pixel);
  }

  /**
   * Converts a JPanel into a buffered image.
   *
   * @param panel  the JPanel to be converted into a buffered image.
   * @param width  the width of the JPanel
   * @param height the height of the JPanel
   * @return the panel as a buffered image
   */
  private BufferedImage panelToBufferedImage(JPanel panel, int width, int height) {
    BufferedImage newBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D panelGraphics = newBuffer.createGraphics();
    panel.setSize(width, height);
    panel.print(panelGraphics);
    return newBuffer;
  }

  /**
   * Creates a map for an empty histogram to initialize the GUI with.
   * @return a with no values for the frequencies
   */
  private HashMap<Integer, Integer> createEmptyFrequencyMap() {
    HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
    int length = 256;

    for (int i = 0; i < length; i++) {
      frequencyMap.put(i, 0);
    }

    return frequencyMap;
  }
}