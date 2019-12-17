/**
 * 
 */
package projetJavaDC.model;

/**
 * @author robin & victor 
 *
 */
public class DateTempsRestantInvalideException extends Exception {

	public DateTempsRestantInvalideException () {
		
	}
	public DateTempsRestantInvalideException(String message) {
		System.err.println(message);
	}
}
