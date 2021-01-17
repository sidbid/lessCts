/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

import lcts.actions.Action.Variants;

/**
 * @author SId
 * The action variant that corresponds to the robot pressing a key once.
 */
public class KeyPress extends Action {
	
	private int keycode;
	
	public static final Variants VAR = Variants.KEY_PRESS;
	public static final String V_TO_STRING = "kp";
	
	/**
	 * the empty constructor, whose values will be filled by unNotate.
	 */
	public KeyPress () {
		super(VAR);
		keycode = 0;
	}
	
	/**
	 * The full constructor for a key press, with a keycode
	 * @param keycode the keycode that the robot will use
	 */
	public KeyPress(int keycode) {
		super(VAR);
		this.keycode = keycode;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + keycode + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public String describe() {
		return "Key press of key " + keycode + ".";
	}

	@Override
	public void feedToRobot(Robot r) {
		// TODO Auto-generated method stub

	}

}
