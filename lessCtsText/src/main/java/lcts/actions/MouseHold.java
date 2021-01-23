/**
 * 
 */
package lcts.actions;

import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.util.Timer;

import lcts.api.ActionFormatException;
import lcts.api.NoSuchActionException;

/**
 * The action variant representing a left or right mouse button hold for a certain number of seconds.
 * @author SId
 *
 */
public class MouseHold extends Action {
	
	private boolean left;
	private float duration;
	
	public static final Variants VAR = Variants.MOUSE_HOLD;
	public static final String V_TO_STRING = "mh";
	
	/**
	 * The empty constructor whose values will be filled out by unNotate.
	 */
	public MouseHold () {
		super(VAR);
		left = true;
		duration = 0.0f;
	}
	
	/**
	 * The full constructor for a mouse hold, specifying button and time
	 * @param left whether the left or right button will be held down
	 * @param duration for how many seconds the robot will press the button
	 */
	public MouseHold (boolean left, float duration) {
		super(VAR);
		this.left = left;
		this.duration = duration;
	}
	
	@Override
	public float getThreadSleepTime() {
		return duration;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + left + Action.NEXT_PROPERTY + duration + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) throws ActionFormatException, NumberFormatException, NoSuchActionException {
		String[] parts = Action.splitNotated(s);
		this.left = Boolean.parseBoolean(parts[0]);
		this.duration = Float.parseFloat(parts[1]);
	}

	@Override
	public String describe() {
		String dir = "left";
		if (!left) {
			dir = "right";
		}
		
		return "A " + dir + " mouse button hold for " + duration + " seconds.";
	}

	@Override
	public void feedToRobot(Robot r) throws InterruptedException {
		int button = MouseEvent.BUTTON1;
		if (!left) {
			button = MouseEvent.BUTTON2;
		}
		
		r.mousePress(button);
		Thread.sleep(Action.secsToMs(duration));
		r.mouseRelease(button);
	}

	
}
