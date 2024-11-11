package model;

import java.io.IOException;
import utils.ImageUtil;

/**
 * A main class for testing the methods of an image.
 */
public class MainImageImpl {

  /**
   * Tests if an image can be successfully down-scaled.
   * @param args the arguments for downscaling
   * @throws IOException an exception
   */
  public static void main(String[] args) throws IOException {

    ImageUtil util = new ImageUtil();
    IImage example = util.convertStringFileToPhoto("examplePNG.png");
    IImage mask = util.convertStringFileToPhoto("exampleMask.png");
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());
    example = example.maskImage(mask, new BlurImageFilter());

    IImage downScaled = example.downScale(150, 186);
    util.saveIImageAsFile(downScaled, "8-blur.png");
  }
}
