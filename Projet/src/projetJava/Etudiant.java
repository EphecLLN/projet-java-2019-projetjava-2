package projetJava;
import java.util.Date;
public class Etudiant {
	private String nom;
	private String prenom;
	
	private int idEtu;
	private Kot infoKot;
	
	private static int nbEtudiants;
	
	private Tache infoTache;
	
	
	//--------------------------------------CONSTRUCTEURS-------------------------------------------
	/**
	 * Construit un �tudiant et incr�mente le nombre d'�tudiant � chaque cr�ation de celui ci
	 * @param nom
	 * @param prenom
	 * @param infoKot : information du kot, nomKot, idKot et le tableau de tout les kots
	 */
	public Etudiant(String nom, String prenom, Kot infoKot, Tache infoTache) {
		this.nom = nom;
		this.prenom = prenom;
		this.infoKot = infoKot;
		
		nbEtudiants++;
		
		//initialiser l'id de l'�tudiant
		this.idEtu = nbEtudiants;
		
		this.infoTache = infoTache;
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
		
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot � projet"), new Tache());
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot(1), new Tache());
		Etudiant etu3 = new Etudiant("Castermane", "Florent", new Kot("les shrabs"), new Tache());
		Etudiant etu4 = new Etudiant("Du Jardin", "Alex", new Kot("Familly"), new Tache());
		Etudiant etu5 = new Etudiant("Zuccet", "Alexandra", new Kot(2), new Tache());
		Etudiant etu6 = new Etudiant("Du riz", "Deborah", new Kot(1), new Tache());
		
		etu1.infoTache.ajouterTache("Faire la vaiselle", new Date(11,11,2019));
		etu1.infoTache.ajouterTache("Faire le m�nage", new Date(11,11,2019));
		etu1.infoTache.ajouterTache("Passer l'aspi", new Date(11,11,2019));
		
		System.out.println(etu1.infoTache.tachesRestantes());
		System.out.println(etu1.infoTache.tempsRestant(1));
		
		
		// TEST AFFICHAGE CONSOLE
		for(int i = 0; i < etu1.infoKot.allKot.length; i++) {
			if(etu1.infoKot.allKot[i][0] == null) {
				break;
			}
			System.out.print("Nom du premier Kot : '" +etu1.infoKot.allKot[i][0]);
			System.out.println("' et son Id : " + etu1.infoKot.allKot[i][1]);
		}
		
	}
}
