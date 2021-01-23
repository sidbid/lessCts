package lcts.actions;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 4; i++) {
			System.out.println("Starting method");
			loopWithTimer();
		}
		
		System.out.println("\n\n");
		
		Timer t = new Timer();
		int count = 0;
		for (int i = 0; i < 4; i++) {
			System.out.println("Starting method");
			count += loopAndIncrementTime(t, count);
		}
		//TimerTask t = new TimerTask() {
		//	
		//	@Override
		//	public void run() {
		//		System.out.println("Done");
		//		cancel();
		//	}
		//};
		//
		//TimerTask t2 = new TimerTask() {
        //
		//	@Override
		//	public void run() {
		//		System.out.println("done again");
		//		cancel();
		//	}
		//	
		//};
		//Timer timer = new Timer("El timero mio");
		//long delay = 5000L;
		//timer.schedule(t, delay);
		//timer.schedule(t2, delay * 2);
		//Thread.sleep(delay * 2);
	}
	
	public static void loopWithTimer () {
		Timer time = new Timer("timer");
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				System.out.println("One iteration complete");
				time.cancel();
			}
			
		};
		
		time.schedule(tt, 4*1000);
		
	}
	
	//Does exactly what I want, which is to queue up all tasks and then execute them after longer and longer times
	public static int loopAndIncrementTime (Timer t, int alreadyDone) {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("one iteration complete");
				if (alreadyDone == 12) {
					t.cancel();
				}
				
			}
		};
		t.schedule(task, (4 + alreadyDone) * 1000);
		return 4;
	}

}
