/**
 * 
 */
package lcts.api;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A 2d point that represents a monitor pixel. As such, values must be greater than 0 and the origin is in the top left corner
 * as opposed to the bottom left. It perhaps does some unnecessary conversions from float to int, but that reinforces inheritance.
 * <p>The maximum values will vary based on the size of the user's monitor.
 * @author SId
 *
 */
public class MonitorPixel extends Point2d {
	
	/**
	 * The top left corner, or (0, 0).
	 */
	public static Dimension MIN_DIM = new Dimension(0, 0);
	/**
	 * The bottom right corner, determined by the size of the screen.
	 */
	public static Dimension MAX_DIM = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * The empty constructor that will be filled by unNotate.
	 */
	public MonitorPixel() {
		super(0, 0);
	}

	/**
	 * The full constructor, which takes integers. Although it sets x and y first(due to using a superclass constructor), 
	 * it performs data validation afterwards using the setters from Point2d.
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 */
	public MonitorPixel(int x, int y) {
		super(clamp(x, MAX_DIM.width, MIN_DIM.width), clamp(y, MAX_DIM.height, MIN_DIM.height));
	}
	
	/**
	 * Non-static implementation of this class's getDistTo, where the first pixel is the pixel that called the method.
	 * @param other the other monitor pixel
	 * @return the distance between the two pixels, as an integer
	 */
	public int getDistTo (MonitorPixel other) {
		return getDistTo(this, other);
	}
	
	/**
	 * Overloading Point2d's static getDistTo method to make it only deal with monitor pixels(so all values are positive integers).
	 * @param a the first pixel
	 * @param b the second pixel
	 * @return the distance between the two pixels, as an integer
	 */
	public static int getDistTo (MonitorPixel a, MonitorPixel b) {
		return (int) Point2d.getDistTo(a, b);
	}
	
	/**
	 * Point2d's clamp method, just for integers.
	 * @param a the value to clamp
	 * @param max the value a cannot be greater than
	 * @param min the value a cannot be less than
	 * @return the integer a, but clamped
	 */
	public static int clamp (int a, int max, int min) {
		return (int) Point2d.clamp(a, max, min);
	}
	
	/**
	 * Overridden to say "the monitor pixel" instead of "the point".
	 */
	@Override
	public String describe() {
		return "the monitor pixel " + notate();
	}
	
	/**
	 * Overridden to notate integers instead of floats.
	 */
	@Override
	public String notate() {
		return "(" + (int)(super.getX()) + Point2d.VALUE_SEP + (int)(super.getY()) + ")";
	}
	
	/**
	 * Overridden to unNotate integers, as otherwise a NumberFormatException would be thrown.
	 */
	@Override
	public void unNotate(String s) throws NumberFormatException {
		String noParentheses = s.substring(1);
		noParentheses = noParentheses.substring(0, noParentheses.length() - 2);
		String[] usable = noParentheses.split(VALUE_SEP);
		super.setX(Integer.parseInt(usable[0]));
		super.setY(Integer.parseInt(usable[1]));
	}
	
	

}
