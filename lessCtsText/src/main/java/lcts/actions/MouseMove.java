/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

import lcts.api.MonitorPixel;

/**
 * The action variant corresponding to the robot moving the mouse from one point to another.
 * @author SId
 */
public class MouseMove extends Action {
	
	private MonitorPixel start;
	private MonitorPixel end;
	private float duration;
	
	public static final Variants VAR = Variants.MOUSE_MOVE;
	public static final String V_TO_STRING = "mm";
	
	/**
	 * The empty constructor whose values unNotate will fill out.
	 */
	public MouseMove () {
		super(VAR);
		start = new MonitorPixel();
		end = new MonitorPixel();
		duration = 0f;
	}
	
	/**
	 * The full constructor for mouse movement
	 * @param x1 starting x position
	 * @param y1 starting y position
	 * @param x2 ending x position
	 * @param y2 ending y position
	 * @param duration the number of seconds over which the mouse will move
	 */
	public MouseMove (int x1, int y1, int x2, int y2, float duration) {
		super(VAR);
		start = new MonitorPixel(x1, y1);
		end = new MonitorPixel(x2, y2);
		this.duration = duration;
	}
	
	@Override
	public float getThreadSleepTime() {
		return duration;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + start.notate() + Action.NEXT_PROPERTY + end.notate() + Action.NEXT_PROPERTY + duration + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) throws NumberFormatException {
		String[] parts = Action.splitNotated(s);
		this.start.unNotate(parts[0]);
		this.end.unNotate(parts[1]);
		this.duration = Float.parseFloat(parts[2]);
	}

	@Override
	public String describe() {
		return "A mouse move from " + start.describe() + " to " + end.describe() + " over " + duration + " seconds.";
	}

	@Override
	public void feedToRobot(Robot r) throws InterruptedException {
		MonitorPixel vector = MonitorPixel.getPartialDist(start, end, (int) (duration * Action.TIMES_RENDERED_PS));
		int currX = (int) (start.getX()); 
		int currY = (int) (start.getY());
		for (float i = 0; i < duration * Action.TIMES_RENDERED_PS; i++) {
			r.mouseMove(currX, currY);
			currX += (int) (vector.getX());
			currY += (int) (vector.getY());
			Thread.sleep(Action.secsToMs(Action.RENDER_TIME));
		}
	}

}
