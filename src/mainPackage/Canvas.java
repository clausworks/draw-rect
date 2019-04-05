// Canvas.java
// CIS 111 - Lab 7


package mainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * An extension of JPanel for drawing graphics.
 * @author Nicholas Brunet
 * @see CartesianCoordinate
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {
	
	public final static int CANVAS_WIDTH = 400;
	public final static int CANVAS_HEIGHT = 300;
	public final static int OFFSET_X = 50;
	public final static int OFFSET_Y = 50;
	public final static int GRAPH_WIDTH = CANVAS_WIDTH - (2 * OFFSET_X);
	public final static int GRAPH_HEIGHT = CANVAS_HEIGHT - (2 * OFFSET_Y);
	
	public BufferedImage image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
	public Graphics2D canvas = (Graphics2D)image.createGraphics();

		
	
	/**
	 * Draws a line from <code>start</code> to <code>end</code>.
	 * @param start the line's start point
	 * @param end the line's end point
	 * @see CartesianCoordinate
	 */
	
	public void drawLine(CartesianCoordinate start, CartesianCoordinate end) {
		canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
		
	}
	
	/**
	 * Draws a rectangle
	 * @param start top left coordinate of the box
	 * @param end bottom right coordinate of the box
	 * @see CartesianCoordinate
	 */
	public void drawRect(CartesianCoordinate start, CartesianCoordinate end) {
		int width = Math.abs(start.xDistanceFrom(end));
		int height = Math.abs(start.yDistanceFrom(end));
		
		Rectangle rect = new Rectangle(start.getX(), start.getY() - height, width, height);
		canvas.draw(rect);
		canvas.fill(rect);
		
//		repaint();
	}
	
	
	/**
	 * Draws the axes.
	 */
	public void drawAxes() {
		canvas.setColor(Color.BLUE);
		canvas.setStroke(new java.awt.BasicStroke(2));
		
		// x-axis
		drawLine(new CartesianCoordinate(-1,-2), new CartesianCoordinate(GRAPH_WIDTH, -2)); 
		
		// y-axis; for some reason, the stroke is painted a little off
		drawLine(new CartesianCoordinate(-1,-2), new CartesianCoordinate(-1, GRAPH_HEIGHT - 1));
		
//		repaint();
	}
	
	
	/**
	 * Draws tick marks on the axes.
	 * @param spacingX spacing between tick marks on <i>x</i> axis
	 * @param spacingY spacing between tick marks on <i>y</i> axis
	 */
	public void drawScale(int spacingX, int spacingY) {
		CartesianCoordinate textPosition = new CartesianCoordinate();
		
		canvas.setColor(Color.BLUE);
		canvas.setStroke(new java.awt.BasicStroke(1));
		canvas.setFont(new Font("Arial", Font.PLAIN, 10));
		int position;
		
		for (position = spacingX; position <= GRAPH_WIDTH; position += spacingX) {
			// tick
			drawLine(new CartesianCoordinate(position, -7), new CartesianCoordinate(position, -1));
			
			// text
			textPosition.setY(-18);
			if (position < 10) {
				textPosition.setX(position - 3);
			}
			else if (position < 100) {
				textPosition.setX(position - 6);
			}
			else {
				textPosition.setX(position - 9);
			}
			canvas.drawString(Integer.toString(position), textPosition.getX(), textPosition.getY());
		}
		
		// y axis
		for (position = spacingY; position <= GRAPH_HEIGHT; position += spacingY) { 
			// tick
			drawLine(new CartesianCoordinate(-7, position), new CartesianCoordinate(-1, position));

			// text
			textPosition.setY(position - 4);
			if (position < 10) {
				textPosition.setX(-17);
			}
			else if (position < 100) {
				textPosition.setX(-22);
			}
			else {
				textPosition.setX(-28);
			}
			canvas.drawString(Integer.toString(position), textPosition.getX(), textPosition.getY());
		}
				
		
	}
	
	
	/*public void drawThingy() {
		// parabola
		double x;
		double y;
		CartesianCoordinate curCoord = new CartesianCoordinate();
		
		canvas.setColor(Color.ORANGE);
		canvas.setStroke(new java.awt.BasicStroke(2));
		
		for (x = 0; x < GRAPH_WIDTH; x += 0.1) {
			y = -0.01 * Math.pow((double)x - (GRAPH_WIDTH / 2), 2.0) + GRAPH_HEIGHT;
			curCoord.set( (int)Math.round(x), (int)Math.round(y) );
			drawLine(curCoord, curCoord);
		}
		
	}*/
	
	/**
	 * Sets up the canvas with background and axes
	 */
	public void initializeCanvas() {
		
		// initialize JPanel for canvas
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		setBorder(javax.swing.BorderFactory.createLineBorder(new Color(150,150,150), 1));
		
		// turn antialiasing on for text only
		canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		canvas.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// draw white background
		canvas.setColor(Color.WHITE);
		CartesianCoordinate.initialize(0, 0, CANVAS_HEIGHT);
		drawRect(new CartesianCoordinate(0,0), new CartesianCoordinate(CANVAS_WIDTH, CANVAS_HEIGHT));

		// set up graph
		CartesianCoordinate.initialize(OFFSET_X, OFFSET_Y, CANVAS_HEIGHT);
		drawAxes();
		drawScale(25, 25);
		
	}
	
	/**
	 * Clears the canvas
	 */
	public void clearCanvas() {
		image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
		canvas = (Graphics2D)image.getGraphics();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawImage(image,  0,  0,  null);
		
		initializeCanvas();
				
	}
	
}
