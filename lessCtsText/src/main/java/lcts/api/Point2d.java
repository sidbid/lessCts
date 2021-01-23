/**
 * 
 */
package lcts.api;

/**
 * @author SId
 * A class used to describe a coordinate in 2d space, typically to represent a monitor pixel.
 */
public class Point2d implements Notatable, Describable {
	//TODO Change x and y to int so that this actually works with Robot
	
	private float x;
	private float y;
	
	/**
	 * Empty constructor that will be filled out with unNotate.
	 */
	public Point2d () {
		x = 0f;
		y = 0f;
	}
	
	/**
	 * Full constructor of a point.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Point2d (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for x
	 * @return x
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Setter for x
	 * @param x the new x value
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Getter for y
	 * @return y
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Setter for y
	 * @param y the new y value
	 */
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String describe() {
		return "the point " + notate();
	}

	@Override
	public String notate() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public void unNotate(String s) throws NumberFormatException {
		String noParentheses = s.substring(1);
		noParentheses = noParentheses.substring(0, noParentheses.length() - 2);
		String[] usable = noParentheses.split(", ");
		this.x = Float.parseFloat(usable[0]);
		this.y = Float.parseFloat(usable[1]);
	}

}
