/**
 * 
 */
package lcts.api;

/**
 * @author SId
 *
 */
public class NoSuchActionException extends IllegalArgumentException {

	/**
	 * Empty constructor
	 */
	public NoSuchActionException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * message constructor
	 * @param s the detail method
	 */
	public NoSuchActionException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * cause constructor
	 * @param cause cause of the exception, saved
	 */
	public NoSuchActionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 2 argument constructor
	 * @param message the detail message, which is saved
	 * @param cause the cause of the exception, also saved
	 */
	public NoSuchActionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
