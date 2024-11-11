package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Represents a test class for an RGBPixel for its methods which implements IPixel.
 */
public class RGBPixelTest {
  IPixel rgbExample1;
  IPixel rgbBlack;
  IPixel rgbWhite;

  @Before
  public void setup() {
    rgbExample1 = new RGBPixel(30,40,50);
    rgbBlack = new RGBPixel(0,0,0);
    rgbWhite = new RGBPixel(255,255,255);
  }

  /**
   * Tests if an IllegalArgumentException is thrown if a pixel is made with parameters
   * below 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInValidPixelNegative() {
    IPixel invalidPixel = new RGBPixel(-10, -50, -60);
  }

  /**
   * Tests if an IllegalArgumentException is thrown if a pixel is made with parameters
   * above 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInValidPixelUpperBound() {
    IPixel invalidPixel = new RGBPixel(256, 50, 50);
  }

  /**
   * Test red value above 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void redTooBig() {
    new RGBPixel(300, 60, 70);
  }

  /**
   * Test green value above 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void greenTooBig() {
    new RGBPixel(55, 600, 90);
  }

  /**
   * Test blue value above 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void blueTooBig() {
    new RGBPixel(92, 33, 256);
  }

  /**
   * Tests if the correct maximum value is returned for a pixel.
   */
  @Test
  public void testValue() {
    assertEquals(50, rgbExample1.valuePixel());
    assertEquals(0, rgbBlack.valuePixel());
    assertEquals(255, rgbWhite.valuePixel());
  }

  /**
   * Tests if an increment is correctly added to each RGB value of a pixel.
   */
  @Test
  public void testBrightenDarkenPixel() {
    // Brightening
    assertEquals(50, rgbExample1.valuePixel());
    IPixel newPixel = rgbExample1.brightenDarkenPixel(1);
    assertEquals(51, newPixel.valuePixel());

    // Darkening
    assertEquals(255, rgbWhite.valuePixel());
    IPixel newPixel2 = rgbWhite.brightenDarkenPixel(-1);
    assertEquals(254, newPixel2.valuePixel());
  }

  /**
   * Tests if a pixel is at a max/min. value, the other pixels will continue to increment but
   * the pixel at the bound will not.
   */
  @Test
  public void testBrightenDarkenPixelMaxMin() {
    // Maximum
    // Add 210. Blue will reach maximum (50 + 210) but red and green will not.
    assertEquals(30, rgbExample1.redChannelPixel());
    assertEquals(40, rgbExample1.greenChannelPixel());
    assertEquals(50, rgbExample1.blueChannelPixel());
    IPixel newPixel = rgbExample1.brightenDarkenPixel(210);
    assertEquals(240, newPixel.redChannelPixel());
    assertEquals(250, newPixel.greenChannelPixel());
    assertEquals(255, newPixel.blueChannelPixel()); // stays at max. 255. Not 260.
    // Maxing out all components.
    IPixel newPixel2 = newPixel.brightenDarkenPixel(50);
    assertEquals(255, newPixel2.redChannelPixel());
    assertEquals(255, newPixel2.greenChannelPixel());
    assertEquals(255, newPixel2.blueChannelPixel());

    // Minimum
    // Decrease by 35.
    IPixel example2 = rgbExample1.brightenDarkenPixel(-35);
    assertEquals(0, example2.redChannelPixel()); // stays at 0, Not -5.
    assertEquals(5, example2.greenChannelPixel());
    assertEquals(15, example2.blueChannelPixel());
    // Minimizing out all components.
    IPixel example3 = example2.brightenDarkenPixel(-50);
    assertEquals(0, example3.redChannelPixel());
    assertEquals(0, example3.greenChannelPixel());
    assertEquals(0, example3.blueChannelPixel());
  }

  /**
   * Tests if the correct intensity is calculated for a pixel.
   */
  @Test
  public void testIntensity() {
    assertEquals(40, rgbExample1.intensityPixel());
    assertEquals(0, rgbBlack.intensityPixel());
    assertEquals(255, rgbWhite.intensityPixel());
  }

  /**
   * Tests if the correct Luma is calculated for a pixel.
   */
  @Test
  public void testLuma() {
    assertEquals(39, rgbExample1.lumaPixel());
    assertEquals(0, rgbBlack.lumaPixel());
    assertEquals(255, rgbWhite.lumaPixel());
  }

  /**
   * Tests if the correct value for a red pixel is returned.
   */
  @Test
  public void testRed() {
    assertEquals(30, rgbExample1.redChannelPixel());
    assertEquals(0, rgbBlack.redChannelPixel());
    assertEquals(255, rgbWhite.redChannelPixel());
  }

  /**
   * Tests if the correct value for a green pixel is returned.
   */
  @Test
  public void testGreen() {
    assertEquals(40, rgbExample1.greenChannelPixel());
    assertEquals(0, rgbBlack.greenChannelPixel());
    assertEquals(255, rgbWhite.greenChannelPixel());
  }

  /**
   * Tests if the correct value for a blue pixel is returned.
   */
  @Test
  public void testBlue() {
    assertEquals(50, rgbExample1.blueChannelPixel());
    assertEquals(0, rgbBlack.blueChannelPixel());
    assertEquals(255, rgbWhite.blueChannelPixel());
  }
}