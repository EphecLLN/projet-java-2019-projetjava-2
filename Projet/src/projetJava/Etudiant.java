package projetJava;

public class Etudiant {
	private String nom;
	private String prenom;
	private int idEtu;
	private Kot infoKot;
	
	public Etudiant(String nom, String prenom, Kot infoKot) {
		this.nom = nom;
		this.prenom = prenom;
		//this.idEtu géré avec un compteur 
		this.infoKot = infoKot;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot("kot à projet"));

		for(int i = 0; i < etu1.infoKot.allKot.length; i++) {
			System.out.println(etu1.infoKot.allKot[i]);
		}
			
		
	}

}
