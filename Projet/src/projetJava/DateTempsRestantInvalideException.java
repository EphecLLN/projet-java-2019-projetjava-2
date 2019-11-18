/**
 * 
 */
package projetJava;

/**
 * @author robin
 *
 */
public class DateTempsRestantInvalideException extends Exception {

	public DateTempsRestantInvalideException () {
		
	}
	public DateTempsRestantInvalideException(String message) {
		System.err.println(message);
	}
}
