package projetJava;

/**
 * @author robin
 *
 */
public class Kot {

	public String nomKot;
	private int idKot;
	public static String [][] allKot = new String[100][2];
	public static int nombreDeKot;
	
	
	/**
	 * Constructeur qui créé un nouveau kot sur base de son nom
	 * @param nomKot
	 */
	public Kot(String nomKot) { // si l'étudiant n'a pas de kot il en crée un (donc nouveau nom)
		this.nomKot = nomKot;
		
		// met les noms des kot dans le tableau all kot à l'indice 0
		this.allKot[nombreDeKot][0] = nomKot; 
		
		nombreDeKot ++; 
		
		//initialise l'id du kot
		this.idKot = nombreDeKot; 
		
		// met une id (qui représente le numéro de kot) dans le tableau all kot à l'indice 1
		this.allKot[nombreDeKot-1][1] = String.valueOf(nombreDeKot);
	}
	
	
	
	
	/**
	 * Constructeur qui ajoute l'étudiant au kot dont l'id est mise en paramètre
	 * @param idKot
	 */
	public Kot(int idKot) {
		//parcourt le tableau de tout les kot, et vérifie si ils ont été créé ( != null )
		for(int i = 0; i < this.allKot.length && this.allKot[i][0] != null; i++) {
			if(idKot == Integer.parseInt(this.allKot[i][1])) {
				this.nomKot = this.allKot[i][0];
			}
		}	
	}
}
