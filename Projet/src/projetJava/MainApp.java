package projetJava;

import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.TaskEditDialogController;
import projetJava.view.TaskOverviewController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<Task> allTasks = FXCollections.observableArrayList();
	private ObservableList<Student> allStudents = FXCollections.observableArrayList();
	
	/**
     * Constructor
     */
    public MainApp() {
        allStudents.add(new Student("Victor"));
        allStudents.add(new Student("Julien")); 
        allStudents.add(new Student("Robin")); 
        
        allTasks.add(new Task("Faire le ménage", LocalDate.of(2020, 2, 21)));
		allTasks.add(new Task("Passer l'aspirateur", LocalDate.of(2020, 6, 21)));
	    allTasks.add(new Task("Nettoyer la salle de bain", LocalDate.of(2019, 12, 21)));
	    allTasks.add(new Task("Ranger le commu", LocalDate.of(2020, 4, 21)));
	    allTasks.add(new Task("Nettoyer le commu", LocalDate.of(2020, 5, 21)));
	    allTasks.add(new Task("Faire la vaiselle", LocalDate.of(2020, 06, 21)));
			
    }
	
	/**
	 * @return the allTasks
	 */
	public ObservableList<Task> getAllTasks() {
		return allTasks;
	}
	
	/**
	 * @return the allStudents
	 */
	public ObservableList<Student> getAllStudents() {
		return allStudents;
	}

	/**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	@Override
	public void start(Stage primaryStage) {		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Task management of a kot");
		
		//runConsole();
		
		initRootLayout();
		
		showTaskManagementOverview();
		
	}
	
	
	/**
	 * Initialize the root layout
	 */
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
     * Montre la gestion des tâches (TaskManagementOverview) dans le RootLayout (mise en page)
     */
    public void showTaskManagementOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskManagementOverview.fxml"));
            AnchorPane TaskManagementOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(TaskManagementOverview);
            
            //donner l'accès de controller task à la MainApp
            TaskOverviewController controllerTask = loader.getController();
            controllerTask.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ouvre une fenêtre pour écrire les détails spécifique d'une tâche.
     * Si l'utilisateut click sur ok les détails sont sauvé dans l'objet Tache
     * et renvoie true
     *
     * @param Task l'objet task à éditer
     * @return true si l'utilisateur click sur OK, false sinon.
     */
    public boolean showTaskEditDialog(Task task) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
           
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Set the Task into the controller.
            TaskEditDialogController controllerTask = loader.getController();
            
            controllerTask.setDialogStage(dialogStage);
            
            controllerTask.setTask(task);
           
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controllerTask.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
