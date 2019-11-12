package projetJava;

public class Etudiant {
	private String nom;
	private String prenom;
	private int idEtu;
	private Kot infoKot;
	private static int nbEtudiants;
	
	
	//--------------------------------------CONSTRUCTEURS-------------------------------------------
	public Etudiant(String nom, String prenom, Kot infoKot) {
		this.nom = nom;
		this.prenom = prenom;
		this.infoKot = infoKot;
		
		nbEtudiants++;
		
		//initialiser l'id de l'étudiant
		this.idEtu = nbEtudiants;
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


	//-----------------------------------------MAIN----------------------------------------------
	public static void main(String[] args) {
		
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot(1));
		Etudiant etu3 = new Etudiant("Castermane", "Florent", new Kot("les shrabs"));
		Etudiant etu4 = new Etudiant("Du Jardin", "Alex", new Kot("Familly"));
		Etudiant etu5 = new Etudiant("Zuccet", "Alexandra", new Kot(2));
		Etudiant etu6 = new Etudiant("Du riz", "Deborah", new Kot(1));

		for(int i = 0; i < etu1.infoKot.allKot.length; i++) {
			if(etu1.infoKot.allKot[i][0] == null) {
				break;
			}
			System.out.print("Nom du premier Kot : '" +etu1.infoKot.allKot[i][0]);
			System.out.println("' et son Id : " + etu1.infoKot.allKot[i][1]);
		}
		System.out.println(etu5.idEtu);
	}
}
