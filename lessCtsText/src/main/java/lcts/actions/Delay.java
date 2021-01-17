/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

/**
 * @author SId
 * The action variant corresponding to the robot doing nothing, to be implemented using a timer.
 */
public class Delay extends Action {
	
	private float duration;
	
	public static final Variants VAR = Variants.DELAY;
	public static final String V_TO_STRING = "de";
	
	/**
	 * The empty constructor, whose values will be filled by unNotate.
	 */
	public Delay() {
		super(VAR);
		this.duration = 0f;
	}

	/**
	 * Constructs a new delay action with a duration
	 * @param duration the length of time of the delay, in seconds
	 */
	public Delay(float duration) {
		super(VAR);
		this.duration = duration;
	}

	@Override
	public String notate() {
		return V_TO_STRING + Action.NEXT_PROPERTY + duration + Action.NEXT_ACTION;
	}

	@Override
	public void unNotate(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public String describe() {
		return "A delay of " + duration + " seconds.";
	}

	@Override
	public void feedToRobot(Robot r) {
		// TODO Auto-generated method stub
	}

}
