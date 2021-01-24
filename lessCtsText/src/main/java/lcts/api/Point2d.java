/**
 * 
 */
package lcts.api;

/**
 * Representation of a two dimensional point, that has an x coordinate and a y coordinate, which signify distance from the origin.
 * <p>This class also has the use of representing a two dimensional vector, seen in a couple of its methods.
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
	 * Represents the distance between two points as a vector quantity, as in the difference between their x's and y's.
	 * @param a the first point
	 * @param b the second point
	 * @param asPoint used to overload between this and the other getDistTo that returns a float
	 * @return the difference between the two points, as a point
	 */
	public static Point2d getDistTo(Point2d a, Point2d b, boolean asPoint) {
		Point2d rv = new Point2d();
		rv.setX(b.getX() - a.getX());
		rv.setY(b.getY() - a.getY());
		return rv;
	}
	
	/**
	 * Takes the point vector, and divides its x and y by a value to get a part of the difference(multiplying 
	 * the vector by a scalar quantity less than one).
	 * @param a the first point
	 * @param b the second point
	 * @param numParts the value to divide the x's and y's by
	 * @return the scaled vector quantity
	 */
	public static Point2d getPartialDist (Point2d a, Point2d b, float numParts) {
		Point2d rv = getDistTo(a, b, true);
		rv.setX(rv.getX() / numParts);
		rv.setY(rv.getY() / numParts);
		return rv;
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
