/**
 * 
 */
package lcts.actions;

import java.awt.Robot;
import java.util.Arrays;
import java.util.Timer;

import lcts.api.Describable;
import lcts.api.NoSuchActionException;
import lcts.api.Notatable;

/**
 * The base class for all actions, such as a delay or a keypress.
 * 
 * <p>Its uses are to create a common type for all actions, to define the variants of actions, and to define delimiters for notation.
 * @author SId
 */
public abstract class Action implements Notatable, Describable {
	
	/**
	 * The various kinds of actions that the robot will be told to do. A class exists for each one, extended from action.
	 * @author SId
	 */
	public enum Variants {
		KEY_PRESS,
		KEY_HOLD,
		DELAY,
		MOUSE_CLICK,
		MOUSE_HOLD,
		MOUSE_MOVE,
		MOUSE_DRAG
		//TODO add a multi action, and a random action
	}
	
	private Variants v;
	
	public static final String NEXT_PROPERTY = "\t";
	public static final String NEXT_ACTION = "\n";
	public static final int SEC_TO_MS = 1000;
	public static final float MIN_TIME = 0.01f;
	
	/**
	 * Constructs a new action, with a defined type. Each subclass knows what variant it is, and will pass it in using super.
	 * @param v the type of action
	 */
	public Action (Variants v) {
		this.v = v;
	}
	
	/**
	 * A getter for the action variant.
	 * @return the variant
	 */
	public Variants getVariant () {
		return this.v;
	}
	
	/**
	 * The amount of time the thread should sleep for after beginning this action. Should be overridden by any action that has a duration
	 * instance variable, otherwise returns a default value.
	 * 
	 * @return the amount of time that the thread should sleep
	 */
	public float getThreadSleepTime () {
		return Action.MIN_TIME;
	}
	
	/**
	 * Convenience function that takes a line, determines the variant it corresponds to, and then uses that variants unNotate method to fill it out.
	 * @param line the line that represents the action
	 * @return the fully filled out action
	 */
	public static Action unNotateHelper (String line) throws NumberFormatException, NoSuchActionException {
		String var = line.substring(0, 2);
		Action av;
		
		switch(var) {
			case KeyPress.V_TO_STRING:
				av = new KeyPress();
				break;
			case KeyHold.V_TO_STRING:
				av = new KeyHold();
				break;
			case Delay.V_TO_STRING:
				av = new Delay();
				break;
			case MouseClick.V_TO_STRING:
				av = new MouseClick();
				break;
			case MouseHold.V_TO_STRING:
				av = new MouseHold();
				break;
			case MouseMove.V_TO_STRING:
				av = new MouseMove();
				break;
			case MouseDrag.V_TO_STRING:
				av = new MouseDrag();
				break;
			default:
				throw new NoSuchActionException("Action type " + var + " does not exist.");	//TODO make or use an exception here, potentially NoSuchVariantException
		}
		
		av.unNotate(line);
		return av;
	}
	
	/**
	 * Convenience function that splits up a line into an array based on the next property delimeter.
	 * @param s the string to be split
	 * @return an array split on the delimeters, minus the final element, which should have been the next action delimeter, and the first element, which just specified the type
	 */
	public static String[] splitNotated (String s) {
		String[] split = s.split(Action.NEXT_PROPERTY);
		//System.out.println(Arrays.toString(Arrays.copyOfRange(split, 1, split.length - 1)));
		return Arrays.copyOfRange(split, 1, split.length);
	}
	
	public static long secsToMs (float s) {
		return (long) (s * SEC_TO_MS);
	}
	
	/**
	 * How the robot is instructed what to do. Each variant defines this separately, even delay.
	 * <p>An explanation of how this works is present in Macro's feedToRobot method.
	 * @param r the robot that will be performing the action
	 * @throws InterruptedException 
	 */
	public abstract void feedToRobot (Robot r) throws InterruptedException;

}
