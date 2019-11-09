/**
 * 
 */
package projetJava;

import java.time.format.DateTimeFormatter;
import  java.time.LocalDate;

/**
 * @author robin
 *
 */
public class Tache {
	private int tbNomTache = 0;
	private int tbDateLimite = 1;
	private int tbIdTache = 2;
	
	private String nomTache;
	private int idTache;
	private Date dateLimite;
	private String [][] allTaches = new String[10][3];
	
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


	
	//-----------------------------------------METHODES---------------------------------------------
	public void ajouterTache(String nomTache, Date dateLimite) {
		this.allTaches[nbTaches][tbNomTache] = nomTache;
		this.allTaches[nbTaches][tbDateLimite] = dateLimite.formatDate();
		this.allTaches[nbTaches][tbIdTache] = String.valueOf(1 + nbTaches);
		
		nbTaches++;
		
	}
	
	public String tachesRestantes() {
		String allTache = "";
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			allTache += "Taches " + this.allTaches[i][tbIdTache] + ": " + this.allTaches[i][tbNomTache] 
					+ ", à finir pour le : " + this.allTaches[i][tbDateLimite] + "\n";
		}
		return allTache;
	}
	
	public int tempsRestant(int idTache) { // A REFAIRE AVEC UNE CLASS DATE EN STRING 
		//Et simplifier la méthode
		int tempsRestant;
		int jourActuel;
		int moisActuel;
		int anneeActuel;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.now();
		
		jourActuel = Integer.parseInt(dtf.format(localDate).charAt(0) + "" + 
				 dtf.format(localDate).charAt(1));
		moisActuel = Integer.parseInt(dtf.format(localDate).charAt(3) + "" + 
				 dtf.format(localDate).charAt(4));
		anneeActuel = Integer.parseInt(dtf.format(localDate).charAt(6) + "" + 
				 dtf.format(localDate).charAt(7) + "" + 
				 dtf.format(localDate).charAt(8) + "" + 
				 dtf.format(localDate).charAt(9));
		
		int jourTache = 0;
		int moisTache = 0;
		int anneeTache = 0;
		
		for(int i = 0; i < this.allTaches.length && this.allTaches[i][tbNomTache] != null; i++) {
			if(idTache == Integer.parseInt(this.allTaches[i][tbIdTache])) {
				jourTache = Integer.parseInt(this.allTaches[i][tbDateLimite].charAt(0) + "" +
						this.allTaches[i][tbDateLimite].charAt(1));
				moisTache = Integer.parseInt(this.allTaches[i][tbDateLimite].charAt(3) + "" +
						this.allTaches[i][tbDateLimite].charAt(4));
				anneeTache = Integer.parseInt(this.allTaches[i][tbDateLimite].charAt(6) + "" +
						this.allTaches[i][tbDateLimite].charAt(7) + "" + 
						this.allTaches[i][tbDateLimite].charAt(8) + "" +
						this.allTaches[i][tbDateLimite].charAt(9));
			}
		}
		tempsRestant = (anneeTache - anneeActuel)*365 + (moisTache - moisActuel)*30 + (jourTache - jourActuel);
		return tempsRestant; // retourne le nombre de jour restant pour une tache précise (id en paramètre)
	}
	
	
	//------------------------------------------MAIN------------------------------------------------

	public static void main() {
		
	}
}
