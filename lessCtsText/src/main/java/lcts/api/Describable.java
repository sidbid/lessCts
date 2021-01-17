/**
 * 
 */
package lcts.api;

/**
 * @author SId
 * Makes it easier to see what values an object contains, especially when saved in a text file.
 * 
 * <p>Different from Object's toString method because this emphasizes readability.
 */
public interface Describable {
	
	/**
	 * Creates a sentence that describes the object, including instance variables and the class it belongs to.
	 * @return the sentence detailing the object.
	 */
	String describe();

}
