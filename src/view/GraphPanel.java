package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import javax.swing.JPanel;
import java.util.Arrays; 

/**
 * Represents a panel for representing the RGB frequency line chart of an image.
 * The line chart displays the frequency for four components of an image: the red, green, blue,
 * and intensity values. The set size of the histogram is 500 by 600 pixels.
 */
public class GraphPanel extends JPanel {
  private final HashMap<Integer, Integer> redGraph;
  private final HashMap<Integer, Integer> greenGraph;
  private final HashMap<Integer, Integer> blueGraph;
  private final HashMap<Integer, Integer> valueGraph;
  private final int width = 600;
  private final int height = 600;

  /**
   * Constructor for a GraphPanel. Takes in four maps for the red, green, blue, and intensity
   * components of a photo.
   * @param redGraph map of the frequency for the red component
   * @param greenGraph map of the frequency for the green component
   * @param blueGraph map of the frequency for the blue component
   * @param valueGraph map of the frequency for the value of a pixel
   */
  public GraphPanel(HashMap<Integer, Integer> redGraph, HashMap<Integer, Integer> greenGraph,
      HashMap<Integer, Integer> blueGraph, HashMap<Integer, Integer> valueGraph) {
    if (redGraph == null || greenGraph == null || blueGraph == null || valueGraph == null) {
      throw new IllegalArgumentException("The graph or color is null!");
    }

    this.redGraph = redGraph;
    this.greenGraph = greenGraph;
    this.blueGraph = blueGraph;
    this.valueGraph = valueGraph;
    setupPanel();
  }

  // Set the background color of the line chart to white
  private void setupPanel() {
    this.setBackground(Color.white);
  }

  /**
   * Creates the graphics for the histogram to display on a GUI. The histogram contains
   * the frequency of all four components of a photo: the red, green, blue, and intensity values.
   * @param currentGraphics the current graphics.
   */
  public void paintComponent(Graphics currentGraphics) {
    int height = 256;
    int width = 256;
    super.paintComponent(currentGraphics);
    Graphics2D g2 = (Graphics2D) currentGraphics;
    int size = 256;  // The size of an array of frequency values
    int yStart = (this.width/2) + (width/2);
    int yPad = 1;
    int xPad = 2;
    int xStart = (this.width/2) - (width/2);
    // 128
    // 400 
    // 400 - 128 = 272, start line
    // 400 + 128 = 528, end line
    // X-axis: (272, 528) to (528, 528)
    // Y-axis: (272, 528) to (272, 272)


    // Arrays for each component
    Point[] pointsRed = new Point[size];
    Point[] pointsGreen = new Point[size];
    Point[] pointsBlue = new Point[size];
    Point[] pointsIntensty = new Point[size];

    // Recur through each map to generate the array, scaled.
    int maxRed = -99999;
    int maxGreen = -99999;
    int maxBlue = -99999;
    int maxIntensity = -99999;
    // To find largest element for scaling afterwards
    for (int i = 0; i < size; i++) {
      int xPosn = i;
      int yPosnRed = this.redGraph.get(i);
      int yPosnGreen = this.greenGraph.get(i);
      int yPosnBlue = this.blueGraph.get(i);
      int yPosnIntensty = this.valueGraph.get(i);
      if (yPosnRed >= maxRed) {
    	  maxRed = yPosnRed;
      }
      if (yPosnGreen >= maxGreen) {
    	  maxGreen = yPosnGreen;
      }
      if (yPosnBlue >= maxBlue) {
    	  maxBlue = yPosnBlue;
      }
      if (yPosnIntensty >= maxIntensity) {
    	  maxIntensity = yPosnIntensty;
      }
    }
//    System.out.println("maxRed: " + maxRed);
//  System.out.println("maxBlue: " + maxBlue);
//  System.out.println("maxGreen: " + maxGreen);
//  System.out.println("maxIntensity: " + maxIntensity);
  
    int arr[] = {maxRed, maxBlue, maxGreen, maxIntensity}; 
    int greatest = Arrays.stream(arr).max().getAsInt();
    if (greatest == 0) {
    	greatest = width;
    }
//    System.out.println("greatest: " + greatest);
    double scaleY = ((double) width / (double) greatest);
//    System.out.println("scaleY: " + scaleY);
    double scaleX = 1;
    
    // for creating posn for each element
    for (int i = 0; i < size; i++) {
        int xPosn = i;
        int yPosnRed = this.redGraph.get(i);
        int yPosnGreen = this.greenGraph.get(i);
        int yPosnBlue = this.blueGraph.get(i);
        int yPosnIntensty = this.valueGraph.get(i);
      pointsRed[i] = new Point(Math.toIntExact(xPad + xStart + Math.round((xPosn * scaleX))),
          (Math.toIntExact(yStart - Math.round((yPosnRed * scaleY)) - yPad)));
      pointsGreen[i] = new Point(Math.toIntExact(xPad + xStart + Math.round((xPosn * scaleX))),
          (Math.toIntExact(yStart - Math.round((yPosnGreen * scaleY)) - yPad)));
      pointsBlue[i] = new Point(Math.toIntExact(xPad + xStart + Math.round((xPosn * scaleX))),
          (Math.toIntExact(yStart - Math.round((yPosnBlue * scaleY)) - yPad)));
      pointsIntensty[i] = new Point(Math.toIntExact(xPad + xStart + Math.round((xPosn * scaleX))),
          (Math.toIntExact(yStart - Math.round((yPosnIntensty * scaleY)) - yPad)));
    }
    
    // TODO: 
//    g2.drawString("(0, 0)", 0, 0);
//    g2.drawString("(272, 272)", 272, 272);
//    g2.drawString("(528, 528)", 528, 528);

    // Draw x- and y-axes
    g2.drawLine((this.width/2) - (width/2), (this.width/2) + (width/2), (this.width/2) + (width/2), (this.width/2) + (width/2));
    g2.setStroke(new BasicStroke(1.5f));
    g2.drawLine((this.width/2) - (width/2), (this.width/2) + (width/2), (this.width/2) - (width/2), (this.width/2) - (width/2));
    
    // Draw ticks (5 for now)
    int numTicks = 5;
    int freqStart = 0;
    int graphStart = yStart;
    int tickLength = 5;
    int tickSpacing = Math.toIntExact(Math.round((double) height / (double) numTicks));
    Font font = new Font("Arial", Font.PLAIN, 12);
    for (int i = 0; i < numTicks + 1; i++) {
    	// start at beg. of x-axis
    	// subtract by tickSpacing each time
    	int fromX = xStart;
        int fromY = yStart -  Math.toIntExact(Math.round((double) tickSpacing * (double) i));
        int toX = fromX - tickLength;
        int toY = fromY;
        int freq = Math.toIntExact(Math.round(((double) i / (double) numTicks) * (double) greatest));
        int toXNumerical = Math.toIntExact(Math.round((double) tickSpacing * (double) i));
        g2.setFont(font);
        g2.drawString(Integer.toString(freq), 
        		fromX - tickLength - 5 - g2.getFontMetrics().stringWidth(Integer.toString(freq)), toY);
        g2.drawString(Integer.toString(toXNumerical), 
        		xStart + toXNumerical - (g2.getFontMetrics().stringWidth(Integer.toString(freq))/2), 
        		yStart + tickLength + 15);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1f));
        g2.drawLine(fromX, fromY, toX, toY);
        g2.drawLine(xStart + Math.toIntExact(Math.round((double) tickSpacing * (double) i)), 
        		yStart, 
        		xStart + Math.toIntExact(Math.round((double) tickSpacing * (double) i)), 
        		yStart + tickLength);
    }
    
    // labels
    g2.drawString("Pixel Value", (this.width/2) - (g2.getFontMetrics().stringWidth("Pixel Value")/2), yStart + 55);
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.rotate(-Math.toRadians(90), 0, 0);
    Font rotatedFont = font.deriveFont(affineTransform);
    g2.setFont(rotatedFont);
    g2.drawString("Frequency", xStart - g2.getFontMetrics().stringWidth(Integer.toString(greatest)) - 55, 
    		(this.width/2) - (g2.getFontMetrics().stringWidth("Frequency")/2));
    g2.setFont(font);
    
    // TODO: Legend
    // Make small squares and put text label next to em
    // Red
    int rectPad = 55;
    int rectSpacing = 20;
    int rectWidth = 20;
    int rectHeight = 12;
    g2.setColor(Color.MAGENTA); // top = y, left = x
    g2.fillRect(xStart, (this.width/2) - (width/2) - rectPad, rectWidth, rectHeight);
    g2.setColor(Color.black);
    g2.drawString("Intensity", xStart + rectWidth + 10, (this.width/2) - (width/2) - rectPad + 11);
    
    g2.setColor(Color.BLUE);
    g2.fillRect(xStart, (this.width/2) - (width/2) - rectPad - rectSpacing, rectWidth, rectHeight);
    g2.setColor(Color.black);
    g2.drawString("Blue", xStart + rectWidth + 10, (this.width/2) - (width/2) - rectPad - rectSpacing + 11);
    
    g2.setColor(Color.GREEN);
    g2.fillRect(xStart, (this.width/2) - (width/2) - rectPad - (rectSpacing * 2), rectWidth, rectHeight);
    g2.setColor(Color.black);
    g2.drawString("Green", xStart + rectWidth + 10, (this.width/2) - (width/2) - rectPad - (rectSpacing * 2) + 11);
    
    g2.setColor(Color.RED);
    g2.fillRect(xStart, (this.width/2) - (width/2) - rectPad - (rectSpacing * 3), rectWidth, rectHeight);
    g2.setColor(Color.black);
    g2.drawString("Red", xStart + rectWidth + 10, (this.width/2) - (width/2) - rectPad - (rectSpacing * 3) + 11);
    

    // Chart for red component
    g2.setColor(Color.RED);
    for (int i = 0; i < pointsRed.length - 1; i++) {
      int fromX = pointsRed[i].x;
      int fromY = reachMax(pointsRed[i].y);
      int toX = pointsRed[i + 1].x;
      int toY = reachMax(pointsRed[i + 1].y);
      g2.setStroke(new BasicStroke(1.5f));
      g2.drawLine(fromX, fromY, toX, toY);
    }

    // Chart for red component
    g2.setColor(Color.GREEN);
    for (int i = 0; i < pointsGreen.length - 1; i++) {
      int fromX = pointsGreen[i].x;
      int fromY = reachMax(pointsGreen[i].y);
      int toX = pointsGreen[i + 1].x;
      int toY = reachMax(pointsGreen[i + 1].y);
      g2.setStroke(new BasicStroke(1.5f));
      g2.drawLine(fromX, fromY, toX, toY);
    }

    // Chart for red component
    g2.setColor(Color.BLUE);
    for (int i = 0; i < pointsBlue.length - 1; i++) {
      int fromX = pointsBlue[i].x;
      int fromY = reachMax(pointsBlue[i].y);
      int toX = pointsBlue[i + 1].x;
      int toY = reachMax(pointsBlue[i + 1].y);
      g2.setStroke(new BasicStroke(1.5f));
      g2.drawLine(fromX, fromY, toX, toY);
    }

    // Chart for value of pixel
    g2.setColor(Color.MAGENTA);
    for (int i = 0; i < pointsIntensty.length - 1; i++) {
      int fromX = pointsIntensty[i].x;
      int fromY = reachMax(pointsIntensty[i].y);
      int toX = pointsIntensty[i + 1].x;
      int toY = reachMax(pointsIntensty[i + 1].y);
      g2.setStroke(new BasicStroke(1.5f));
      g2.drawLine(fromX, fromY, toX, toY);
    }
  }

  // Set the dimensions of the panel
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  /**
   * Should a frequency value go over the bounds of the histogram axes,
   * clamp it to the maximum value of the y-axis of the histogram.
   * @param num the frequency
   * @return the original frequency value or the maximum.
   */
  private int reachMax(int num) {
    if (num < 100) {
      return 100;
    }
    else {
      return num;
    }
  }
}