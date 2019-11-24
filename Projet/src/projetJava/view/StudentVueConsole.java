package projetJava.view;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import projetJava.DateTempsRestantInvalideException;
import projetJava.controller.StudentController;
import projetJava.model.Student;
import projetJava.model.Task;

public class StudentVueConsole extends StudentVue implements Observer{
	protected Scanner sc;
	
	public StudentVueConsole(Student model, StudentController controller) {
		super(model, controller);

		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model.getAllStudentsList());
		Task task = new Task();
		System.out.println(task.getAllTasksList());
		printHelp();
		
	}

	@Override
	public void affiche(String string) {
		System.out.println(string);
	}

	@Override
	public void enableWarning() {
		System.out.println("Alerte");
	}

	@Override
	public void disableWarning() {
		System.out.println("Succès");
	}
	
	private void printHelp() {
		//System.out.println("Pour login : login + id utilisateur");
		System.out.println("Pour ajouter une tâche commencez par insérer : A");
		System.out.println("Pour voir le temps restant d'un tâche commencez par insérer : T \n");
	}

	private class ReadInput implements Runnable {

		@Override
		public void run() {
			while(true) {
				
					
					String c = sc.next();
					if(c.length()!= 1){
						affiche("Format d'input incorrect");
						printHelp();
					}
					String str1 = "A";
					String str2 = "T";
					
					if(c.equals(str1)) {
						System.out.print("Nom de la tâche : ");
						String taskName = sc.next();
						System.out.print("Date limtie de la tâche sous le format 'YYYY-MM-DD' : ");
						String date = sc.next();
						affiche("");
						
						String annee = date.charAt(0) + "" + date.charAt(1) + date.charAt(2) + date.charAt(3);
						String mois = date.charAt(5) + "" + date.charAt(6);
						String jour = date.charAt(8) + "" + date.charAt(9);
						
						try {
							controller.addTask(taskName, new Date(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour)));
						}
						catch(DateTempsRestantInvalideException e){
							printHelp();
						}
						
					}
					
					else if(c.equals(str2)) {
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						affiche("");
						controller.timeLeft(i); // problème il faut faire appel à une tache et pas à un controller
					}
					
					else {
						affiche(c);
						affiche("Opération incorrecte");
						printHelp();
					}
					
				
				
			
			}
		}
	}
}
