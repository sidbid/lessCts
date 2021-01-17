/**
 * 
 */
package lcts.api;

/**
 * an exception that is thrown when an action does not match any format that an action variant can then be made from.
 * @author SId
 */
public class ActionFormatException extends IllegalArgumentException {

	/**
	 * Empty constructor
	 */
	public ActionFormatException() {
	}

	/**
	 * message constructor
	 * @param s the detail method
	 */
	public ActionFormatException(String s) {
		super(s);
	}

	/**
	 * cause constructor
	 * @param cause cause of the exception, saved
	 */
	public ActionFormatException(Throwable cause) {
		super(cause);
	}

	/**
	 * 2 argument constructor
	 * @param message the detail message, which is saved
	 * @param cause the cause of the exception, also saved
	 */
	public ActionFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
