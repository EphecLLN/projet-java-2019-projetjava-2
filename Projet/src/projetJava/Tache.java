/**
 * 
 */
package projetJava;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Robin Castermane
 *
 */
public class Tache {
	private String [][] allTaches = new String[10][5]; // tableau contenant toutes les t�ches de l'�tudiant
	private int tbNomTache = 0; //indice 0 du tableau, nom de la t�che
	private int tbGetTime = 1; //indice 1 du tableau, date.getTime() de la dateLimite
	private int tbDateLimite = 2; //indice 2 du tableau, toString() de la dateLimite
	private int tbIdTache = 3; //indice 3 du tableau, Id de la tache
	private int tbTacheAccomplie = 4; // indice 4 du tableau, "NON" ou "OUI" si la tache est accomplie ou pas
	
	// ArrayList<String> = new ArrayList() � venir pour am�liorer le code
	
	private int nbTaches; //nombre de Tache par �tudiant
	
	//--------------------------------------CONSTRUCTEURS-------------------------------------------
	public Tache() {
		
	}
	
	
	//------------------------------------GETTERS & SETTERS-----------------------------------------
	/**
	 * @return le tableau de tout les t�ches
	 */
	public String[][] getAllTaches() {
		return allTaches;
	}

	/**
	 * @param allTaches le tableau de tout les t�ches � mettre
	 */
	public void setAllTaches(String[][] allTaches) {
		this.allTaches = allTaches;
	}
	
	/**
	 * @return le nombre de taches que l'�tudiant a
	 */
	public int getNbTaches() {
		return this.nbTaches;
	}
	
	/**
	 * @param nbTaches, nombre de t�che � mettre
	 */
	public void setNbTaches(int nbTaches) {
		this.nbTaches = nbTaches;
	}
	
	
	//----------------------------------------TO STRING---------------------------------------------
	/**
	 * @return allTache : Un String reprenant toutes les informations des t�ches de l'�tudiant
	 */
	public String toString() {
		String allTache = "";
		
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			allTache += "Nom t�che: " +
						this.allTaches[i][tbNomTache] + "\t pour le: " +
						this.allTaches[i][tbDateLimite] + "\t id: " +
						this.allTaches[i][tbIdTache] +  "\t tache accomplie: " +
						this.allTaches[i][tbTacheAccomplie] + "\n";
		}
		return allTache;
	}
	
	
	//-----------------------------------------METHODES---------------------------------------------
	/**
	 * @param nomTache, nom de la t�che que l'on veut ajouter
	 * @param dateLimite, dateLimite pour effectuer cette T�che
	 */
	public void ajouterTache(String nomTache, Date dateLimite) throws DateTempsRestantInvalideException {
		boolean test = true;
		
		Date ajd = new Date();
		if(ajd.getYear() + 1900 > dateLimite.getYear()) {
			test = false;
			throw new DateTempsRestantInvalideException("ann�e encod� inf�rieur � cette ann�e-ci:" 
					+ dateLimite.getYear());
		}
		if(ajd.getYear() + 1900 == dateLimite.getYear() && ajd.getMonth() > dateLimite.getMonth()) {
			test = false;
			throw new DateTempsRestantInvalideException("mois encod� inf�rieur � celui de cette ann�e-ci:" 
					+ dateLimite.getMonth());
		}
		if(ajd.getYear() + 1900 == dateLimite.getYear() && ajd.getMonth() == dateLimite.getMonth() &&
				ajd.getDate() > dateLimite.getDate()) {
			test = false;
			throw new DateTempsRestantInvalideException("jours encod� inf�rieur � celui de cette ann�e-ci:" 
					+ dateLimite.getDate());
		}
		if(test) {
			this.allTaches[nbTaches][tbNomTache] = nomTache;
			this.allTaches[nbTaches][tbGetTime] = String.valueOf(dateLimite.getTime());
			this.allTaches[nbTaches][tbDateLimite] = String.valueOf(dateLimite.getDate() + "/" +
																dateLimite.getMonth() + "/" +
																dateLimite.getYear());
			this.allTaches[nbTaches][tbIdTache] = String.valueOf(1 + nbTaches);
			this.allTaches[nbTaches][tbTacheAccomplie] = "NON";
		
			nbTaches++;	
		}
	}
	
	
	
	/**
	 *
	 * @param idTache : int
	 * @return resultat : float, le nombre de jour entre aujourd'hui et la date limite de la tache trait�e
	 */
	public int tempsRestant(int idTache) { 
		Calendar cal = Calendar.getInstance(); // Date de ce jour-ci
		
		int jourActuel = cal.get(Calendar.YEAR);
		int moisActuel = cal.get(Calendar.MONTH);
		int anneeActuel = cal.get(Calendar.DATE);
		
		Date dateAjd = new Date(jourActuel, moisActuel, anneeActuel); // Pour avoir le m�me format
		
		long dateATraiter = 0;
		
		boolean test = false;
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				test = true;
				dateATraiter = Long.parseLong(this.allTaches[i][tbGetTime]); //prendre le getTime() de la date � traiter
			}
		}
		if(test) { // Si la t�che n'a pas �t� cr�ez
			long diff = dateATraiter - dateAjd.getTime(); 
			int resultat = (int)(diff/(1000*60*60*24));
			
			return resultat;
		}
		return 0;
	}
	
	
	
	/**
	 * @param idTache : int
	 * @return true si la tache � �t� accomplie, sinon false
	 */
	public Boolean verifierTacheAccomplie(int idTache) {
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				if(this.allTaches[i][tbTacheAccomplie] == "OUI") {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * Modifie le tableau de la tache, et mets dans celui la valeur de la tache accomplie � : "OUI"
	
	/**
	 * @param idTache : int
	 * @return true si l'id Tache � bien �t� trouv�, que la valeur de base �tait "NON" (accomplie)
	 * cela modifie donc le tableau de la tache et mets dans celui la valeur de la tache 
	 * accomplie � : "OUI"
	 * 		   false si l'id de la Tache n'a pas �t� trouv� ou qu'elle �tait d�j� accomplie.
	 */
	public boolean tacheAccomplie(int idTache) {
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				if(this.allTaches[i][tbTacheAccomplie] == "NON") {
					this.allTaches[i][tbTacheAccomplie] = "OUI";
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}