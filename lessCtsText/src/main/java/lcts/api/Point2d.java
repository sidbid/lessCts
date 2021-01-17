/**
 * 
 */
package lcts.api;

/**
 * @author SId
 * A class used to describe a coordinate in 2d space, typically to represent a monitor pixel.
 */
public class Point2d implements Notatable, Describable {
	
	private float x;
	private float y;
	
	public Point2d () {
		x = 0f;
		y = 0f;
	}
	
	public Point2d (float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

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
	public void unNotate(String s) {
		// TODO Auto-generated method stub
	}

}
