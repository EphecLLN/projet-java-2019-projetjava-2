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
					        //scan.close();					
						
						
						System.out.print("Date limtie de la tâche sous le format 'YYYY-MM-DD' : ");
						String date = scan.next();
						affiche("");
						
						String annee = date.charAt(0) + "" + date.charAt(1) + date.charAt(2) + date.charAt(3);
						String mois = date.charAt(5) + "" + date.charAt(6);
						String jour = date.charAt(8) + "" + date.charAt(9);
						
						try {
							controller.addTask(taskName, new Date(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour)));
							//controller.addTask(taskName, new Date(2019, 11, 28));
						}
						catch(DateTempsRestantInvalideException e){
							printHelp();
						}
						
					}
					
					else if(c.equals(str2)) {
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						
						
						Task task1 = new Task();
						for(Task task : task1.getAllTasks()) {
							if(task.getId() == (i)) {
								System.out.println(task.timeLeft() + " jour(s) \n");				
							}
						}
						
						//controller.timeLeft(i); // problème il faut faire appel à une tache et pas à un controller
					}
					
					else if(c.equals(str3)){
						System.out.print("Numéro de la tâche : ");
						int i = sc.nextInt();
						
						
						Task task1 = new Task();
						for(Task task : task1.getAllTasks()) {
							if(task.getId() == (i)) {
								if(task.getAccomplished() == false) {
									task.setAccomplished(true);
									System.out.println(task.getAccomplished());	
									update(null,null);
								}			
							}
						}
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
