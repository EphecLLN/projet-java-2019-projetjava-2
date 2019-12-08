package projetJavaDC.view;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import projetJavaDC.DateTempsRestantInvalideException;
import projetJavaDC.controller.TaskManagementController;
import projetJavaDC.model.Student;
import projetJavaDC.model.Task;
import projetJavaDC.model.TaskManagement;

public class TaskManagementVueConsole extends TaskManagementVue implements Observer{
	protected Scanner sc;
	
	public TaskManagementVueConsole(TaskManagement model, TaskManagementController controller) {
		super(model, controller);

		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model.getAllStudentsList());
		System.out.println(model.getAllTasksList());
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
						
						
						String annee = date.charAt(6) + "" + date.charAt(7) + date.charAt(8) + date.charAt(9);
						String mois = date.charAt(3) + "" + date.charAt(4);
						String jour = date.charAt(0) + "" + date.charAt(1);
						
						System.out.print("Id de l'étudiant concerné : ");
						int idStudent = scan.nextInt();
						affiche("");
						
						Student student = new Student();
						for(Student stud : model.getAllStudents()) {
							if(stud.getId() == idStudent) {
								student = stud;
							}
						}
						try {
							controller.addTask(taskName, new Date(Integer.parseInt(annee), Integer.parseInt(mois) -1, Integer.parseInt(jour)), 
									student);
						}
						catch(DateTempsRestantInvalideException e){
							printHelp();
						}
					}
					
					
					else if(c.equals(str2)) {
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						
						if(controller.timeLeft(i) == -1) {
							System.out.println("La tâche n'existe pas.");
						}
						else if(controller.timeLeft(i) > 1) {
							System.out.println("Temps restant : " + controller.timeLeft(i) + " jours.");
						}
						else {
							System.out.println("Temps restant : " + controller.timeLeft(i) + " jour.");
						}
						
						
					}
					
					
					else if(c.equals(str3)){
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						
						controller.accomplishTaskStudent(i);
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
