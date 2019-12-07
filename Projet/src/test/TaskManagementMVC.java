package test;

import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import projetJava.DateTempsRestantInvalideException;
import projetJava.MainApp;
import projetJava.controller.TaskManagementController;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.model.TaskManagement;
import projetJava.view.TaskManagementVueConsole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class TaskManagementMVC extends Application{ 

	public TaskManagement TaskManagementMVC;
	
	public TaskManagementMVC(String name) {
		//Création du modèle
		
		Student currentStudent = new Student(name);
		TaskManagement model = new TaskManagement(currentStudent);
		
		//Création des controleurs : une pour chaque vue
		//Chaque controleurs doit avoir une référence vers le model pour pouvoir le commander
		
		TaskManagementController controllerConsole = new TaskManagementController(model);
		//StudentController controllerGui = new StudentController(model);
		
		//Création des vues
		//Chaque vue doit connaître son controleur et avoir une référence vers le modèle pour l'observer
		
		TaskManagementVueConsole vueConsole = new TaskManagementVueConsole(model, controllerConsole);
		
		//StudentVueGui --> à venir
		
		//On donne la référence à la vue pour chaque controleur
		
		controllerConsole.addView(vueConsole);
		
		//controlleurGui --> à venir
	}
	
	public TaskManagementMVC(int id) {
		
		Student currentStudent = null;
		for(Student s : TaskManagement.getAllStudents()) {
			if(s.getId() == id) {
				currentStudent = s;
			}
		}
		
		TaskManagement model = new TaskManagement(currentStudent);
		
		TaskManagementController controllerConsole = new TaskManagementController(model);
		
		TaskManagementVueConsole vueConsole = new TaskManagementVueConsole(model, controllerConsole);
		
		controllerConsole.addView(vueConsole);
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
			        Student student4 = new Student("Robin");
					
			        try {
						Task task = new Task("Faire le ménage", new Date(2019, 11, 25)); //1
						Task task1 = new Task("Faire la vaiselle", new Date(2019, 11, 26)); //2
			            Task task2 = new Task("Passer l'aspirateur", new Date(2019, 11, 27)); //3
			            Task task3 = new Task("Ranger le commu", new Date(2019, 11, 26)); //4
			            
							student1.addTask(task);
							student2.addTask(task1);
							student3.addTask(task2);
							student3.addTask(task3);
						
			            
			            
					} catch (projetJava.model.DateTempsRestantInvalideException e) {	}
			        
			        
			        boolean test = false;
					while(!test) {
						
						Scanner sc = new Scanner(System.in);
						System.out.println("Pour se connecter tappez : C ");
						System.out.println("Pour s'inscrire tappez : I ");
						String carac = sc.next();
						
						
				        String StudentName = "";
						
						if(carac.equals("C")) {
							System.out.println(TaskManagement.getAllStudents());
							System.out.print("insérez votre ID : ");
							int id = sc.nextInt();
							
							TaskManagement s = new TaskManagement();
							if(s.login(id)) {
								//MainApp gui = new MainApp();
								//MainApp.launch();
								new TaskManagementMVC(id);
								test = true;
							}
						}
						else if(carac.equals("I")){
							System.out.println("insérez votre nom : ");
							StudentName = sc.next();
							new TaskManagementMVC(StudentName);
							test = true;
						}
						else{
							System.out.println("Erreur d'input réessayez \n");
						}
						
						
					}
				}

			}
			);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
