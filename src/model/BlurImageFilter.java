package model;

/**
 * Represents an operation to filter an image by blurring it. Implements ICommandFilterImage.
 */
public class BlurImageFilter implements ICommandTransformation {

  private double[][] gaussianBlur;

  /**
   * Constructor for BlurImageFilter.
   */
  public BlurImageFilter() {
    gaussianBlur = new double[][]
        {{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}};
  }

  /**
   * Produces a new filtered image based off the given one.
   *
   * @param image the image to be filtered.
   * @return a new IImage that is the filtered version of the given one.
   */
  @Override
  public IImage performTransformation(IImage image) {
    double[][] gaussianBlur = this.gaussianBlur;

    int width = image.getImageWidth();
    int height = image.getImageHeight();
    IPixel[][] pixels = new IPixel[height][width];
    IPixel[][] oldPixels = image.getPixels();

    // create an image with a "border" of zeroes
    IPixel[][] oldPixelsZero = addZerosBorder(oldPixels, width, height);

    // Recur image pixels
    for (int i = 0; i < height + 2; i++) {
      for (int j = 0; j < width + 2; j++) {

        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;

        // Skipping border ... to prevent going out of bounds
        if (i > 0 && i < height + 1 && j > 0 && j < width + 1) {
          // Convert RGB values
          redSum = newPixel(oldPixelsZero, "red", i, j);
          greenSum = newPixel(oldPixelsZero, "green", i, j);
          blueSum = newPixel(oldPixelsZero, "blue", i, j);

          // Create new pixel
          IPixel newPixel = new RGBPixel(Math.toIntExact(Math.round(redSum)),
              Math.toIntExact(Math.round(greenSum)), Math.toIntExact(Math.round(blueSum)));

          // Add to new pixel matrix
          pixels[i - 1][j - 1] = newPixel; // (0,0) : pixels (1,1) : zeroPixels
        }
      }
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
      sum = gaussianBlur[0][0] * oldPixels[i - 1][j - 1].redChannelPixel()
          + gaussianBlur[0][1] * oldPixels[i - 1][j].redChannelPixel()
          + gaussianBlur[0][2] * oldPixels[i - 1][j + 1].redChannelPixel()
          + gaussianBlur[1][0] * oldPixels[i][j - 1].redChannelPixel()
          + gaussianBlur[1][1] * oldPixels[i][j].redChannelPixel()
          + gaussianBlur[1][2] * oldPixels[i][j + 1].redChannelPixel()
          + gaussianBlur[2][0] * oldPixels[i + 1][j - 1].redChannelPixel()
          + gaussianBlur[2][1] * oldPixels[i + 1][j].redChannelPixel()
          + gaussianBlur[2][2] * oldPixels[i + 1][j + 1].redChannelPixel();
    } else if (channel.equals("green")) {
      sum = gaussianBlur[0][0] * oldPixels[i - 1][j - 1].greenChannelPixel()
          + gaussianBlur[0][1] * oldPixels[i - 1][j].greenChannelPixel()
          + gaussianBlur[0][2] * oldPixels[i - 1][j + 1].greenChannelPixel()
          + gaussianBlur[1][0] * oldPixels[i][j - 1].greenChannelPixel()
          + gaussianBlur[1][1] * oldPixels[i][j].greenChannelPixel()
          + gaussianBlur[1][2] * oldPixels[i][j + 1].greenChannelPixel()
          + gaussianBlur[2][0] * oldPixels[i + 1][j - 1].greenChannelPixel()
          + gaussianBlur[2][1] * oldPixels[i + 1][j].greenChannelPixel()
          + gaussianBlur[2][2] * oldPixels[i + 1][j + 1].greenChannelPixel();
    } else if (channel.equals("blue")) {
      sum = gaussianBlur[0][0] * oldPixels[i - 1][j - 1].blueChannelPixel()
          + gaussianBlur[0][1] * oldPixels[i - 1][j].blueChannelPixel()
          + gaussianBlur[0][2] * oldPixels[i - 1][j + 1].blueChannelPixel()
          + gaussianBlur[1][0] * oldPixels[i][j - 1].blueChannelPixel()
          + gaussianBlur[1][1] * oldPixels[i][j].blueChannelPixel()
          + gaussianBlur[1][2] * oldPixels[i][j + 1].blueChannelPixel()
          + gaussianBlur[2][0] * oldPixels[i + 1][j - 1].blueChannelPixel()
          + gaussianBlur[2][1] * oldPixels[i + 1][j].blueChannelPixel()
          + gaussianBlur[2][2] * oldPixels[i + 1][j + 1].blueChannelPixel();
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
    int newWidth = width + 2;
    int newHeight = height + 2;
    IPixel[][] newImage = new IPixel[newHeight][newWidth];
    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {
        if (i == 0 || i == height + 1 || j == 0 || j == width + 1) {
          newImage[i][j] = new RGBPixel(0, 0, 0);
        } else {
          newImage[i][j] = oldPixels[i - 1][j - 1];
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