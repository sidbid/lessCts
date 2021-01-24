/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

import lcts.api.MonitorPixel;

/**
 * The action variant corresponding to the robot dragging the mouse pointer, with either the left or right button held.
 * @author SId
 *
 */
public class MouseDrag extends Action {
	
	private MonitorPixel start;
	private MonitorPixel end;
	private float duration;
	private boolean left;
	
	public static final Variants VAR = Variants.MOUSE_DRAG;
	public static final String V_TO_STRING = "md";
	
	/**
	 * The empty constructor whose values will be filled in by unNotate.
	 */
	public MouseDrag () {
		super(VAR);
		start = new MonitorPixel();
		end = new MonitorPixel();
		duration = 0f;
		left = true;
	}
	
	/**
	 * The full constructor for a mouse drag.
	 * @param x1 starting x position
	 * @param y1 starting y position
	 * @param x2 ending x position
	 * @param y2 ending y position
	 * @param duration over how many seconds the drag will last
	 * @param left whether the left or right button will be held down
	 */
	public MouseDrag (int x1, int y1, int x2, int y2, float duration, boolean left) {
		super(VAR);
		start = new MonitorPixel(x1, y1);
		end = new MonitorPixel(x2, y2);
		this.duration = duration;
		this.left = left;
	}

	@Override
	public float getThreadSleepTime() {
		return duration;
	}
	
	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + start.notate() + Action.NEXT_PROPERTY + end.notate() + Action.NEXT_PROPERTY + duration + Action.NEXT_PROPERTY + left + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) throws NumberFormatException {
		String[] parts = Action.splitNotated(s);
		this.start.unNotate(parts[0]);
		this.end.unNotate(parts[1]);
		this.duration = Float.parseFloat(parts[2]);
		this.left = Boolean.parseBoolean(parts[3]);
	}

	@Override
	public String describe() {
		String dir = "left";
		if (!left) {
			dir = "right";
		}
		return "A " + dir + "-click drag from " + start.describe() + " to " + end.describe() + " over a duration of " + duration + " seconds.";
	}

	@Override
	public void feedToRobot(Robot r) {
		// TODO Auto-generated method stub

	}

}
