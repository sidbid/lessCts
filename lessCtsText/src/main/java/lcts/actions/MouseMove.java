/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

import lcts.actions.Action.Variants;
import lcts.api.Point2d;

/**
 * The action variant corresponding to the robot moving the mouse from one point to another.
 * @author SId
 */
public class MouseMove extends Action {
	
	private Point2d start;
	private Point2d end;
	private float duration;
	
	public static final Variants VAR = Variants.MOUSE_MOVE;
	public static final String V_TO_STRING = "mm";
	
	/**
	 * The empty constructor whose values unNotate will fill out.
	 */
	public MouseMove () {
		super(VAR);
		start = new Point2d();
		end = new Point2d();
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
	public MouseMove (float x1, float y1, float x2, float y2, float duration) {
		super(VAR);
		start = new Point2d(x1, y1);
		end = new Point2d(x2, y2);
		this.duration = duration;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + start.notate() + Action.NEXT_PROPERTY + end.notate() + Action.NEXT_PROPERTY + duration + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public String describe() {
		return "A mouse move from " + start.describe() + " to " + end.describe() + " over " + duration + " seconds.";
	}

	@Override
	public void feedToRobot(Robot r) {
		// TODO Auto-generated method stub

	}

}
