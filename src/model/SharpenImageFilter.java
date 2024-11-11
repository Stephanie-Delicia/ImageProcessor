package model;

/**
 * Represents an operation to filter an image by sharpening it. Implements ICommandFilterImage.
 */
public class SharpenImageFilter implements ICommandTransformation {

  private double[][] sharpen;

  /**
   * Constructor for SharpenImageFilter.
   */
  public SharpenImageFilter() {
    sharpen = new double[][]
        {{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}};
  }

  /**
   * Produces a new filtered image based off the given one.
   *
   * @param image the image to be filtered.
   * @return a new IImage that is the filtered version of the given one.
   */
  @Override
  public IImage performTransformation(IImage image) {

    int width = image.getImageWidth();
    int height = image.getImageHeight();
    IPixel[][] pixels = new IPixel[height][width];
    IPixel[][] oldPixels = image.getPixels();
    int countRow = 0;
    int countCol = 0;

    // create an image with a "border" of zeroes
    IPixel[][] oldPixelsZero = addZerosBorder(oldPixels, width, height);

    // Recur image pixels
    for (int i = 0; i < height + 2; i++) {
      for (int j = 0; j < width + 2; j++) {
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;

        if (i > 1 && i < height + 2 && j > 1 && j < width + 2) {
          // Convert RGB values
          redSum = reachBound(Math.toIntExact(Math.round(newPixel(oldPixelsZero,
              "red", i, j))));
          greenSum = reachBound(Math.toIntExact(Math.round(newPixel(oldPixelsZero,
              "green", i, j))));
          blueSum = reachBound(Math.toIntExact(Math.round(newPixel(oldPixelsZero,
              "blue", i, j))));

          // Create new pixel
          IPixel newPixel = new RGBPixel(Math.toIntExact(Math.round(redSum)),
              Math.toIntExact(Math.round(greenSum)), Math.toIntExact(Math.round(blueSum)));
          // Add to new pixel matrix
          pixels[i - 2][j - 2] = newPixel;
        }

        countCol++;
      }
      countRow++;
    }

    return new ImageImpl(pixels);
  }

  /**
   * Returns the new pixel with the gaussian matrix applied.
   *
   * @param oldPixels the 2D array containing the old pixels
   * @param channel   the red, green, or blue value
   * @param i         the row that the for loop is currently on
   * @param j         the column that the for loop is currently on
   * @return a double representing the value of the given channel for a pixel
   */
  private double newPixel(IPixel[][] oldPixels, String channel, int i, int j) {
    double sum = 0.0;
    if (channel.equals("red")) {
      sum = sharpen[0][0] * oldPixels[i - 2][j - 2].redChannelPixel()
          + sharpen[0][1] * oldPixels[i - 2][j - 1].redChannelPixel()
          + sharpen[0][2] * oldPixels[i - 2][j].redChannelPixel()
          + sharpen[0][3] * oldPixels[i - 2][j + 1].redChannelPixel()
          + sharpen[0][4] * oldPixels[i - 2][j + 2].redChannelPixel()
          + sharpen[1][0] * oldPixels[i - 1][j - 2].redChannelPixel()
          + sharpen[1][1] * oldPixels[i - 1][j - 1].redChannelPixel()
          + sharpen[1][2] * oldPixels[i - 1][j].redChannelPixel()
          + sharpen[1][3] * oldPixels[i - 1][j + 1].redChannelPixel()
          + sharpen[1][4] * oldPixels[i - 1][j + 2].redChannelPixel()
          + sharpen[2][0] * oldPixels[i][j - 2].redChannelPixel()
          + sharpen[2][1] * oldPixels[i][j - 1].redChannelPixel()
          + sharpen[2][2] * oldPixels[i][j].redChannelPixel()
          + sharpen[2][3] * oldPixels[i][j + 1].redChannelPixel()
          + sharpen[2][4] * oldPixels[i][j + 2].redChannelPixel()
          + sharpen[3][0] * oldPixels[i + 1][j - 2].redChannelPixel()
          + sharpen[3][1] * oldPixels[i + 1][j - 1].redChannelPixel()
          + sharpen[3][2] * oldPixels[i + 1][j].redChannelPixel()
          + sharpen[3][3] * oldPixels[i + 1][j + 1].redChannelPixel()
          + sharpen[3][4] * oldPixels[i + 1][j + 2].redChannelPixel()
          + sharpen[4][0] * oldPixels[i + 2][j - 2].redChannelPixel()
          + sharpen[4][1] * oldPixels[i + 2][j - 1].redChannelPixel()
          + sharpen[4][2] * oldPixels[i + 2][j].redChannelPixel()
          + sharpen[4][3] * oldPixels[i + 2][j + 1].redChannelPixel()
          + sharpen[4][4] * oldPixels[i + 2][j + 2].redChannelPixel();
    } else if (channel.equals("green")) {
      sum = sharpen[0][0] * oldPixels[i - 2][j - 2].greenChannelPixel()
          + sharpen[0][1] * oldPixels[i - 2][j - 1].greenChannelPixel()
          + sharpen[0][2] * oldPixels[i - 2][j].greenChannelPixel()
          + sharpen[0][3] * oldPixels[i - 2][j + 1].greenChannelPixel()
          + sharpen[0][4] * oldPixels[i - 2][j + 2].greenChannelPixel()
          + sharpen[1][0] * oldPixels[i - 1][j - 2].greenChannelPixel()
          + sharpen[1][1] * oldPixels[i - 1][j - 1].greenChannelPixel()
          + sharpen[1][2] * oldPixels[i - 1][j].greenChannelPixel()
          + sharpen[1][3] * oldPixels[i - 1][j + 1].greenChannelPixel()
          + sharpen[1][4] * oldPixels[i - 1][j + 2].greenChannelPixel()
          + sharpen[2][0] * oldPixels[i][j - 2].greenChannelPixel()
          + sharpen[2][1] * oldPixels[i][j - 1].greenChannelPixel()
          + sharpen[2][2] * oldPixels[i][j].greenChannelPixel()
          + sharpen[2][3] * oldPixels[i][j + 1].greenChannelPixel()
          + sharpen[2][4] * oldPixels[i][j + 2].greenChannelPixel()
          + sharpen[3][0] * oldPixels[i + 1][j - 2].greenChannelPixel()
          + sharpen[3][1] * oldPixels[i + 1][j - 1].greenChannelPixel()
          + sharpen[3][2] * oldPixels[i + 1][j].greenChannelPixel()
          + sharpen[3][3] * oldPixels[i + 1][j + 1].greenChannelPixel()
          + sharpen[3][4] * oldPixels[i + 1][j + 2].greenChannelPixel()
          + sharpen[4][0] * oldPixels[i + 2][j - 2].greenChannelPixel()
          + sharpen[4][1] * oldPixels[i + 2][j - 1].greenChannelPixel()
          + sharpen[4][2] * oldPixels[i + 2][j].greenChannelPixel()
          + sharpen[4][3] * oldPixels[i + 2][j + 1].greenChannelPixel()
          + sharpen[4][4] * oldPixels[i + 2][j + 2].greenChannelPixel();
    } else if (channel.equals("blue")) {
      sum = sharpen[0][0] * oldPixels[i - 2][j - 2].blueChannelPixel()
          + sharpen[0][1] * oldPixels[i - 2][j - 1].blueChannelPixel()
          + sharpen[0][2] * oldPixels[i - 2][j].blueChannelPixel()
          + sharpen[0][3] * oldPixels[i - 2][j + 1].blueChannelPixel()
          + sharpen[0][4] * oldPixels[i - 2][j + 2].blueChannelPixel()
          + sharpen[1][0] * oldPixels[i - 1][j - 2].blueChannelPixel()
          + sharpen[1][1] * oldPixels[i - 1][j - 1].blueChannelPixel()
          + sharpen[1][2] * oldPixels[i - 1][j].blueChannelPixel()
          + sharpen[1][3] * oldPixels[i - 1][j + 1].blueChannelPixel()
          + sharpen[1][4] * oldPixels[i - 1][j + 2].blueChannelPixel()
          + sharpen[2][0] * oldPixels[i][j - 2].blueChannelPixel()
          + sharpen[2][1] * oldPixels[i][j - 1].blueChannelPixel()
          + sharpen[2][2] * oldPixels[i][j].blueChannelPixel()
          + sharpen[2][3] * oldPixels[i][j + 1].blueChannelPixel()
          + sharpen[2][4] * oldPixels[i][j + 2].blueChannelPixel()
          + sharpen[3][0] * oldPixels[i + 1][j - 2].blueChannelPixel()
          + sharpen[3][1] * oldPixels[i + 1][j - 1].blueChannelPixel()
          + sharpen[3][2] * oldPixels[i + 1][j].blueChannelPixel()
          + sharpen[3][3] * oldPixels[i + 1][j + 1].blueChannelPixel()
          + sharpen[3][4] * oldPixels[i + 1][j + 2].blueChannelPixel()
          + sharpen[4][0] * oldPixels[i + 2][j - 2].blueChannelPixel()
          + sharpen[4][1] * oldPixels[i + 2][j - 1].blueChannelPixel()
          + sharpen[4][2] * oldPixels[i + 2][j].blueChannelPixel()
          + sharpen[4][3] * oldPixels[i + 2][j + 1].blueChannelPixel()
          + sharpen[4][4] * oldPixels[i + 2][j + 2].blueChannelPixel();
    }
    return sum;
  }

  /**
   * Create an image with a "border" of pixels with values 0.
   *
   * @param oldPixels the pixels values of the image.
   * @param width     the width of the image.
   * @param height    the height of the image.
   * @return an image with additional pixels of value 0.
   */
  private IPixel[][] addZerosBorder(IPixel[][] oldPixels, int width, int height) {
    int newWidth = width + 4;
    int newHeight = height + 4;
    IPixel[][] newImage = new IPixel[newHeight][newWidth];
    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {
        if (i == 0 || i == 1 || i == newHeight - 1 || i == newHeight - 2
            || j == 0 || j == 1 || j == newWidth - 1 || j == newWidth - 2) {
          newImage[i][j] = new RGBPixel(0, 0, 0);
        } else {
          newImage[i][j] = oldPixels[i - 2][j - 2];
        }
      }
    }
    return newImage;
  }

  /**
   * Should a pixel value reach its max after a conversion, it is automatically set at the max bound
   * or min bound (255 or 0), not over it.
   *
   * @param pixelValue the value of a pixel.
   * @return the new integer value of the pixel.
   */
  protected int reachBound(int pixelValue) {
    if (pixelValue > 255) {
      return 255;
    } else if (pixelValue < 0) {
      return 0;
    }

    return pixelValue;
  }
}