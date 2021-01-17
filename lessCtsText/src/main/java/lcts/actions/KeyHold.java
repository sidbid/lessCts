/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

import lcts.actions.Action.Variants;

/**
 * @author SId
 * The action variant representing a key hold, where the robot will hold down a key for a certain amount of time and then release. Will make use of a timer.
 */
public class KeyHold extends Action {
	
	private int keycode;
	private float duration;
	
	public static final Variants VAR = Variants.KEY_HOLD;
	public static final String V_TO_STRING = "kh";
	
	/**
	 * The empty constructor whose values will be filled by unNotate.
	 */
	public KeyHold () {
		super(VAR);
		keycode = 0;
		duration = 0f;
	}
	
	/**
	 * The full constructor for a key hold, with the key and duration specified.
	 * @param keycode the code for the key that the robot will press
	 * @param duration the amount of time, in seconds, that the robot will press the key
	 */
	public KeyHold (int keycode, float duration) {
		super(VAR);
		this.keycode = keycode;
		this.duration = duration;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + keycode + Action.NEXT_PROPERTY + duration + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public String describe() {
		return "A key hold of key " + keycode + " for " + duration + " seconds.";
	}

	@Override
	public void feedToRobot(Robot r) {
		// TODO Auto-generated method stub

	}

}
