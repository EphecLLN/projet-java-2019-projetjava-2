/**
 * 
 */
package projetJava;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;


/**
 * @author robin
 *
 */
class TacheTest {

	@Test
	void testAjouterTache() {
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot(1));
		
		boolean test = false;
		boolean test2 = false;
		if(etu1.getInfoTache().getNbTaches() > 0) {
			test = true;
		}
		if(etu1.getInfoTache().getNbTaches() > 0) {
			test2 = true;
		}
		
		assertEquals(false, test);
		assertEquals(false, test2);
		
		try {
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		etu2.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		} catch (DateTempsRestantInvalideException e) {
		}
		
		if(etu1.getInfoTache().getNbTaches() > 0) {
			test = true;
		}
		if(etu1.getInfoTache().getNbTaches() > 0) {
			test2 = true;
		}
		
		assertEquals(true, test);
		assertEquals(true, test2);
	}
	
	@Test
	void testTempsRestant() {
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		
		Calendar cal = Calendar.getInstance(); // Date de ce jour-ci
		int anneeActuel = cal.get(Calendar.YEAR);
		int moisActuel = cal.get(Calendar.MONTH);
		int jourActuel = cal.get(Calendar.DATE);
		//Ceci me permet que le test soit valide tout les jours
		
		try {
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(anneeActuel,moisActuel,jourActuel + 5));
		etu1.getInfoTache().ajouterTache("Passer l'aspirateur", new Date(anneeActuel,moisActuel,jourActuel + 6));
		etu1.getInfoTache().ajouterTache("Ranger le commu", new Date(anneeActuel,moisActuel,jourActuel + 8));
		etu1.getInfoTache().ajouterTache("Nétoyer la salle de bain", new Date(anneeActuel,moisActuel,jourActuel + 9));
		} catch (DateTempsRestantInvalideException e) {
		}
		
		assertEquals(5, etu1.getInfoTache().tempsRestant(1)); //tache 1
		assertEquals(6, etu1.getInfoTache().tempsRestant(2)); //tache 2
		assertEquals(8, etu1.getInfoTache().tempsRestant(3)); //tache 3
		assertEquals(9, etu1.getInfoTache().tempsRestant(4)); //tache 4
	}
	
	
	
	@Test
	void testVerifierTacheAccomplie() {
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot(1));
		try {
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		etu2.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		} catch (DateTempsRestantInvalideException e) {
		}
		assertEquals(false, etu1.getInfoTache().verifierTacheAccomplie(1));
		assertEquals(false, etu2.getInfoTache().verifierTacheAccomplie(1));
		
		etu1.getInfoTache().tacheAccomplie(1);
		
		assertEquals(true, etu1.getInfoTache().verifierTacheAccomplie(1));
		assertEquals(false, etu2.getInfoTache().verifierTacheAccomplie(1));
	}
	
	@Test
	void testTacheAccomplie() {
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		try {
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019, 10, 29));
		etu1.getInfoTache().ajouterTache("Passer l'aspirateur", new Date(2019, 10, 29));
		etu1.getInfoTache().ajouterTache("Ranger le commu", new Date(2019, 10, 29));
		} catch (DateTempsRestantInvalideException e) {
		}
		assertEquals(true, etu1.getInfoTache().tacheAccomplie(1)); //tache 1
		assertEquals(false, etu1.getInfoTache().tacheAccomplie(1)); //tache 1
		assertEquals(true, etu1.getInfoTache().tacheAccomplie(2)); //tache 2
		assertEquals(false, etu1.getInfoTache().tacheAccomplie(2)); //tache 2
		assertEquals(true, etu1.getInfoTache().tacheAccomplie(3)); //tache 3
		assertEquals(false, etu1.getInfoTache().tacheAccomplie(3)); //tache 3
	}	
}
