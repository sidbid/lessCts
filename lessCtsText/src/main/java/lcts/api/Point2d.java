/**
 * 
 */
package lcts.api;

/**
 * Representation of a two dimensional point, that has an x coordinate and a y coordinate, which signify distance from the origin.
 * @author SId
 */
public class Point2d implements Notatable, Describable {
	
	private float x;
	private float y;
	/**
	 * Used to notate and unNotate the point. It goes in between the x value and the y value.
	 */
	public static final String VALUE_SEP = ", ";
	
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
	
	/**
	 * Non-static version of getDistTo, which makes point a the point that is calling the method.
	 * @param other the other point 
	 * @return the distance between the two points, as a float
	 */
	public float getDistTo (Point2d other) {
		return getDistTo(this, other);
	}
	
	/**
	 * Distance formula employed to get the distance between two points. The second point's x and y subtract the first point's, not that
	 * it really matters.
	 * @param a the first point
	 * @param b the second point
	 * @return the distance between the two points, as a float
	 */
	public static float getDistTo (Point2d a, Point2d b) {
		return (float) (Math.pow((Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2)), 0.5));
	}
	
	/**
	 * Keeps a in between max and min, essentially "clamping" it between the two values.
	 * @param a the value to check
	 * @param max the maximum value a can be
	 * @param min the minimum value a can be
	 * @return a after it has been clamped, as a float.
	 * @throws IllegalArgumentException if max < min or same thing, min > max
	 */
	public static float clamp (float a, float max, float min) {
		if (max < min) {
			throw new IllegalArgumentException("Max was less than min.");
		}
		
		if (a < min) {
			return min;
		} else if (a > max) {
			return max;
		} else {
			return a;
		}
	}

	@Override
	public String describe() {
		return "the point " + notate();
	}

	@Override
	public String notate() {
		return "(" + x + VALUE_SEP + y + ")";
	}

	@Override
	public void unNotate(String s) throws NumberFormatException {
		String noParentheses = s.substring(1);
		noParentheses = noParentheses.substring(0, noParentheses.length() - 2);
		String[] usable = noParentheses.split(VALUE_SEP);
		this.x = Float.parseFloat(usable[0]);
		this.y = Float.parseFloat(usable[1]);
	}

}
