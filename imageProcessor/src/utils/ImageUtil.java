package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import model.IPixel;
import model.IImage;
import model.ImageImpl;
import model.RGBPixel;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Saves an Image as a PPM file.
   *
   * @param imageName the name of the PPM file with file path.
   * @throws IOException if the file transmission fails.
   */
  public static void imageToPPM(String imageName, IImage image) throws IOException {
    int width = image.getImageWidth();
    int height = image.getImageHeight();

    File imagePPM = new File(imageName);
    FileWriter writer = new FileWriter(imagePPM);

    writer.write("P3\n" + width + " " + height + "\n255\n");

    IPixel[][] pixels = image.getPixels();
    IPixel pixel;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixel = pixels[i][j];
        int red = pixel.redChannelPixel();
        int green = pixel.greenChannelPixel();
        int blue = pixel.blueChannelPixel();
        writer.write(String.format("%d %d %d ", red, green, blue));
      }
    }

    writer.close();
  }

  /**
   * Converts a file into an Image.
   *
   * @param pathName the name of the image
   * @return the jpg or png image as an Image class type
   */
  public static BufferedImage fileToBufferedImage(String pathName) {
    BufferedImage imageBuff = null;
    BufferedImage imageEx;
    try {
      imageEx = ImageIO.read(new File(pathName));
      imageBuff = new BufferedImage(imageEx.getWidth(), imageEx.getHeight(),
          BufferedImage.TYPE_INT_RGB);
      imageBuff = ImageIO.read(new File(pathName));

      System.out.println("Reading successful.");
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }

    return imageBuff;
  }

  /**
   * Given an IImage, converts it into a Buffered Image.
   *
   * @param image the image to be converted into a Buffered Image.
   * @return the image as a buffered image.
   */
  public BufferedImage iImageToBufferedImage(IImage image) throws IOException {

    int width = image.getImageWidth();
    int height = image.getImageHeight();
    IPixel[][] pixels = image.getPixels();
    BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    int sizeArray = 0;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        IPixel pixel = pixels[i][j];
        int rgb = new Color(pixel.redChannelPixel(),
            pixel.greenChannelPixel(), pixel.blueChannelPixel()).getRGB();
        sizeArray++;
        buffImage.setRGB(j, i, rgb);
      }
    }

    return buffImage;
  }

  /**
   * Saves the given image to the given path.
   *
   * @param pathname the name of path image will be saved to
   * @param image    the image to be saved
   */
  public static void saveImage(String pathname, BufferedImage image,
      String formatType) throws IOException {

    File output = new File(pathname);
    ImageIO.write(image, formatType, output);
  }

  /**
   * Given a BufferedImages, converts it into an IIMage.
   *
   * @param bufferImage the buffer image to be converted
   * @return the IImage of the BufferedImage
   */
  public IImage bufferedImageToIImage(BufferedImage bufferImage) {
    int width = bufferImage.getWidth();
    int height = bufferImage.getHeight();
    IPixel[][] pixelsBuffer = new IPixel[height][width];
    // Grab each pixel from BufferedImage
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int color = bufferImage.getRGB(j, i);
        int blue = color & 0xff;
        int green = (color & 0xff00) >> 8;
        int red = (color & 0xff0000) >> 16;
        pixelsBuffer[i][j] = new RGBPixel(red, green, blue);
      }
    }

    // return image as IImage
    return new ImageImpl(pixelsBuffer);
  }

  /**
   * Converts a String of a ppm file into an Image.
   *
   * @param filename the name of the file
   * @return the ppm file as an Image
   * @throws FileNotFoundException if the filename cannot be found
   */
  public IImage ppmToImage(String filename) {
    Scanner sc;

    // Scanner
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    // Create image
    IPixel[][] pixelsPPM = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        // Create new pixel
        pixelsPPM[i][j] = new RGBPixel(r, g, b);
      }
    }
    // return ppm as image impl.
    return new ImageImpl(pixelsPPM);
  }

  /**
   * Given a pathname for an image (.ppm, .jpeg, or .png), saves the file image as an IImage.
   *
   * @param pathname the location of the image
   * @return the image as an IImage
   */
  public IImage convertStringFileToPhoto(String pathname) {
    int length = pathname.length();
    String type = pathname.substring(length - 3);
    String type4 = pathname.substring(length - 4);
    IImage returnImage = null;
    if (type.equalsIgnoreCase("png") || type.equalsIgnoreCase("jpg")) {
      BufferedImage buffer = fileToBufferedImage(pathname);
      returnImage = bufferedImageToIImage(buffer);
    } else if (type.equalsIgnoreCase("ppm")) {
      returnImage = new ImageUtil().ppmToImage(pathname);
    }

    return returnImage;
  }

  /**
   * Given an IImage, saves it as either a .ppm, .jpeg, .png.
   *
   * @param image    the image to be saved
   * @param pathname the pathname to save the image too
   */
  public void saveIImageAsFile(IImage image, String pathname) throws IOException {
    int length = pathname.length();
    String type = pathname.substring(length - 3);
    if (type.equalsIgnoreCase("png") || type.equalsIgnoreCase("jpg")) {
      BufferedImage buffer = new ImageUtil().iImageToBufferedImage(image);
      new ImageUtil().saveImage(pathname, buffer, type);
    } else if (type.equalsIgnoreCase("ppm")) {
      imageToPPM(pathname, image);
    }
  }

  /**
   * Given a textfile path, returns a string of its contents.
   */
  public String textfileToString(String pathname) throws FileNotFoundException {
    System.out.println("Got here.");
    String fileString = "";
    Scanner sc;
    sc = new Scanner(new FileInputStream(pathname));
    // Scan each line of the file and append to overall string
    while (sc.hasNextLine()) {
      fileString += sc.nextLine();
    }
    return fileString;
  }
}

