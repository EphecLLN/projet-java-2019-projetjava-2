package test;

import java.util.Date;
import java.util.Scanner;

import projetJava.DateTempsRestantInvalideException;
import projetJava.controller.StudentController;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.StudentVueConsole;


public class StudentMVC {

	public Student StudentMVC;
	public Task TaskMVC;
	
	public StudentMVC(String name) {
		//Création du modèle
		
		Student model = new Student(name);
		
		//Création des controleurs : une pour chaque vue
		//Chaque controleurs doit avoir une référence vers le model pour pouvoir le commander
		
		StudentController controllerConsole = new StudentController(model);
		StudentController controllerGui = new StudentController(model);
		
		//Création des vues
		//Chaque vue doit connaître son controleur et avoir une référence vers le modèle pour l'observer
		
		StudentVueConsole vueConsole = new StudentVueConsole(model, controllerConsole);
		//StudentVueGui --> à venir
		
		//On donne la référence à la vue pour chaque controleur
		
		controllerConsole.addView(vueConsole);
		//controlleurGui --> à venir
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					
			        Student student1 = new Student("Victor"); 
			        Student student2 = new Student ("Florent");
			        Student student3 = new Student("Julien");
					
			        try {
						Task task = new Task("Faire le ménage", new Date(2019, 11, 25)); //1
						Task task1 = new Task("Faire la vaiselle", new Date(2019, 11, 26)); //2
			            Task task2 = new Task("passer l'aspirateur", new Date(2019, 11, 27)); //3
			            try {
							student1.addTask(task);
							student1.addTask(task1);
							student1.addTask(task2);
						} catch (DateTempsRestantInvalideException e) {		}
			            
			            
					} catch (projetJava.model.DateTempsRestantInvalideException e) {	}
			        
					Scanner sc = new Scanner(System.in);
					System.out.print("Insérez votre nom : ");
					String name = sc.next();
					
					new StudentMVC(name);
				}

			}
			);
	}
}
