/**
 * 
 */
package lcts.text_based;

import java.util.Arrays;

/**
 * The central component of the ui. It calls the other main methods, and processes the arguments given.
 * @author SId
 *
 */
public class MacroCore {

	/**
	 * Main method
	 * @param args command line options
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			print("Starting ui");
		} else {
			switch(args[0]) {
				case "-create":
					print("Creating macro");
					MacroCreator.main(Arrays.copyOfRange(args, 1, args.length - 1));
					break;
				case "-manage":
					print("Managing macros");
					
					break;
				case "-run":
					print("Running a macro");
					break;
				case "-help":
					print("Help: ");
					break;
				case "-edit":
					print("Editing macro");
					break;
				default:
					print("No known options given, starting ui");
					break;
			}
		}
	}
	
	public static void print (String s) {
		System.out.println(s);
	}

}
