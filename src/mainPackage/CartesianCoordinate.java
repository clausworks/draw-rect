// CartesianCoordinate.java
// CIS 111 - Lab 7


package mainPackage;


/**
 * A Cartesian coordinate system with the origin at the bottom-left.
 * <p>
 * <b>Run <code>CartesianCoordiante.initialize(int, int, int)</code> before use.</b>
 * </p>
 * @author Nicholas Brunet
 *
 */
public class CartesianCoordinate {
	// these store the real coordinates, relative to the top-left of the canvas
	private int x;
	private int y;

	private static int height; // height of the canvas
	private static int offsetX; // offset of origin relative to left
	private static int offsetY; // offset of origin relative to bottom
	
	
	
	/**
	 * Class constructor creating point at (0,0) relative to the origin.
	 * This uses a Cartesian coordinate system with the origin on the bottom-left.
	 * 
	 */
	public CartesianCoordinate() {
		this.setX(0);
		this.setY(0);
	}
	
	
	
	/**
	 * Class constructor creating a point at (<code>x</code>,<code>y</code>) relative to the origin.
	 * This uses a Cartesian coordinate system with the origin on the bottom-left.
	 * 
	 * @param x the <i>x</i> coordinate for the point.
	 * @param y the <i>y</i> coordinate for the point.
	 */
	public CartesianCoordinate(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	
	
	/**
	 * Static function initializing the origin at (<code>originX</code>, <code>originY</code>) relative to the canvas's top left corner.
	 * 
	 * @param originX the <i>x</i> coordinate for the origin.
	 * @param originY the <i>y</i> coordinate for the origin.
	 * @param canvasHeight the height of the canvas.
	 */
	public static void initialize(int originX, int originY, int canvasHeight) {
		offsetX = originX;
		offsetY = originY;
		height = canvasHeight;
	}
	
	
	// get x value relative to top-left of canvas
	private static int getOffsetXValue(int x) {
		return (offsetX + x);
	}
	
	// get y value relative to top-left of canvas; the canvas uses the top for y=0, but we want the bottom to be y=0.
	private static int getOffsetYValue(int y) {
		return (height - (offsetY + y));
	}
	
	
	/**
	 * Returns the distance to coordinate <code>c</code>.
	 * </p>
	 * Returns a negative value if coordinate is to the left of <code>c</code>.
	 * Returns a positive value if coordinate is to the right of <code>c</code>.
	 * @param c coordinate to find distance from.
	 * @return distance to the argument
	 */
	public int distanceFrom(CartesianCoordinate c) {
		// d^2 = (x1-x2)^2 + (y1-y2)^2
		double d = Math.sqrt( Math.pow( this.getX() - c.getX(), 2.0 ) + Math.pow( this.getY() - c.getY(), 2.0 ) );
		return (int)Math.round(d);
	}
	
	/**
	 * Returns the horizontal distance to point <code>c</code>.
	 * @param c coordinate to find distance from.
	 * @return horizontal distance to the argument
	 */
	public int xDistanceFrom(CartesianCoordinate c) {
		return this.getX() - c.getX();
	}
	
	/**
	 * Returns the vertical distance to point <code>c</code>.
	 * @param c coordinate to find distance from.
	 * @return vertical distance to the argument
	 */
	public int yDistanceFrom(CartesianCoordinate c) {
		return this.getY() - c.getY();
	}
	

	
	
	// getter and setter methods
	// this.x and this.y store the offset values. The user never sees these.
	
	/**
	 * Sets the <i>x</i> and <i>y</i> coordinates.
	 * @param x the <i>x</i> coordinate for the point.
	 * @param y the <i>y</i> coordinate for the point.
	 */
	public void set(int x, int y) {
		this.x = getOffsetXValue(x);
		this.y = getOffsetYValue(y);
	}
	
	public void setX(int x) {
		this.x = getOffsetXValue(x);
	}
	
	public int getX() {
		return this.x;
	}
	
	
	public void setY(int y) {
		this.y = getOffsetYValue(y);
	}
	
	public int getY() {
		return this.y;
	}
}
