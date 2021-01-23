/**
 * 
 */
package lcts.text_based;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Timer;

import lcts.actions.Action;
import lcts.actions.Delay;
import lcts.actions.KeyHold;
import lcts.actions.MouseClick;

/**
 * The only way to run macros. A macro file is fed to the robot, and it then performs each action.
 * @author SId
 *
 */
public class MacroRunner {
	
	/**
	 * A helper class that represents one thread, that comes with its own Robot.
	 * The purpose is to have the main thread pause while the robot does something, so that each action is properly spaced.
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
	 * @param args
	 */
	public static void main(String[] args) {
		test3();
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

}
