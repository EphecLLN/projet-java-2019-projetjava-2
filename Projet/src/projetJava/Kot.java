package projetJava;

/**
 * @author robin
 *
 */
public class Kot {

		public String nomKot;
		private int idKot;
		public static String [] allKot = new String[100];
		public static int nombreDeKot;
		
		public Kot(String nomKot) {
			this.nomKot = nomKot;
			
			nombreDeKot ++;
			this.allKot[nombreDeKot - 1] = nomKot;
			
			
			for(int i = 0; i < this.allKot.length; i++)	{
				if(this.nomKot == this.allKot[i]) {
					this.allKot[i] = null;
					nombreDeKot --;
				}
			}		
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			
			
		}
}
