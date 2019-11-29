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
	
	public StudentVueConsole(Student model, StudentController controller, 
			Task modelTask, StudentController controllerTask) {
		super(model, controller, modelTask, controllerTask);

		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model.getAllStudentsList());
		System.out.println(modelTask.getAllTasksList());
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
		System.out.println("Pour voir le temps restant d'un tâche commencez par insérer : T");
		System.out.println("Pour accomplir une tâche commencez par insérer : D \n");
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
					String str3 = "D";
					
					if(c.equals(str1)) {
						System.out.print("Nom de la tâche: "); 
					    Scanner scan = new Scanner(System.in);
					    String taskName="";

					    taskName+=scan.nextLine();
					      					
						
						
						System.out.print("Date limtie de la tâche sous le format 'DD-MM-YYYY' : ");
						String date = scan.next();
						affiche("");
						
						String annee = date.charAt(6) + "" + date.charAt(7) + date.charAt(8) + date.charAt(9);
						String mois = date.charAt(3) + "" + date.charAt(4);
						String jour = date.charAt(0) + "" + date.charAt(1);
						
						try {
							controller.addTask(taskName, new Date(Integer.parseInt(annee), Integer.parseInt(mois) -1, Integer.parseInt(jour)));
						}
						catch(DateTempsRestantInvalideException e){
							printHelp();
						}
					}
					
					
					else if(c.equals(str2)) {
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						
						if(controllerTask.timeLeft(i) == -1) {
							System.out.println("La tâche n'existe pas.");
						}
						else if(controllerTask.timeLeft(i) > 1) {
							System.out.println("Temps restant : " + controllerTask.timeLeft(i) + " jours.");
						}
						else {
							System.out.println("Temps restant : " + controllerTask.timeLeft(i) + " jour.");
						}
						
						
					}
					
					
					else if(c.equals(str3)){
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						
						controllerTask.accomplishTaskStudent(i);
						update(null, null);
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
