/**
 * 
 */
package lcts.text_based;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lcts.actions.Action;
import lcts.actions.Delay;
import lcts.actions.KeyHold;
import lcts.actions.Macro;
import lcts.actions.MouseClick;
import lcts.actions.MouseDrag;
import lcts.actions.MouseMove;
import lcts.api.ActionFormatException;
import lcts.api.FileConvenience;
import lcts.api.NoSuchActionException;

/**
 * The only way to run macros. A macro file is fed to the robot, and it then performs each action.
 * @author SId
 *
 */
public class MacroRunner {
	
	/**
	 * A helper class that represents one thread, that comes with its own Robot.
	 * The purpose is to have the main thread pause while the robot does something, so that each action is properly spaced.
	 * <p>This should avoid any action being cut too short, and it means that each thread will be allowed to end. However, if timing
	 * is slightly offset, it may mean that multiple actions occur at once.
	 * @author SId
	 *
	 */
	private static class RobotThread implements Runnable {
		
		Action a;
		Robot r;
		
		/**
		 * Default constructor, which gets the instructions needed for the robot.
		 * @param a the action which has said instructions
		 * @throws AWTException
		 */
		public RobotThread (Action a) throws AWTException {
			this.a = a;
			this.r = new Robot();
		}

		@Override
		public void run() {
			try {
				print("Action being performed: " + a.describe());
				a.feedToRobot(r);
			} catch (InterruptedException e) {
				print("aborting action");
			}
		}
	}

	/**
	 * This function will provide the ui for and the ability to run a macro.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		print("Enter name of macro file(omit extension): ");
		String name = input.next();
		FileConvenience fc;
		
		try {
			fc = new FileConvenience(name);
			if (!fc.doesFileExist() ) {
				input.close();
				throw new IOException("The file does not exist, aborting. Make the macro with the creator if necessary.");
			}
			
			Macro m = new Macro(name);
			m.unNotate(fc.retrieveString());
			ArrayList<Action> actionsCopy = m.getActionList();
			
			do {
				for (int i = 0; i < m.getNumTimes(); i++) {
					Action a = actionsCopy.get(i);
					new Thread(new RobotThread(a)).start();
					Thread.sleep(Action.secsToMs(a.getThreadSleepTime()));
				}
			}
			while (m.getIndRepeats());
			
		} catch (IOException e) {
			input.close();
			print(e.getMessage());
		} catch (ActionFormatException e) {
			input.close();
			print(e.getMessage());
		} catch (NoSuchActionException e) {
			input.close();
			print(e.getMessage());
		} catch (NumberFormatException e) {
			input.close();
			print(e.getMessage());
		} catch (AWTException e) {
			input.close();
			print("There was an error making robots, aborting.");
		} catch (InterruptedException e) {	// this should realistically never happen
			input.close();
			print("Slight error performing the current action, aborting");
		}
		
		input.close();
	}
	
	public static void print(String s) {
		System.out.println(s);
	}
	
	//move to the test folder
	public static void test () {
		try {
			for (int i = 0; i < 10; i++) {
				new Thread(new RobotThread(new Delay(2.0f))).start();
				Thread.sleep(2 * 1000);
			}
		} catch (AWTException e) {
			print("Error making the robot, aborting.");
		} catch (InterruptedException e) {
			print("I was interrupted, that shouldn't have happened");
		}
	}
	
	public static void test2 () {
		try {
			for (int i = 0; i < 10; i++) {
				new Thread(new RobotThread(new MouseClick(true))).start();
				Thread.sleep(Action.secsToMs(Action.MIN_TIME));
			}
		} catch (AWTException e) {
			print("Error making the robot, aborting.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("I was interrupted, that shouldn't have happened");
		}
	}
	
	public static void test3 () {
		try {
			for (int i = 0; i < 2; i++) {
				new Thread(new RobotThread(new KeyHold(44, 1.5f))).start();
				Thread.sleep(Action.secsToMs(1.5f));
			}
		} catch (AWTException e) {
			print("Error making the robot, aborting.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("I was interrupted, that shouldn't have happened");
		}
	}
	
	public static void test4 () {
		try {
			MouseMove a = new MouseMove(10, 10, 500, 500, 3.2f);
			new Thread(new RobotThread(a)).start();
			Thread.sleep(Action.secsToMs(a.getThreadSleepTime()));
		} catch (AWTException e) {
			print("Error making the robot, aborting.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("I was interrupted, that shouldn't have happened");
		}
	}
	
	public static void test5 () {
		try {
			MouseDrag a = new MouseDrag(1000, 1000, 100, 100, 5f, true);	//TODO make it so that if the distance is small, it waits more + moves more(less loops)
			new Thread(new RobotThread(a)).start();
			Thread.sleep(Action.secsToMs(a.getThreadSleepTime()));
		} catch (AWTException e) {
			print("Error making the robot, aborting.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("I was interrupted, that shouldn't have happened");
		}
	}

}
