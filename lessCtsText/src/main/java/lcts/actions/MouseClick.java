/**
 * 
 */
package lcts.actions;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import lcts.actions.Action.Variants;

/**
 * The action variant representing a left or right mouse click to a robot.
 * @author SId
 * 
 */
public class MouseClick extends Action {
	
	private boolean left;
	
	public static final Variants VAR = Variants.MOUSE_CLICK;
	public static final String V_TO_STRING = "mc";
	
	/**
	 * The empty constructor whose values will be filled by unNotate.
	 */
	public MouseClick () {
		super(VAR);
		left = true;
	}
	
	/**
	 * The full constructor for a mouse click, including which of the two main buttons will be clicked.
	 * @param left whether it is a left click(true) or a right click(false)
	 */
	public MouseClick (boolean left) {
		super(VAR);
		this.left = left;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + left + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) throws NumberFormatException {
		String[] parts = Action.splitNotated(s);
		this.left = Boolean.parseBoolean(parts[0]);
	}

	@Override
	public String describe() {
		String dir;
		if (left) {
			dir = "left";
		} else {
			dir = "right";
		}
		
		return "A " + dir + " click.";
	}

	@Override
	public void feedToRobot(Robot r) throws InterruptedException {
		int button = InputEvent.BUTTON1_DOWN_MASK;
		if (!left) {
			button = InputEvent.BUTTON2_DOWN_MASK;
		}
		
		r.mousePress(button);
		Thread.sleep(Action.secsToMs(Action.MIN_TIME));
		r.mouseRelease(button);
	}

}
