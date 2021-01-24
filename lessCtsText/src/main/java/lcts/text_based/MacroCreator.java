/**
 * 
 */
package lcts.text_based;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import lcts.actions.Action;
import lcts.actions.Delay;
import lcts.actions.KeyHold;
import lcts.actions.KeyPress;
import lcts.actions.Macro;
import lcts.actions.MouseClick;
import lcts.actions.MouseDrag;
import lcts.actions.MouseHold;
import lcts.actions.MouseMove;
import lcts.api.FileConvenience;

/**
 * The main way to create macros(the other being to just edit a text file).
 * @author SId
 * 
 */
public class MacroCreator {

	/**
	 * @param args from the command line
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		print("Enter name of macro file(Omit extension): ");
		String name = input.next();
		FileConvenience fc;
		try {
			fc = new FileConvenience(name);
			if (fc.doesFileExist() == true) {
				input.close();
				throw new IOException("This file already exists, aborting. Delete if necessary.");
			}
			
			fc.makeFile();
			Macro m;
			boolean indef = getYesNo(input, "Will the macro repeat until the program is ended(y/n)? " , "Only y/n/yes/no");
			if (indef) {
				m = new Macro(name, indef);
			} else {
				int num = getPositiveInt(input, "Number of times the macro repeats: ", "positive integers only");
				m = new Macro(name, num);
			}
			
			
			int potential = 7;
			do {
				print("Create another action(1: delay, 2: key press, 3: key hold, 4: mouse click, 5: mouse hold, 6: mouse move, 7: mouse drag) or finish(0): ");
				if (input.hasNextInt()) {
					potential = input.nextInt();
					switch(potential) {
						case 1:
							print("Creating a delay.");
							
							{
								float dur = getPositiveFloat(input, "Enter the duration of the delay, decimal ok: ", "Numbers only");
								m.addAction(new Delay(dur));
							}
							
							print("Delay made.");
							break;
						case 2:
							print("Creating a keypress.");
							
							{
								int num = getPositiveInt(input, "Enter the keycode of the key you want pressed(Refer to the keycode list if necessary: ", "Integers only");
								m.addAction(new KeyPress(num));
							}
							
							print("Key press made.");
							break;
						case 3:
							print("Creating a key hold.");
							
							{
								int num = getPositiveInt(input, "Enter the keycode of the key you want pressed(Refer to the keycode list if necessary: ", "Integers only");
								float dur = getPositiveFloat(input, "Enter the duration of the delay, decimal ok: ", "Numbers only");
								m.addAction(new KeyHold(num, dur));
							}
							
							print("Key hold made.");
							break;
						case 4:
							print("Creating a mouse click.");
							
							
							{
								boolean left = getYesNo(input, "Left click, right click otherwise(y/n)? ", "Only y/n/yes/no");
								m.addAction(new MouseClick(left));
							}
							
							print("Mouse click made.");
							break;
						case 5:
							print("Creating a mouse hold.");
							
							
							{
								boolean left = getYesNo(input, "Left click, right click otherwise(y/n)? ", "Only y/n/yes/no");
								float dur = getPositiveFloat(input, "Enter duration of the move, decimal ok: ", "Numbers only.");
								m.addAction(new MouseHold(left, dur));
							}
							
							print("Mouse hold made.");
							break;
						case 6:
							print("Creating a mouse move. ");
							
							{
								int x1 = getPositiveInt(input, "Enter initial mouse x: ", "Only positive integers.");
								int y1 = getPositiveInt(input, "Enter initial mouse y: ", "Only positive integers.");
								int x2 = getPositiveInt(input, "Enter final mouse x: ", "Only positive integers.");
								int y2 = getPositiveInt(input, "Enter final mouse y: ", "Only positive integers.");
								float dur = getPositiveFloat(input, "Enter duration of the move, decimal ok: ", "Numbers only.");
								m.addAction(new MouseMove(x1, y1, x2, y2, dur));
							}
							
							print("Mouse move made.");
							break;
						case 7:
							print("Creating a new mouse drag.");
							
							{
								int x1 = getPositiveInt(input, "Enter initial mouse x: ", "Only positive integers.");
								int y1 = getPositiveInt(input, "Enter initial mouse y: ", "Only positive integers.");
								int x2 = getPositiveInt(input, "Enter final mouse x: ", "Only positive integers.");
								int y2 = getPositiveInt(input, "Enter final mouse y: ", "Only positive integers.");
								float dur = getPositiveFloat(input, "Enter duration of the move, decimal ok: ", "Numbers only.");
								boolean left = getYesNo(input, "Left click, right click otherwise(y/n)? ", "Only y/n/yes/no");
								m.addAction(new MouseDrag(x1, y1, x2, y2, dur, left));
							}
							
							print("Mouse drag made.");
							break;
						default:
							if (potential != 0) {
								print("Only integers from 1-6.");
							}
							break;
					}
				} else {
					print("Only integers. ");
				}
			}
			while (potential != 0);
			
			//after filling out m
			fc.storeString(m.notate());
			print("All done, the macro can now be run.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		input.close();
	}
	
	public static void print (String s) {
		System.out.println(s);
	}
	
	public static int getPositiveInt (Scanner scan, String message, String badInput) {
		int number;
		do {
			print(message);
			while (!scan.hasNextInt()) {
				print(badInput);
				scan.next();
			}
			number = scan.nextInt();
		}
		while (number <= 0);
		
		return number;
	}
	
	public static float getPositiveFloat (Scanner scan, String message, String badInput) {
		float number;
		do {
			print(message);
			while (!scan.hasNextFloat()) {
				print(badInput);
				scan.next();
			}
			number = scan.nextFloat();
		}
		while (number <= 0.0f);
		
		return number;
	}
	
	public static boolean getYesNo (Scanner scan, String message, String badInput) {
		boolean rv = false;
		print(message);
		
		while (true) {
			String ans = scan.nextLine().trim();
			if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
				rv = true;
				break;
			} else if (ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no")) {
				break;
			} else {
				print(badInput);
			}
		}
		return rv;
	}

}
