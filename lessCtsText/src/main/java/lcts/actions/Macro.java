/**
 * 
 */
package lcts.actions;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.Scanner;

import lcts.api.ActionFormatException;
import lcts.api.Describable;
import lcts.api.NoSuchActionException;
import lcts.api.Notatable;

/**
 * A set of actions that corresponds to a text file. It can be run by passing in each action to the robot.
 * @author SId
 *
 */
public class Macro implements Notatable, Describable {
	
	private String name;
	private ArrayList<Action> actionList;
	
	public static final String DESCRIPTION_SYMBOL = "# ";
	public static final String DESCRIPTION_END = "\n";
	
	/**
	 * One argument constructor for a macro.
	 * @param name the name of the macro to be constructed. It will be the same as the file that the macro is opened from and saved to.
	 */
	public Macro (String name) {
		this.name = name;
		actionList = new ArrayList<Action>();
	}
	
	/**
	 * Adds another action that the robot will perform.
	 * @param a the action to be added
	 */
	public void addAction (Action a) {
		actionList.add(a);
	}
	
	/**
	 * Removes an action, so the robot will not perform it. 
	 * @param a the action to be removed
	 */
	public void removeAction (Action a) {
		actionList.remove(a);
	}
	
	/**
	 * Gets the number of actions in this macro, which is the size of the arraylist that it uses to keep track of them.
	 * @return the number of actions
	 */
	public int getNumActions () {
		return actionList.size();
	}
	
	/**
	 * How the robot is told what to do.
	 * @param r the robot that will be instructed
	 * @throws InterruptedException 
	 */
	@Deprecated
	public void feedToRobot (Robot r) throws InterruptedException {
		//TODO: change this to make a new robot and new thread in each
		for (Action a : actionList) {
			a.feedToRobot(r);
		}
	}
	
	/**
	 * A method to get the arrayList of actions, but safe because it returns a copy.
	 * @return the copy of the action arrayList
	 */
	public ArrayList<Action> getActionList () {
		ArrayList<Action> copy = new ArrayList<Action>(actionList.size());
		for (int i = 0; i < copy.size(); i++) {
			copy.add(i, actionList.get(i));
		}
		return copy;
	}

	@Override
	public String describe() {
		return "Macro " + name + "(has " + actionList.size() + " actions)";
	}

	@Override
	public String notate() {
		String rv = "";
		
		for (Action a : actionList) {
			rv += a.notate();
			rv += Macro.DESCRIPTION_SYMBOL + a.describe() + Macro.DESCRIPTION_END;
		}
		
		return rv;
	}

	@Override
	public void unNotate(String s) throws ActionFormatException, NumberFormatException, NoSuchActionException {
		Scanner scan = new Scanner(s);
		
		while (scan.hasNextLine()) {
			Action a;
			String nl = scan.nextLine();
			if (!nl.substring(0, 2).equals(DESCRIPTION_SYMBOL)) {
				a = Action.unNotateHelper(nl);
				addAction(a);
			}
		}
		
		scan.close();
		//TODO: make a counter for what line the macro was on, for the checker and also in case the macro fails
	}

}
