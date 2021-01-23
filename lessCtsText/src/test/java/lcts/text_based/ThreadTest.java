package lcts.text_based;

import java.awt.AWTException;
import java.awt.Event;
import java.awt.Robot;
import java.awt.event.MouseEvent;

public class ThreadTest {
	
	private class RobotThread1 implements Runnable {
		
		private Robot r;
		
		public RobotThread1() throws AWTException {
			this.r = new Robot();
		}
		
		public RobotThread1 (Robot r) {
			this.r = r;
		}
		
		public void doSomething () {
			r.mousePress(MouseEvent.BUTTON1);
			print("Clicked");
		}

		@Override
		public void run() {
			doSomething();
		}
		
	}
	
	private class RobotThread2 extends Thread {
		
		public RobotThread2() {
			// TODO Auto-generated constructor stub
		}
	}
	
	private static class SimpleThread extends Thread {
		
		@Override
		public void run() {
			print("ran");
			print("Second thread counts " + Thread.activeCount());
			super.run();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		simpleTest();
	}
	
	public static void print (String s) {
		System.out.println(s);
	}
	
	public static void simpleTest () throws InterruptedException {
		print("Main thread counts " + Thread.activeCount());
		new SimpleThread().start();
		print("Main thread counts " + Thread.activeCount());
		print("Main thread counts " + Thread.activeCount());
		print("Main thread counts " + Thread.activeCount());
		Thread.sleep(1000);
		print("Main thread counts " + Thread.activeCount());
		print("Main thread counts " + Thread.activeCount());
	}

}
