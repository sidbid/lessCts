/**
 * 
 */
package lcts.text_based;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Checking if awt's toolkit gets the dimensions of the monitor, even if a window is not created.
 * @author SId
 *
 */
public class ToolkitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Toolkit t = Toolkit.getDefaultToolkit();
		System.out.println(t.getScreenResolution());
		Dimension d = t.getScreenSize();
		System.out.println("The screen is " + d.getWidth() + " by " + d.getHeight() + ".");
		System.out.println("beeping");
		t.beep();	//there are so many possibilities here
	}

}
