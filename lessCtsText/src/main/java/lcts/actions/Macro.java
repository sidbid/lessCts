/**
 * 
 */
package lcts.actions;

import java.util.ArrayList;

import lcts.api.Describable;
import lcts.api.Notatable;

/**
 * A set of actions that corresponds to a text file. It can be run by passing in each action to the robot.
 * @author SId
 *
 */
public class Macro implements Notatable, Describable {
	
	private String name;
	private ArrayList<Action> actionList;
	
	public Macro (String name) {
		this.name = name;
		actionList = new ArrayList<Action>();
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
		}
		
		return rv;
	}

	@Override
	public void unNotate(String s) {
		// TODO Auto-generated method stub

	}

}
