/**
 * 
 */
package lcts.api;

/**
 * A 2d point that represents a monitor pixel. As such, values must be greater than 0 and the origin is in the top left corner as opposed to the bottom left.
 * <p>The maximum values will vary based on the size of the user's monitor.
 * @author SId
 *
 */
public class MonitorPixel extends Point2d {

	/**
	 * 
	 */
	public MonitorPixel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 */
	public MonitorPixel(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

}
