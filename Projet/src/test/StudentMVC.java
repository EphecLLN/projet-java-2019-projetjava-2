package test;

import java.util.Scanner;

import projetJava.controller.StudentController;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.StudentVueConsole;


public class StudentMVC {

	public Student StudentMVC;
	public Task TaskMVC;
	
	public StudentMVC(String name) {
		//Cr�ation du mod�le
		
		Student model = new Student(name);
		
		//Cr�ation des controleurs : une pour chaque vue
		//Chaque controleurs doit avoir une r�f�rence vers le model pour pouvoir le commander
		
		StudentController controllerConsole = new StudentController(model);
		StudentController controllerGui = new StudentController(model);
		
		//Cr�ation des vues
		//Chaque vue doit conna�tre son controleur et avoir une r�f�rence vers le mod�le pour l'observer
		
		StudentVueConsole vueConsole = new StudentVueConsole(model, controllerConsole);
		//StudentVueGui --> � venir
		
		//On donne la r�f�rence � la vue pour chaque controleur
		
		controllerConsole.addView(vueConsole);
		//controlleurGui --> � venir
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					Scanner sc = new Scanner(System.in);
					System.out.print("Ins�rez votre nom : ");
					String name = sc.next();
					
					new StudentMVC(name);
				}

			}
			);
	}
}
