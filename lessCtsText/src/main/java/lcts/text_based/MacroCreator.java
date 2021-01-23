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
				throw new IOException("This file already exists, aborting. Delete with the macro manager if necessary.");
			}
			
			fc.makeFile();
			Macro m = new Macro(name);
			
			
			int potential = 7;
			do {
				print("Create another action(1: delay, 2: key press, 3: key hold, 4: mouse click, 5: mouse move, 6: mouse drag) or finish(0): ");
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
							
							break;
						case 4:
							print("Creating a mouse click.");
							m.addAction(new MouseClick(true));
							print("Finished making the mouse click.");
							break;
						case 5:
							print("Creating a mouse move. ");
							
							{
								float x1 = getPositiveFloat(input, "Enter initial mouse x: ", "Only numbers.");
								float y1 = getPositiveFloat(input, "Enter initial mouse y: ", "Only numbers.");
								float x2 = getPositiveFloat(input, "Enter final mouse x: ", "Only numbers.");
								float y2 = getPositiveFloat(input, "Enter final mouse y: ", "Only numbers.");
								float dur = getPositiveFloat(input, "Enter duration of the move, decimal ok: ", "Numbers only.");
								m.addAction(new MouseMove(x1, y1, x2, y2, dur));
							}
							
							print("Made the mouse move.");
							break;
						case 6:
							print("Creating a new mouse drag.");
							
							{
								float x1 = getPositiveFloat(input, "Enter initial mouse x: ", "Only numbers.");
								float y1 = getPositiveFloat(input, "Enter initial mouse y: ", "Only numbers.");
								float x2 = getPositiveFloat(input, "Enter final mouse x: ", "Only numbers.");
								float y2 = getPositiveFloat(input, "Enter final mouse y: ", "Only numbers.");
								float dur = getPositiveFloat(input, "Enter duration of the move, decimal ok: ", "Numbers only.");
								m.addAction(new MouseDrag(x1, y1, x2, y2, dur, true));
							}
							
							print("Created the mouse drag.");
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
			print(e.getMessage());
		} catch (IOException e) {
			print(e.getMessage());
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
		return false;
		//TODO
	}

}
