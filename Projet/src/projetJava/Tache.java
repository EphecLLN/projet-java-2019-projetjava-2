/**
 * 
 */
package projetJava;


import java.util.Calendar;
import java.util.Date;
/**
 * @author robin
 *
 */
public class Tache {
	private String [][] allTaches = new String[10][4];
	private int tbNomTache = 0; //indice 0 du tableau
	private int tbDateLimite = 1; //indice 1 du tableau
	private int tbIdTache = 2; //indice 2 du tableau
	private int tbTacheAccomplie = 3;
	
	private String nomTache;
	private int idTache;
	private Date dateLimite;
	
	
	
	private int nbTaches;
	
	
	//--------------------------------------CONSTRUCTEURS-------------------------------------------
	public Tache() {
		
	}
	/**
	 * @param nomTache
	 * @param dateLimite
	 */
	public Tache(String nomTache, Date dateLimite) {
		this.nomTache = nomTache;
		this.dateLimite = dateLimite;
		
	}

	
	
	//------------------------------------GETTERS & SETTERS-----------------------------------------
	/**
	 * @return the idTache
	 */
	public int getIdTache() {
		return idTache;
	}



	/**
	 * @param idTache the idTache to set
	 */
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}



	/**
	 * @return the nomTache
	 */
	public String getNomTache() {
		return nomTache;
	}



	/**
	 * @param nomTache the nomTache to set
	 */
	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}



	/**
	 * @return the dateLimite
	 */
	public Date getDateLimite() {
		return dateLimite;
	}



	/**
	 * @param dateLimite the dateLimite to set
	 */
	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}
	


	/**
	 * @return the allTaches
	 */
	public String[][] getAllTaches() {
		return allTaches;
	}



	/**
	 * @param allTaches the allTaches to set
	 */
	public void setAllTaches(String[][] allTaches) {
		this.allTaches = allTaches;
	}


	/**
	 * @return
	 */
	public String toString() { // affichage à changer
		String allTache = "";
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			allTache += "Taches " + this.allTaches[i][tbIdTache] + ": " + this.allTaches[i][tbNomTache] +
					", à finir pour le : " + this.allTaches[i][tbDateLimite] + ", Tache accomplie : " +
					this.allTaches[i][tbTacheAccomplie] + "\n";
		}
		return allTache;
	}
	
	//-----------------------------------------METHODES---------------------------------------------
	/**
	 * @param nomTache
	 * @param dateLimite
	 */
	public void ajouterTache(String nomTache, Date dateLimite) {
		this.allTaches[nbTaches][tbNomTache] = nomTache;
		this.allTaches[nbTaches][tbDateLimite] = String.valueOf(dateLimite.getTime());
		this.allTaches[nbTaches][tbIdTache] = String.valueOf(1 + nbTaches);
		this.allTaches[nbTaches][tbTacheAccomplie] = "NON";
		
		nbTaches++;
		
	}
	
	
	/**
	 *
	 * @param idTache : int
	 * @return resultat : float, le nombre de jour entre aujourd'hui et la date limite de la tache traitée
	 */
	public float tempsRestant(int idTache) { 
		Calendar cal = Calendar.getInstance();

		Date d1 = new Date(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
		
		long tempsATraiter = 0;
		
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				tempsATraiter = Long.parseLong(this.allTaches[i][tbDateLimite]);
			}
		}
		long diff = tempsATraiter - d1.getTime();
		float resultat = (diff/(1000*60*60*24));
	
		return resultat;
	}
	
	public String verifierTacheAccomplie(int idTache) {
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				return this.allTaches[i][tbTacheAccomplie];
			}
		}
		return null;
	}
	
	public void tacheAccomplie(int idTache) {
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				this.allTaches[i][tbTacheAccomplie] = "OUI";
			}
		}
	}
	
	
	
	//------------------------------------------MAIN------------------------------------------------

	public static void main() {
	
	}
}