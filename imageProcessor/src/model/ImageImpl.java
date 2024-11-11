package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents an Image which consists of a 2-d Array of pixels (IPixel). Offers functionality for
 * brightening/darkening an Image and also converting an Image to grayscale. Implements the Image
 * interface.
 */
public class ImageImpl implements IImage {

  private final IPixel[][] image;
  private int width;
  private int height;

  /**
   * First constructor for ImageImpl.
   *
   * @param image 2D array of pixels that make up an image.
   */
  public ImageImpl(IPixel[][] image) {
    this.image = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  /**
   * Second constructor for ImageImpl. Creates an image given a width and height and one pixel. The
   * entire image will be made of the one pixel.
   *
   * @param width  the width of the image
   * @param height the height of the image
   * @param pixel  the pixel to be replicated across the image
   * @throws IllegalArgumentException if the given inputs are invalid.
   */
  public ImageImpl(int width, int height, IPixel pixel) throws IllegalArgumentException {
    if (width < 1 || height < 1 || pixel == null) {
      throw new IllegalArgumentException("The given inputs are invalid.");
    }
    IPixel[][] image = new IPixel[height][width];
    this.width = width;
    this.height = height;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[i][j] = pixel;
      }
    }
    this.image = image;
  }

  /**
   * Flips an image horizontally and returns a new flipped image.
   *
   * @return an Image flipped horizontally.
   */
  @Override
  public IImage flipImageHorizontal() {
    int width = this.getImageWidth();
    int height = this.getImageHeight();
    IPixel[][] pixels = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = this.image[i][width - 1 - j];
      }
    }

    return new ImageImpl(pixels);
  }

  /**
   * Flips an image vertically and returns a new flipped image.
   *
   * @return an Image flipped vertically.
   */
  @Override
  public IImage flipImageVertical() {
    int width = this.getImageWidth();
    int height = this.getImageHeight();
    IPixel[][] pixels = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = this.image[height - 1 - i][j];
      }
    }
    IImage newImage = new ImageImpl(pixels);
    return newImage;
  }

  /**
   * Given an increment value, adds this increment to each RGB value of each pixel in an image.
   *
   * @return an Image
   */
  @Override
  public IImage darkenBrightenImage(int increment) {
    int width = this.getImageWidth();
    int height = this.getImageHeight();
    IPixel[][] pixels = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = this.image[i][j].brightenDarkenPixel(increment);
      }
    }

    return new ImageImpl(pixels);
  }

  /**
   * Creates a grey-scale image specified by string input.
   *
   * @param command a command that specifies how exactly an image may be grey-scaled.
   * @return a new grey-scaled image.
   */
  @Override
  public IImage grayScaleImage(ACommandGrayScale command) {
    return command.performTransformation(this);
  }

  /**
   * Obtains the width (number of columns) of the image.
   *
   * @return the width of an image
   */
  @Override
  public int getImageWidth() {
    int width = 0;
    for (IPixel p : this.image[0]) {
      width++;
    }
    return width;
  }


  /**
   * Obtains the height (number of rows) of an image.
   *
   * @return the height of an image
   */
  @Override
  public int getImageHeight() {
    return this.image.length;
  }

  /**
   * Obtains the pixels of an image.
   *
   * @return the array of pixels
   */
  @Override
  public IPixel[][] getPixels() {
    return this.image.clone();
  }

  @Override
  public IImage filterImage(ICommandTransformation command) {
    return command.performTransformation(this);
  }

  @Override
  public IImage colorTransform(AColorTransform command) {
    return command.performTransformation(this);
  }

  @Override
  public IImage downScale(double width, double height) throws IllegalArgumentException {
    if (width > this.getImageWidth() || height > this.getImageHeight()) {
      throw new IllegalArgumentException("Dimensions are greater than original photo "
          + "or negative.");
    }

    // compute scale factors
    double widthFactor = width / (this.getImageWidth() * 1.00);
    double heightFactor = height / (this.getImageHeight() * 1.00);

    // Compute dimensions of down-scaled image
    int scaledWidth = Math.toIntExact(Math.round(this.getImageWidth() * widthFactor));
    int scaledHeight = Math.toIntExact(Math.round(this.getImageHeight() * heightFactor));
    double origWidth = (double) this.getImageWidth();
    double origHeight = (double) this.getImageHeight();

    // Grab pixels
    IPixel[][] origPixels = this.getPixels();
    IPixel[][] newPixels = new IPixel[scaledHeight][scaledWidth];

    // Compute new floating positions of pixels from original photo
    for (int i = 0; i < scaledHeight; i++) {
      for (int j = 0; j < scaledWidth; j++) {
        // Map the position in T to the position in S
        double xPosn = clampDoublePosn(((double) j / (double) scaledWidth) * origWidth,
            origWidth);
        double yPosn = clampDoublePosn(((double) i / (double) scaledHeight) * origHeight,
            origHeight);

        // Return int array of size three, for each RGB value
        IPixel newPixel = downScaleMappedValues(xPosn, yPosn, origPixels);

        // Set new X and Y Posn to the new Pixel.
        newPixels[i][j] = newPixel;
      }
    }

    // New Downscaled image
    return new ImageImpl(newPixels);
  }
  
  /**
   * This method returns a mosaiced image by the given number of seeds. The
   * change is bounded by the pixel's max value.
   *
   * @param seeds - the number of seeds in the mosaic.
   * @return - a new image with the edits made.
   */
  public ImageImpl createMosaic(int seeds) {
	IPixel[][] temp = this.getPixels();

    // assign seeds to random positions
    Position2D[] seedLocations = generateRandomSeeds(seeds);
    // Assign pixels posn to different clusters
    HashMap<Position2D, ArrayList<Position2D>> clusters = cluster(seedLocations);

    // Finds the average per seed/cluster
    for (HashMap.Entry<Position2D, ArrayList<Position2D>> entry : clusters.entrySet()) {
      ArrayList<Position2D> pixelPosns = entry.getValue();
      // Find average per component
      int[] average = findAveragePerSeed(pixelPosns);
      int rAverage = average[0];
      int gAverage = average[1];
      int bAverage = average[2];

      // Set each pixel component to the average values
      for (int i = 0; i < pixelPosns.size(); i++) {
        Position2D currentPosn = pixelPosns.get(i);
        int xPosn = currentPosn.getX();
        int yPosn = currentPosn.getY();
        temp[xPosn][yPosn] = new RGBPixel(rAverage, gAverage, bAverage);
      }
    }
    // Create new image with reassigned pixel values
    return new ImageImpl(temp);
  }

  /**
   * Generates a list of random seed positions.
   * @param seeds the number of seeds.
   * @return an array of seed positions.
   */
  private Position2D[] generateRandomSeeds(int seeds) {
    Position2D[] seedLocations = new Position2D[seeds];
    for (int i = 0; i < seeds; i++) {
      int xPosn = (int) (Math.random() * this.height);
      int yPosn = (int) (Math.random() * this.width);
      // Avoid duplicate seeds, keep re-assigning until there is a unique position
      while (isDuplicatePosn(xPosn, yPosn, seedLocations)) {
        xPosn = (int) (Math.random() * this.height);
        yPosn = (int) (Math.random() * this.width);
      }
      seedLocations[i] = new Position2D(xPosn, yPosn);
    }
    return seedLocations;
  }

  /**
   * Determines if a position is already in a list of positions. Returns true is position is in
   * list.
   *
   * @param xPosn the x-position
   * @param yPosn the y-position
   * @return a boolean if position is already in list
   */
  private Boolean isDuplicatePosn(int xPosn, int yPosn, Position2D[] posns) {
    // Search for duplicate position in list
    for (Position2D p : posns) {
      // If the index has yet to be assigned
      if (p == null) {
        return false;
      }
      // If positions are equivalent
      if (p.getX() == xPosn && p.getY() == yPosn) {
        return true;
      }
    }
    return false;
  }

  /**
   * Finds the average pixel value from a list of pixel positions.
   *
   * @param posns the list of pixel positions
   * @return the average pixel value from list
   */
  private int[] findAveragePerSeed(ArrayList<Position2D> posns) {
    int[] avgComponents = new int[3];
    int rtotal = 0;
    int gtotal = 0;
    int btotal = 0;
    double size = posns.size() * 1.0;
    IPixel[][] pixels = this.getPixels();

    // Find the average across red, green, blue components from list of pixels
    for (Position2D posn : posns) {
      rtotal += pixels[posn.getX()][posn.getY()].redChannelPixel();
      gtotal += pixels[posn.getX()][posn.getY()].greenChannelPixel();
      btotal += pixels[posn.getX()][posn.getY()].blueChannelPixel();
    }

    // Find the average per component and record in array
    int rAverage = Math.toIntExact((long) ((rtotal * 1.0) / size));
    avgComponents[0] = rAverage;
    int gAverage = Math.toIntExact((long) ((gtotal * 1.0) / size));
    avgComponents[1] = gAverage;
    int bAverage = Math.toIntExact((long) ((btotal * 1.0) / size));
    avgComponents[2] = bAverage;

    return avgComponents;
  }

  /**
   * Creates a cluster of pixels for each seed in the image.
   *
   * @param seedLocations the x and y position associated with each seed.
   * @return the position mapped to the associated list of pixels.
   */
  private HashMap<Position2D, ArrayList<Position2D>> cluster(Position2D[] seedLocations) {
    ArrayList<Position2D> pixelPosnsList = new ArrayList<>();
    HashMap<Position2D, ArrayList<Position2D>> seedToPixelPosn = new HashMap<>();

    // initialize hashmap with known seed positions and empty list of pixel posns
    for (Position2D posn : seedLocations) {
      seedToPixelPosn.put(posn, new ArrayList<>());
    }

    // Recur through entire image
    // for each pixel in the image, determine which seed this pixel pertains to
    for (int h = 0; h < height; h++) {  // iterate through height
      for (int w = 0; w < width; w++) { // iterate through width
        // Find the position this pixel is closest to
        Position2D seedLoc = findClosestSeed(seedLocations, h, w);
        // Reference this pixel's position in image
        Position2D pixelPosn = new Position2D(h, w);
        // Add pixel position to the seed's list of pixels
        pixelPosnsList = seedToPixelPosn.get(seedLoc);
        pixelPosnsList.add(pixelPosn);
        // Add seed posn with new list
        seedToPixelPosn.put(seedLoc, pixelPosnsList);
      }
    }
    return seedToPixelPosn;
  }

  /**
   * Position2D of the closest seed to the given pixel location.
   * @param seedLocations the x and y position associated with each seed.
   * @param x x position of the pixel location.
   * @param y y position of the pixel location.
   * @return Position2D associated with the closes seed to the given pixel location.
   */
  private Position2D findClosestSeed(Position2D[] seedLocations, int x, int y) {
    Position2D answer = null;
    int numSeeds = seedLocations.length;
    // calculate distances
    HashMap<Position2D, Double> distances = new HashMap<>();
    double currentMin = Math.sqrt(((this.width) * (this.width)) + ((this.height) * (this.height)));

    for (int i = 0; i < numSeeds; i++) {
      int seedX = seedLocations[i].getX();
      int seedY = seedLocations[i].getY();

      double distance = Math.sqrt(((x - seedX) * (x - seedX)) + ((y - seedY) * (y - seedY)));
      if (distance < currentMin) {
        currentMin = distance;
      }
      distances.put(seedLocations[i], distance);
    }
    for (HashMap.Entry<Position2D, Double> entry : distances.entrySet()) {
      Position2D key = entry.getKey();
      Double value = entry.getValue();
      if (value == currentMin) {
        answer = key;
      }
    }
    return answer;
  }


  /**
   * Clamps an estimated index to the maximum or minimum of an image's dimensions.
   *
   * @param value   the value to be clamped
   * @param maximum the maximum index of an image
   * @return the value, either clamped, or its original value
   */
  private double clampDoublePosn(double value, double maximum) {
    if (value > maximum) {
      return maximum - 1;
    } else if (value < 0) {
      return 0.00;
    } else {
      return value;
    }
  }

  /**
   * Returns the mapped pixel for a down-scaled image.
   *
   * @param newXPosn the x-position of the pixel in the original image.
   * @param newYPosn the y-position of the pixel in the original image.
   * @param pixels   the pixels of the original image.
   * @return the mapped pixel for a down-scaled image.
   */
  private IPixel downScaleMappedValues(double newXPosn, double newYPosn, IPixel[][] pixels) {

    int ceilingX = (int) Math.ceil(newXPosn);
    int floorX = (int) Math.floor(newXPosn);
    int ceilingY = (int) Math.ceil(newYPosn);
    int floorY = (int) Math.floor(newYPosn);

    // Compute four surrounding pixel posn's
    IPixel origPixel = pixels[floorY][floorX];
    IPixel pixelA = pixels[floorY][floorX];
    IPixel pixelB = pixels[floorY][ceilingX];
    IPixel pixelC = pixels[ceilingY][floorX];
    IPixel pixelD = pixels[ceilingY][ceilingX];

    // Compute m
    int mR = computeMDownScale(pixelB.redChannelPixel(), pixelA.redChannelPixel(), newXPosn);
    int mG = computeMDownScale(pixelB.greenChannelPixel(), pixelA.greenChannelPixel(), newXPosn);
    int mB = computeMDownScale(pixelB.blueChannelPixel(), pixelA.blueChannelPixel(), newXPosn);
    // Compute n
    int nR = computeNDownScale(pixelD.redChannelPixel(), pixelC.redChannelPixel(), newXPosn);
    int nG = computeNDownScale(pixelD.greenChannelPixel(), pixelC.greenChannelPixel(), newXPosn);
    int nB = computeNDownScale(pixelD.blueChannelPixel(), pixelC.blueChannelPixel(), newXPosn);

    // Compute value c
    int cR = floorCeilEqualsZero((int) (nR * (newYPosn - Math.floor(newYPosn)) +
        mR * (Math.ceil(newYPosn) - newYPosn)), origPixel.redChannelPixel());
    int cG = floorCeilEqualsZero((int) (nG * (newYPosn - Math.floor(newYPosn)) +
        mG * (Math.ceil(newYPosn) - newYPosn)), origPixel.greenChannelPixel());
    int cB = floorCeilEqualsZero((int) (nB * (newYPosn - Math.floor(newYPosn)) +
        mB * (Math.ceil(newYPosn) - newYPosn)), origPixel.blueChannelPixel());

    return new RGBPixel(cR, cG, cB); // return new pixel
  }

  /**
   * Given a component value and the current x-position of a pixel in S (original image), returns
   * the n value.
   *
   * @param component1 one of the RGB values of a pixel
   * @param component2 one of the RGB values of another pixel
   * @return the n-value of a component
   */
  private int computeNDownScale(int component1, int component2, double xPosn) {
    return (int) (component1 * (xPosn - Math.floor(xPosn)) +
        component2 * (Math.ceil(xPosn) - xPosn));
  }

  /**
   * Given a component value and the current x-position of a pixel in S (original image), returns
   * the m value.
   *
   * @param component1 one of the RGB values of a pixel
   * @param component2 one of the RGB values of another pixel
   * @return the m-value of a component
   */
  private int computeMDownScale(int component1, int component2, double xPosn) {
    return (int) (component1 * (xPosn - Math.floor(xPosn)) +
        component2 * (Math.ceil(xPosn) - xPosn));
  }

  /**
   * If the floor and ceiling happen to be equal and the result is zero, returns the value of the
   * original pixel.
   *
   * @param value the value
   * @return the floored value
   */
  private int floorCeilEqualsZero(int value, int originalValue) {
    if (value == 0) {
      return originalValue;
    } else {
      return value;
    }
  }

  @Override
  public IImage maskImage(IImage mask, ICommandTransformation transform)
      throws IllegalArgumentException {
    int maskHeight = mask.getImageHeight();
    int maskWidth = mask.getImageWidth();

    // Check if dimensions are the same
    if (this.getImageWidth() != maskWidth ||
        this.getImageHeight() != maskHeight) {
      throw new IllegalArgumentException("Mask does not have same dimensions as original image.");
    }

    // Create transformed image
    IImage transformedImage = transform.performTransformation(this);
    IPixel[][] origPixels = this.getPixels();
    IPixel[][] transPixels = transformedImage.getPixels();
    IPixel[][] maskPixels = mask.getPixels();
    IPixel[][] newPixels = new IPixel[maskHeight][maskWidth];

    // Recur through mask to determine when to replace base pixels with transformed pixels
    for (int i = 0; i < maskHeight; i++) {
      for (int j = 0; j < maskWidth; j++) {
        // Check if black pixel, then set to pixel in transformed image
        if (maskPixels[i][j].intensityPixel() == 0) {
          newPixels[i][j] = transPixels[i][j];
        }
        // Otherwise, do not change the pixel from original
        else {
          newPixels[i][j] = origPixels[i][j];
        }
      }
    }

    // return resulting photo
    return new ImageImpl(newPixels);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    // if o is not a thermostat
    if (!(o instanceof IImage)) {
      return false;
    }

    IImage other = (IImage) o;

    if (this.getPixels().length != other.getPixels().length) {
      return false;
    }

    for (int i = 0; i < this.getPixels().length; i++) {
      for (int j = 0; j < this.getImageWidth(); j++) {
        if (Math.abs(this.getPixels()[i][j].valuePixel()
            - other.getPixels()[i][j].valuePixel()) > 1) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getPixels());
  }
}
