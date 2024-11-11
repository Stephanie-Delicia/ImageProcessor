package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;

import utils.ImageUtil;

/**
 * Tests the ImageImpl class and the functionality it offers
 * which implements the IImage interface.
 */
public class ImageImplTest {

  IPixel pixel;
  IPixel pixel2;
  IImage exampleImage;
  IImage exampleImage2;
  IImage exampleImage3;
  IImage koalaExample;
  IImage koala_horizontal;
  IImage koala_vertical;
  IImage koala_horizontal_vertical;
  IImage koala_vertical_horizontal;
  IImage koala_brighter;
  IImage koala_intensity;
  IImage koala_luma;
  IImage koala_value;
  IImage koala_red;
  IImage koala_green;
  IImage koala_blue;
  ImageUtil util = new ImageUtil();

  /**
   * Creates examples of images to be tested against. All koala photos are provided by homework
   * starter code, converted to PPM and then an Image.
   *
   * @throws FileNotFoundException if a file cannot be found.
   */
  @Before
  public void init() throws FileNotFoundException {
    pixel = new RGBPixel(50, 60, 70);
    pixel2 = new RGBPixel(50, 50, 50);
    exampleImage = new ImageImpl(100, 100, pixel);
    exampleImage2 = new ImageImpl(250, 150, pixel);
    exampleImage3 = new ImageImpl(100, 100, pixel2);

    koalaExample = util.ppmToImage("Koala.ppm");
    koala_horizontal = util.ppmToImage("horizontalPPM.ppm");
    koala_vertical = util.ppmToImage("koala-verticalPPM.ppm");
    koala_horizontal_vertical = util.ppmToImage("koala-horizontal-verticalPPM.ppm");
    koala_vertical_horizontal = util.ppmToImage("koala-vertical-horizontalPPM.ppm");
    koala_brighter = util.ppmToImage("koala-brighter-by-50PPM.ppm");
    koala_intensity = util.ppmToImage("koala-intensity-greyscalePPM.ppm");
    koala_luma = util.ppmToImage("koala-luma-greyscalePPM.ppm");
    koala_value = util.ppmToImage("koala-value-greyscalePPM.ppm");
    koala_red = util.ppmToImage("koala-red-greyscalePPM.ppm");
    koala_green = util.ppmToImage("koala-green-greyscalePPM.ppm");
    koala_blue = util.ppmToImage("koala-blue-greyscalePPM.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void height0() {
    new ImageImpl(100, 0, pixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void heightNegative() {
    new ImageImpl(100, -100, pixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void width0() {
    new ImageImpl(0, 90, pixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void widthNegative() {
    new ImageImpl(-200, 50, pixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullPixel() {
    new ImageImpl(100, 100, null);
  }

  /**
   * Tests if the correct height of the image is obtained.
   */
  @Test
  public void testHeight() {
    assertEquals(100, exampleImage.getImageHeight());
    assertEquals(150, exampleImage2.getImageHeight());
    assertEquals(768, koalaExample.getImageHeight());
    assertEquals(768, koala_horizontal.getImageHeight());
  }

  /**
   * Tests if the correct width of the image is obtained.
   */
  @Test
  public void testWidth() {
    assertEquals(100, exampleImage.getImageWidth());
    assertEquals(250, exampleImage2.getImageWidth());
    assertEquals(1024, koalaExample.getImageWidth());
    assertEquals(1024, koala_horizontal.getImageWidth());
  }

  @Test
  public void testFlipImageHorizontal() {
    assertTrue(new ImageImpl(100, 100, pixel)
        .equals(exampleImage.flipImageHorizontal()));
    assertTrue(koala_horizontal.equals(koalaExample.flipImageHorizontal()));
  }

  @Test
  public void testFlipImageVertical() {
    assertTrue(new ImageImpl(100, 100, pixel)
        .equals(exampleImage.flipImageVertical()));
    assertTrue(koala_vertical.equals(koalaExample.flipImageVertical()));
  }

  @Test
  public void testFlipImageHorizontalVertical() {
    assertTrue(new ImageImpl(100, 100, pixel).equals(
        exampleImage.flipImageHorizontal().flipImageVertical()));
    assertTrue(koala_horizontal_vertical.equals(koalaExample
        .flipImageHorizontal().flipImageVertical()));
  }

  @Test
  public void testFlipImageVerticalHorizontal() {
    assertTrue(new ImageImpl(100, 100, pixel).equals(
        exampleImage.flipImageVertical().flipImageHorizontal()));
    assertEquals(koala_horizontal_vertical,
        koalaExample.flipImageVertical().flipImageHorizontal());
  }


  @Test
  public void testBrightenImage() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(60, 70, 80))
        .equals(exampleImage.darkenBrightenImage(10)));
    assertEquals(koala_brighter, koalaExample.darkenBrightenImage(50));
  }

  @Test
  public void testDarkenImage() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(15, 25, 35))
        .equals(exampleImage.darkenBrightenImage(-35)));
  }

  @Test
  public void testGrayScaleImageIntensity() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(60, 60, 60))
        .equals(exampleImage.grayScaleImage(new IntensityGreyScale())));
    assertEquals(koala_intensity, koalaExample.grayScaleImage(new IntensityGreyScale()));
  }

  @Test
  public void testGrayScaleImageLuma() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(59, 59, 59))
        .equals(exampleImage.grayScaleImage(new LumaGrayScale())));
    assertTrue(koala_luma.equals(koalaExample.grayScaleImage(new ValueGreyScale())));
  }

  @Test
  public void testGrayScaleImageValue() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(70, 70, 70))
        .equals(exampleImage.grayScaleImage(new ValueGreyScale())));
    assertEquals(koala_value, koalaExample.grayScaleImage(new ValueGreyScale()));
  }

  @Test
  public void testGrayScaleImageRed() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(50, 50, 50))
        .equals(exampleImage.grayScaleImage(new RedGrayScale())));
    assertEquals(koala_red, koalaExample.grayScaleImage(new RedGrayScale()));
  }

  @Test
  public void testGrayScaleImageGreen() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(60, 60, 60))
        .equals(exampleImage.grayScaleImage(new GreenGrayScale())));
    assertEquals(koala_green, koalaExample.grayScaleImage(new GreenGrayScale()));
  }

  @Test
  public void testGrayScaleImageBlue() {
    assertTrue(new ImageImpl(100, 100, new RGBPixel(70, 70, 70))
        .equals(exampleImage.grayScaleImage(new BlueGrayScale())));
    assertEquals(koala_blue, koalaExample.grayScaleImage(new BlueGrayScale()));
  }

  @Test
  public void testMaskImage() {
    ImageUtil util = new ImageUtil();
    IImage example = util.convertStringFileToPhoto("koala-horizontal.png");
    IImage mask = util.convertStringFileToPhoto("koalaMask.png");
    IImage transformed = util.convertStringFileToPhoto("koala-masked.ppm");
    assertTrue(transformed.equals(example.maskImage(mask, new GreyScaleColorTransform())));
  }

  @Test
  public void testDownscaleImage() {
    assertTrue(new ImageImpl(10, 20, new RGBPixel(50, 50, 50))
        .equals(exampleImage2.downScale(10,20)));
    assertTrue(new ImageImpl(5, 5, new RGBPixel(50, 60, 70))
        .equals(exampleImage2.downScale(5,5)));
    assertTrue(new ImageImpl(5, 3, new RGBPixel(50, 60, 70))
        .equals(exampleImage2.downScale(5,3)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeWidth() {
    exampleImage3.downScale(-2,50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeHeight() {
    exampleImage3.downScale(65,-50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void LargeWidth() {
    exampleImage3.downScale(120,50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void LargeHieght() {
    exampleImage3.downScale(15,250);
  }
}