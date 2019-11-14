package projetJava;


import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Robin Castermane && Victor Cotton
 *
 */
public class Kot {
	private static String [][] allKot = new String[100][3];
	private int tbNomKot = 0; //indice 0 du tableau
	private int tbIdKot = 1; //indice 1 du tableau
	private int tbNombreEtu = 2; //indice 2 du tableau
	
	
	private String nomKot;
	private int idKot;
	private static int nombreDeKot;
	
	private ArrayList<String> event;
	private ArrayList<String> note;
	
	//--------------------------------------CONSTRUCTEURS-------------------------------------------
	/**
	 * Constructeur qui créé un nouveau kot sur base de son nom
	 * @param nomKot : le nom du kot à créer, se fait lors de l'inscription de l'etudiant
	 * si il n'a pas encore de Kot à rejoindre
	 */
	public Kot(String nomKot) { 
		this.nomKot = nomKot;
		this.idKot = nombreDeKot + 1; 
		this.event = new ArrayList<String>();
		this.note = new ArrayList<String>();
		
	// met les noms des kot dans le tableau all kot à l'indice 0
		this.allKot[nombreDeKot][tbNomKot] = nomKot; 
		this.allKot[nombreDeKot][tbNombreEtu] = String.valueOf(1);
	// met une id (qui représente le numéro de kot) dans le tableau all kot
		this.allKot[nombreDeKot][tbIdKot] = String.valueOf(nombreDeKot + 1);

		nombreDeKot ++; 		
	}
	
	
	/**
	 * Constructeur qui ajoute l'étudiant a un kot existant sur base de l'id de son kot
	 * @param idKot : id du kot qui est donné au createur du kot, lors de sa création
	 */
	public Kot(int idKot) {
		
		//parcourt le tableau de tout les kot, et vérifie si ils ont été créé ( != null )
		for(int i = 0; i < this.allKot.length && this.allKot[i][tbNomKot] != null; i++) {
			
			if(idKot == Integer.parseInt(this.allKot[i][tbIdKot])) {
				
				this.nomKot = this.allKot[i][tbNomKot];
				this.idKot = Integer.parseInt(this.allKot[i][tbIdKot]);
				
				int nbEtu = Integer.parseInt(this.allKot[i][tbNombreEtu]);
				nbEtu ++;
				this.allKot[i][tbNombreEtu] = String.valueOf(nbEtu);
			}
		}	
	}


	//------------------------------------GETTERS & SETTERS-----------------------------------------

	/**
	 * @return the nomKot
	 */
	public String getNomKot() {
		return nomKot;
	}


	/**
	 * @param nomKot the nomKot to set
	 */
	public void setNomKot(String nomKot) {
		this.nomKot = nomKot;
	}


	/**
	 * @return the idKot
	 */
	public int getIdKot() {
		return idKot;
	}


	/**
	 * @param idKot the idKot to set
	 */
	public void setIdKot(int idKot) {
		this.idKot = idKot;
	}


	/**
	 * @return the allKot
	 */
	public static String[][] getAllKot() {
		return allKot;
	}


	/**
	 * @param allKot the allKot to set
	 */
	public static void setAllKot(String[][] allKot) {
		Kot.allKot = allKot;
	}


	/**
	 * @return the nombreDeKot
	 */
	public static int getNombreDeKot() {
		return nombreDeKot;
	}


	/**
	 * @param nombreDeKot the nombreDeKot to set
	 */
	public static void setNombreDeKot(int nombreDeKot) {
		Kot.nombreDeKot = nombreDeKot;
	}
	

	public int getNombreEtuParKot() {
		for(int i = 0; i < allKot.length && allKot[i][tbNomKot] != null; i++) {
			if(this.idKot == Integer.parseInt(allKot[i][tbIdKot])) {
				return Integer.parseInt(allKot[i][tbNombreEtu]);
			}
		}
		return 0;
	}

	
	public String toString() {	
		return this.nomKot + " id: " + this.idKot + " nombreEtu: " + this.getNombreEtuParKot();
	}
	
	//-----------------------------------------METHODES-----------------------------------------
	/**
	 * Methode qui permet d'ajouter un evenements au calendrier du kot
	 * @param event : une string decrivant l'evenement souhaite
	 * @param dateEvent : la date à laquelle celui ci aura lieu, est de type date
	 */
	public void ajouterEvent(String event, Date dateEvent) {
	
		this.event.add(event); 
		
	}
	
	
	/**
	 * Methode qui permet de supprimer un evenement 
	 */
	public void supprimerEvent() {
		
	}
	
	
	/**
	 * Methode qui permet de supprimer tous les evenements contenus dans l'arraysList event 
	 */
	public void retirerAllEvent() {
		
	}
	
	
	/**
	 * Methode qui permet d'ajouter une note au calendrier du kot
	 * @param note
	 * @param dateNote
	 */
	public void ajouterNote(String note, Date dateNote) {
		this.note.add(note);
	}
	
	
	/**
	 * 
	 */
	public void retirerNote() {
		
	}
	
	
	/**
	 * 
	 */
	public void retirerAllNotes() {
		
	}
	//------------------------------------------MAIN------------------------------------------------
	
	public static void main(String[] args) {
		
	}
	
}
	
