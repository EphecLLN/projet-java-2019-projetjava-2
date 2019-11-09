/**
 * 
 */
package projetJava;

/**
 * @author robin
 *
 */
public class Date {

	private int jour;
	private int mois;
	private int annee;
	
	public Date(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	
	/**
	 * @return String : le format de la date sous JJ-MM-YYYY
	 */
	public String formatDate() {
		return this.jour + "-" + this.mois + "-" + this.annee; 
	}
	
}
