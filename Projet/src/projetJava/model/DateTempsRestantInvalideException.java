/**
 * 
 */
package projetJava.model;

/**
 * @author robin
 *
 */
public class DateTempsRestantInvalideException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DateTempsRestantInvalideException () {
		
	}
	public DateTempsRestantInvalideException(String message) {
		System.err.println(message);
	}
}
