/**
 * 
 */
package lcts.api;

/**
 * @author SId
 * A callback that will be used to notate a class I have not made. For every class, a new class implementing this will have to be made.
 */
public interface NotateCallback {
	
	/**
	 * 
	 * @return
	 */
	String notate();
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	Notatable unNotate(String s);

}
