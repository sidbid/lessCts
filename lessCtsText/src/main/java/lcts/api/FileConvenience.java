/**
 * 
 */
package lcts.api;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author SId
 * Provides read, write, and file creation methods for a single file, corresponding to the file name provided.
 * 
 * <p>Files are stored in the TODO directory, where users can access them through other means as well.
 */
public class FileConvenience {
	
	private String fileName;
	private File file;
	private Scanner scan;
	
	public static final String LOCATION = "src/main/res/write";
	public static final String EXTENSION = ".txt";
	
	/**
	 * Constructs a FileConvenience object that contains a file object.
	 * @param fileName the name of the file that all operations will relate to
	 * @throws IOException 
	 */
	public FileConvenience (String fileName) throws IOException {
		this.fileName = fileName;
		file = new File(LOCATION + fileName + EXTENSION);
		
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	/**
	 * Stores a string in the file, by using a PrintWriter object.
	 * @param s the string to be written
	 */
	public void storeString (String s) {
		
	}
	
	/**
	 * Gets the string written in the file, or an empty string if the file is empty.
	 * @return the string that was written
	 */
	public String retrieveString () {
		return "";
	}
	
	

}
