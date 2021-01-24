/**
 * 
 */
package lcts.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Provides read, write, and file creation methods for a single file, corresponding to the file name provided.
 * <p>Files are stored in the TODO directory, where users can access them through other means as well.
 * @author SId
 * @see java.io.File
 * 
 */
public class FileConvenience {
	
	private String fileName;
	private File file;
	private boolean fileExists = true;
	//private Scanner scan;
	
	// for testing
	// public static final String LOCATION = "src/main/res/write/";
	public static final String LOCATION = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static final String DIR = "macros";
	public static final String FULL_LOC = LOCATION + SEP + DIR + SEP;
	public static final String EXTENSION = ".txt";
	
	/**
	 * Constructs a FileConvenience object that contains a file object.
	 * @param fileName the name of the file that all operations will relate to
	 * @throws IOException 
	 */
	public FileConvenience (String fileName) throws IOException {
		this.fileName = fileName;
		Files.createDirectories(Paths.get(FULL_LOC));
		file = new File(FULL_LOC + fileName + EXTENSION);
		
		if (!file.exists()) {
			fileExists = false;
		}
	}
	
	/**
	 * Makes the file specified by the name, and updates fileExists accordingly.
	 * @throws IOException
	 */
	public void makeFile () throws IOException {
		fileExists = file.createNewFile();
	}
	
	/**
	 * A getter for the fileExists instance variable.
	 * @return the existence of the file.
	 */
	public boolean doesFileExist () {
		return fileExists;
	}
	
	/**
	 * Stores a string in the file, by using a PrintWriter object.
	 * @param s the string to be written
	 * @throws FileNotFoundException if the file does not exist(should not happen unless this is called early)
	 */
	public void storeString (String s) throws FileNotFoundException {
		PrintWriter p = new PrintWriter(file);
		p.write(s);
		p.close();
	}
	
	/**
	 * Gets the string written in the file, or an empty string if the file is empty.
	 * @return the string that was written
	 * @throws FileNotFoundException 
	 */
	public String retrieveString () throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		scan.useDelimiter("\\Z");
		String content = scan.next();
		scan.close();
		return content;
	}
	
	/**
	 * Checks whether the file specified by the string was created, to determine whether or not writing is appropriate.
	 * @return existence of the file
	 */
	public boolean newFileCreated () {
		return fileExists;
	}
	
	

}
