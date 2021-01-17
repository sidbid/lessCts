/**
 * 
 */
package lcts.actions;

import java.awt.Robot;

import lcts.api.Describable;
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
		MOUSE_MOVE,
		MOUSE_DRAG
		//TODO add a multi action, and a random action
	}
	
	private Variants v;
	
	public static final String NEXT_PROPERTY = "\t";
	public static final String NEXT_ACTION = "\n";
	
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
	 * How an action translates into something that the robot can actually do. Defined by each action variant.
	 * @param r the robot that will be performing the action.
	 */
	public abstract void feedToRobot (Robot r);

}
