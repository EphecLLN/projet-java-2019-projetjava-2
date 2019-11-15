/**
 * 
 */
package projetJava;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

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
		
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		etu2.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		
		
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
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		fail("Not yet implemented");
	}
	
	
	
	@Test
	void testVerifierTacheAccomplie() {
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		Etudiant etu2 = new Etudiant("Cotton", "Victor", new Kot(1));
		
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		etu2.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		
		assertEquals(false, etu1.getInfoTache().verifierTacheAccomplie(1));
		assertEquals(false, etu2.getInfoTache().verifierTacheAccomplie(1));
		
		etu1.getInfoTache().tacheAccomplie(1);
		
		assertEquals(true, etu1.getInfoTache().verifierTacheAccomplie(1));
		assertEquals(false, etu2.getInfoTache().verifierTacheAccomplie(1));
	}
	
	@Test
	void testTacheAccomplie() {
		Etudiant etu1 = new Etudiant("Castermane", "Robin", new Kot("kot à projet"));
		etu1.getInfoTache().ajouterTache("Faire la vaiselle", new Date(2019,10,21));
		fail("Not yet implemented");
	}	
}
