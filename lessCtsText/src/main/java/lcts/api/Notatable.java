/**
 * 
 */
package lcts.api;

/**
 * @author sidbid
 * Allows for the conversion of an object to and from a string, which can then be saved to a text file.
 * 
 * <p> Named after the term "Notation" in JSON, though the two are not related.
 */
public interface Notatable {
	
	/**
	 * Converts instance variables of an object to a string.
	 * @return the instance variables combined into a single string
	 */
	
	String notate ();
	
	/**
	 * Called by a new, empty object to set its instance variables.
	 * @param s the string containing the instance variables
	 * @throws ActionFormatException
	 */
	void unNotate (String s) throws ActionFormatException;

}
