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
	
	/**
	 * The name of the macro, that corresponds to the file it is saved in.
	 * Not filled out by unNotate because it should already be known.
	 */
	private String name;
	/**
	 * All actions contained in the macro. This should be secure, hence the method used in the getter.
	 */
	private ArrayList<Action> actionList;
	/**
	 * Whether the macro, when run, will continually repeat until the program is terminated(probably by ctrl-c)
	 */
	private boolean indRepeats;
	/**
	 * The number of times the macro loops. Irrelevant if indRepeats is true.
	 */
	private int numTimes;
	/**
	 * Starts any line that is ignored when unNotating. Lines that use this are mostly just to provide information to the user.
	 */
	public static final String DESCRIPTION_SYMBOL = "# ";
	/**
	 * Just used to put a newline after a description
	 */
	public static final String DESCRIPTION_END = "\n";
	
	/**
	 * One argument constructor for a macro.
	 * @param name the name of the macro to be constructed. It will be the same as the file that the macro is opened from and saved to.
	 */
	public Macro (String name) {
		this.name = name;
		actionList = new ArrayList<Action>();
		indRepeats = false;
		numTimes = 1;
	}
	
	/**
	 * The constructor for an indefinitely repeating macro.
	 * @param name the name of the macro, same as the file that it is stored in
	 * @param repeats whether the macro continually repeats or not
	 */
	public Macro (String name, boolean repeats) {
		this.name = name;
		actionList = new ArrayList<Action>();
		this.indRepeats = repeats;
		numTimes = 0;
	}
	
	/**
	 * The constructor for a macro that repeats a certain number of times.
	 * @param name the name of the macro, same as the file that it is stored in
	 * @param numTimes the number of times the macro repeats before terminating
	 */
	public Macro (String name, int numTimes) {
		this.name = name;
		actionList = new ArrayList<Action>();
		this.numTimes = numTimes;
		indRepeats = false;
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
	 * @deprecated since a better method came up using threads(this class just provides a copy of its arraylist)
	 */
	@Deprecated
	public void feedToRobot (Robot r) throws InterruptedException {
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
		for (int i = 0; i < actionList.size(); i++) {
			copy.add(i, actionList.get(i));
		}
		return copy;
	}
	
	public boolean getIndRepeats () {
		return indRepeats;
	}
	
	public int getNumTimes () {
		return numTimes;
	}

	@Override
	public String describe() {
		return "Macro " + name + "(has " + actionList.size() + " actions)";
	}
	
	/**
	 * Notates itself, then loops through its actions and notates all of them
	 */
	@Override
	public String notate() {
		String rv = "";
		rv += indRepeats + Action.NEXT_ACTION;
		rv += DESCRIPTION_SYMBOL + "Whether the macro indefinitely repeats or not" + DESCRIPTION_END;
		rv += numTimes + Action.NEXT_ACTION;
		rv += DESCRIPTION_SYMBOL + "The number of times the macro repeats(ignore if the above was true)" + DESCRIPTION_END;
		
		for (Action a : actionList) {
			rv += a.notate();
			rv += Macro.DESCRIPTION_SYMBOL + a.describe() + Macro.DESCRIPTION_END;
		}
		
		return rv;
	}

	@Override
	public void unNotate(String s) throws ActionFormatException, NumberFormatException, NoSuchActionException {
		Scanner scan = new Scanner(s);
		
		//TODO make this more safe later
		indRepeats = Boolean.parseBoolean(scan.nextLine());
		scan.nextLine();
		numTimes = Integer.parseInt(scan.nextLine());
		scan.nextLine();
		
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
