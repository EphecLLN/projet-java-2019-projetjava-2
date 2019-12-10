package projetJava;

import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.StudentEditDialogController;
import projetJava.view.TaskEditDialogController;
import projetJava.view.TaskManagementOverviewController;

public class MainApp extends Application {

	private Stage primaryStage;
	//private AnchorPane TaskManagementOverview;
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

	/**
	 * Lors du run, la méthode start est appelez, elle même appelle le initRootLayout 
	 * et le showTaskManagementOverview.
	 * Et défini l'icon de l'application
	 */
	@Override
	public void start(Stage primaryStage) {		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Task management of a kot");
		this.primaryStage.getIcons().add(new Image("file:///C:/Users/robin/git/"
				+ "projet-java-2019-projetjava-2/resources/images/iconfinder_Note_Book_86977.png"));
		
		initRootLayout();
		showTaskManagementOverview();
	}
	
	
	/**
	 * Initialise le root layout (la racine)
	 */
	public void initRootLayout() {
        try {
            // charge le root layout via le ficher fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Affiche la scène contenant la disposition racine.
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
            // Chargement de l'aperçu des tâches.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskManagementOverview.fxml"));
            
            AnchorPane TaskManagementOverview = (AnchorPane) loader.load();
            //TaskManagementOverview = (AnchorPane) loader.load();
            
            // Mettre l'aperçu des tâche dans le centre du root layout.
            
            rootLayout.setCenter(TaskManagementOverview);
            //Scene scene = new Scene(TaskManagementOverview);
            //primaryStage.setScene(scene);
            //primaryStage.show();
            
            //donner l'accès de controller task à la MainApp
            TaskManagementOverviewController controllerTask = loader.getController();
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
     * @param Task the task to edit
     * @param MainApp the MainApp acces (pour les ArrayList).
     * @return true si l'utilisateur click sur OK, false si une erreur est apparue.
     */
    public boolean showTaskEditDialog(Task task, MainApp mainApp) {
        try {
            // Charger le fichier fxml et créer un nouveau "stage" pour le pop up du dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
           
            // Créé le dialogue "Stage" (fenêtre).
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // défini la tâche dans le controller
            TaskEditDialogController controllerTask = loader.getController();
            
            controllerTask.setDialogStage(dialogStage);
            
            controllerTask.setTask(task, mainApp);
            
            // Affiche la boîte de dialogue et attend que l'utilisateur la ferme.
            dialogStage.showAndWait();
            return controllerTask.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showStudentEditDialog(Student student, MainApp mainApp) {
    	try {
    		// Charger le fichier fxml et créer un nouveau "stage" pour le pop up du dialog.
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StudentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
        	
        	// Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Défini l'étudiant dans le controller
            StudentEditDialogController controllerTask = loader.getController();
           
            controllerTask.setDialogStage(dialogStage);
            controllerTask.setStudent(student, mainApp);
           
            
            // Affiche la boîte de dialogue et attend que l'utilisateur la ferme.
            dialogStage.showAndWait();
            return controllerTask.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
