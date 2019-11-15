package projetJava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date; // API date

public class Etudiant {
	private String nom;
	private String prenom;
	private int idEtu;
	private static int nbEtudiants;
	
	private Kot infoKot;
	private Tache infoTache;
	
	
	//--------------------------------------CONSTRUCTEURS-------------------------------------------
	/**
	 * Construit un étudiant et incrémente le nombre d'étudiant à chaque création de celui ci
	 * @param nom
	 * @param prenom
	 * @param infoKot : information du kot, nomKot, idKot et le tableau de tout les kots
	 */
	public Etudiant(String nom, String prenom, Kot infoKot) {
		this.nom = nom;
		this.prenom = prenom;
		this.infoKot = infoKot;
		
		nbEtudiants++;
		
		this.idEtu = nbEtudiants;//initialiser l'id de l'étudiant
		
		this.infoTache = new Tache(); 
	}
	
	//------------------------------------GETTERS & SETTERS-----------------------------------------
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}



	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}



	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}



	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	/**
	 * @return the idEtu
	 */
	public int getIdEtu() {
		return idEtu;
	}



	/**
	 * @param idEtu the idEtu to set
	 */
	public void setIdEtu(int idEtu) {
		this.idEtu = idEtu;
	}



	/**
	 * @return the infoKot
	 */
	public Kot getInfoKot() {
		return infoKot;
	}



	/**
	 * @param infoKot the infoKot to set
	 */
	public void setInfoKot(Kot infoKot) {
		this.infoKot = infoKot;
	}



	/**
	 * @return the infoTache
	 */
	public Tache getInfoTache() {
		return infoTache;
	}



	/**
	 * @param infoTache the infoTache to set
	 */
	public void setInfoTache(Tache infoTache) {
		this.infoTache= infoTache;
	}


	/**
	 * @return the nbEtudiants
	 */
	public static int getNbEtudiants() {
		return nbEtudiants;
	}



	/**
	 * @param nbEtudiants the nbEtudiants to set
	 */
	public static void setNbEtudiants(int nbEtudiants) {
		Etudiant.nbEtudiants = nbEtudiants;
	}

	
	//---------------------------------------METHODES-------------------------------------------
	
	

	//-----------------------------------------MAIN----------------------------------------------
	public static void main(String[] args) {
		
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot(1));
		Etudiant etu3 = new Etudiant("Castermane", "Florent", new Kot("les shrabs"));
		Etudiant etu4 = new Etudiant("Du Jardin", "Alex", new Kot("Familly"));
		Etudiant etu5 = new Etudiant("Zuccet", "Alexandra", new Kot(2));
		Etudiant etu6 = new Etudiant("Du riz", "Deborah", new Kot(1));
		
		//etu1.infoTache.ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		//etu1.infoTache.ajouterTache("Faire le ménage", new Date(2019,10,20));
		//etu1.infoTache.ajouterTache("Passer l'aspi", new Date(2019,10,19));

		System.out.println(etu1.getInfoTache().getNbTaches());
		
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		etu2.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		
		System.out.println(etu1.getInfoTache().getNbTaches());

	}
	
}