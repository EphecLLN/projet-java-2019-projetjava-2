package projetJavaDC;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KotTest {

	@Test
	void testAjouterEvent() {
		boolean test = false;
		boolean test2 = false;
				
		Kot kotTest = new Kot("Kot1"); 
		if(kotTest.event.size() > 0) {
			test = true; 
		}
		assertEquals(false, test); 
		
		Kot kotTest2 = new Kot("Kot2");
		kotTest2.ajouterEvent("wifi");
		if(kotTest2.event.size() > 0) {
			test2 = true;
		}
		assertEquals(true, test2); 
	}


	@Test
	void testRetirerAllEvent() {
		boolean test = false; 
		
		Kot kotTest1 = new Kot("Kot1");
		kotTest1.ajouterEvent("wifi");
		kotTest1.ajouterEvent("anniversaire");
		kotTest1.retirerAllEvent();
		if(kotTest1.event.size() > 0) {
			test = false; 
		}
		assertEquals(false, test);

				
	}

}
