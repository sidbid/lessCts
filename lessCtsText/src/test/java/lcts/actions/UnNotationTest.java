/**
 * 
 */
package lcts.actions;

import java.io.FileNotFoundException;
import java.io.IOException;

import lcts.api.FileConvenience;

/**
 * @author SId
 *
 */
public class UnNotationTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try {
			testTwo();
		} catch (FileNotFoundException e) {
			print(e.getMessage());
		}
		
		testOne();
	}
	
	public static void testOne () {
		Macro m = new Macro("TestMacro");
		m.unNotate("de	2.0");
		print(m.describe());
		m.unNotate("kp	234\r\n" + 
				"# Key press of key 234.");
		print(m.describe());
		m.unNotate("mc	true\r\n" + 
				"# A left click.\r\n" + 
				"kh	32	8.999\r\n" + 
				"# A key hold of key 32 for 8.999 seconds.");
		print(m.describe());
		m.unNotate("mm	(34.0, 34.0)	(43.0, 43.0)	23.3");
		print(m.describe());
		m.unNotate("md	(234.0, 36.0)	(4325.0, 457.0)	23.11	true\n" + 
				"# A left-click drag from the point (234.0, 36.0) to the point the point (4325.0, 457.0) over a duration of 23.11 seconds.");
		print(m.notate());
	}
	
	public static void testTwo () throws IOException {
		Macro m = new Macro("TestMacro2");
		
		Delay d = new Delay(4.2f);
		KeyPress k1 = new KeyPress(102);
		KeyHold k2 = new KeyHold(102, 4.34f);
		MouseClick m1 = new MouseClick(false);
		MouseHold m2 = new MouseHold(true, 9.9789f);
		MouseMove m3 = new MouseMove(10, 10, 20, 20, 1);
		MouseDrag m4 = new MouseDrag(30, 30, 60, 60, 4.3f, true);
		
		m.unNotate(d.notate());
		m.unNotate(k1.notate());
		m.unNotate(k2.notate());
		m.unNotate(m1.notate());
		m.unNotate(m2.notate());
		m.unNotate(m3.notate());
		m.unNotate(m4.notate());
		
		print(m.describe());
		print(m4.notate());
		
		Macro mac2 = new Macro("TestMacro3");
		String all = d.notate() + k1.notate() + k2.notate() + m1.notate() + m2.notate() + m3.notate() + m4.notate();
		mac2.unNotate(all);
		print(mac2.describe());
		
		Macro mac3 = new Macro("TestMacro4");
		FileConvenience fc = new FileConvenience("UnNotateFromFileTest");
		fc.storeString(all);
		mac3.unNotate(fc.retrieveString());
		print(mac3.describe());
		
		Macro mac4 = new Macro("TestMacro5");
		FileConvenience fc2 = new FileConvenience("UnNotateFromFileTest");
		fc2.storeString(mac3.notate());
		mac4.unNotate(fc2.retrieveString());
		print(mac4.describe());			//testing if the description comments are messing this up
		
		//Conclusion: unNotation clearly works, even from a file
		//My guess is that in test 1, copy pasting the notated actions must have put additional characters or something
		
		//Final words: the reason test 1 was failing was due to an error parsing point2ds. All unNotation should work now.
	}
	
	public static void print(int a) {
		System.out.println(a);
	}
	
	public static void print(String s) {
		System.out.println(s);
	}

}
