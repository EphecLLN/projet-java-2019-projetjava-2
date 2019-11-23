package projetJava;


import java.util.Calendar;
import java.util.Date; // API date
import java.util.Scanner;

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
	
	
	public String toString() {
		return "Etudiant [nom= " + nom + ", prenom= " + prenom + ", id= " + idEtu + ", nomDuKot= " + 
				this.getInfoKot().getNomKot() + ", TâcheRestante= " + this.getInfoTache().getNbTaches() + "]";
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
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Insérer votre nom: ");
		String nom = sc.nextLine();
		System.out.print("Insérer votre prénom: ");
		String prenom = sc.nextLine();
		
		System.out.println(nom + " " + prenom);
		*/
		try {
			etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019, 11, 18));
			etu1.getInfoTache().ajouterTache("Passer l'aspirateur", new Date(2019, 10, 28));
			etu1.getInfoTache().ajouterTache("Ranger le commu", new Date(2019, 10, 28)); //Méthode void
			etu1.getInfoTache().ajouterTache("Nettoyer la sdb", new Date(2019, 10, 28)); //Méthode void
		} catch (DateTempsRestantInvalideException e) {
		}
		
		System.out.println(etu1);
		System.out.println("");
		System.out.println(etu1.getInfoTache().getNbTaches());
		System.out.println(etu1.getInfoTache());
		System.out.println(etu1.getInfoTache().tempsRestant(1));
		System.out.println("");
		System.out.println(etu1.getInfoTache().tempsRestant(1));
		
		System.out.println(etu1.getInfoTache().verifierTacheAccomplie(1));
		etu1.getInfoTache().tacheAccomplie(1);
		System.out.println(etu1.getInfoTache().verifierTacheAccomplie(1));
		
		
	}
	
}