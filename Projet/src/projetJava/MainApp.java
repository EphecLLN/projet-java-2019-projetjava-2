package projetJava;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.StudentEditDialogController;
import projetJava.view.TaskEditDialogController;
import projetJava.view.TaskManagementOverviewController;

/**
 * @author Robin & Victor
 *
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane TaskManagementOverview;
	
	/* Utilisation d'un observableArrayList car celui-ci permet d'�couter les changements*/
	private ObservableList<Task> allTasks = FXCollections.observableArrayList();
	private ObservableList<Student> allStudents = FXCollections.observableArrayList();
	
	/**
     * Constructor
     */
    public MainApp() {
        allStudents.add(new Student("Victor"));
        allStudents.add(new Student("Julien")); 
        allStudents.add(new Student("Robin")); 
        
        allTasks.setAll(new Task("Faire le m�nage", LocalDate.of(2020, 2, 21)),
        				new Task("Passer l'aspirateur", LocalDate.of(2020, 6, 21)),
        				new Task("Nettoyer la salle de bain", LocalDate.of(2019, 12, 21)),
        				new Task("Ranger le commu", LocalDate.of(2020, 4, 21)),
        				new Task("Nettoyer le commu", LocalDate.of(2019, 12, 10)),//past
        				new Task("Faire la vaiselle", LocalDate.of(2020, 5, 21)),
        				new Task("Nettoyer le commu", LocalDate.of(2019, 5, 21)),//past
        				new Task("Faire la vaiselle", LocalDate.of(2020, 06, 21)));
        
        allTasks.get(0).setStudent(allStudents.get(0));
		allTasks.get(1).setStudent(allStudents.get(1));
		allTasks.get(2).setStudent(allStudents.get(0));
		allTasks.get(3).setStudent(allStudents.get(2));
		allTasks.get(4).setStudent(allStudents.get(1));
		allTasks.get(6).setStudent(allStudents.get(1));
		
		deleteTasksDone(); 	
    }
	
	/**
	 * @return the allTasks : la liste des t�ches � faire 
	 */
	public ObservableList<Task> getAllTasks() {
		return allTasks;
	}
	
	/**
	 * @return the allStudents : la liste des etudiants
	 */
	public ObservableList<Student> getAllStudents() {
		return allStudents;
	}

	/** 
     * @return the main stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	/**
	 * Lors du run, la m�thode start est appel�, elle m�me appelle la m�thode initRootLayout() 
	 * et la showTaskManagementOverview().
	 * Et d�fini l'icone de l'application
	 */
	@Override
	public void start(Stage primaryStage) {		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My Kot Manager");
		this.primaryStage.getIcons().add(new Image("file:///C:/Users/robin/git/"
				+ "projet-java-2019-projetjava-2/resources/images/iconfinder_Note_Book_86977.png"));
		
		showTaskManagementOverview();
	}

	/**
     * Montre la gestion des t�ches (TaskManagementOverview) dans le RootLayout (mise en page)
     */
    public void showTaskManagementOverview() {
        try {
            // Chargement de l'aper�u des t�ches.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskManagementOverview.fxml"));
            
            TaskManagementOverview = (AnchorPane) loader.load();
            
            Scene scene = new Scene(TaskManagementOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //donner l'acc�s de controller task � la MainApp
            TaskManagementOverviewController controllerTask = loader.getController();
            controllerTask.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ouvre une fen�tre pour �crire les d�tails sp�cifique d'une t�che.
     * Si l'utilisateur click sur ok, les d�tails sont sauv�s dans l'objet Tache
     * et renvoie true
     *
     * @param Task the task to edit
     * @param MainApp the MainApp acces (pour les ArrayList).
     * @return true si l'utilisateur click sur OK, false si une erreur est apparue.
     */
    public boolean showTaskEditDialog(Task task, MainApp mainApp) {
        try {
            // Charger le fichier fxml et cr�er un nouveau "stage" pour le pop up du dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
           
            // Cr�� le dialogue "Stage" (fen�tre).
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // d�fini la t�che dans le controller
            TaskEditDialogController controllerTask = loader.getController();
            
            controllerTask.setDialogStage(dialogStage);
            controllerTask.setTask(task, mainApp);
            
            // Affiche la bo�te de dialogue et attend que l'utilisateur la ferme.
            dialogStage.showAndWait();
            return controllerTask.isOkClicked(); 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * @param student : l'etudiant � editer 
     * @param mainApp : l'access � la mainApp(pour les arrayList)
     * @return true si l'utilisateur click sur "ok", false sinon
     */
    public boolean showStudentEditDialog(Student student, MainApp mainApp) {
    	try {
    		// Charger le fichier fxml et cr�er un nouveau "stage" pour le pop up du dialog.
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
            
            // D�fini l'�tudiant dans le controller
            StudentEditDialogController controllerTask = loader.getController();
           
            controllerTask.setDialogStage(dialogStage);
            controllerTask.setStudent(student, mainApp);
           
            
            // Affiche la bo�te de dialogue et attend que l'utilisateur la ferme.
            dialogStage.showAndWait();
            return controllerTask.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Supprime les t�ches qui sont faites et qui sont d�pass�s, celle qui sont d�pass�s mais non faites, recevrons
     * une alerte en console lors du chargement de l'application
     */
    public void deleteTasksDone() {
    	List <Task> copie = new ArrayList<Task>(allTasks);
    	allTasks.clear();
    	
    	int compteurId = 1;
    	for(int i = 0 ; i <  copie.size(); i++) {
    		if(copie.get(i).timeLeft() >= 0) {
    			
    			allTasks.add(copie.get(i));
    			copie.get(i).setId(compteurId);
    			compteurId ++;
    			
    		}else {
    			if(!copie.get(i).getAccomplished()) {
    				allTasks.add(copie.get(i));
    				copie.get(i).setId(compteurId);
        			compteurId ++;
        			System.out.println("T�che : <<" + copie.get(i).getName() + ">> (pour " + 
        							copie.get(i).getStudent().getName() + ") NON ACCOMPLIE, pour le " + 
        							copie.get(i).getDeadLine() + " est d�pass�!!");
        			
    				
    			}else {
    				System.out.println("T�che : <<" + copie.get(i).getName() + ">> accomplie, pour le " + 
    									copie.get(i).getDeadLine() + " est d�pass�, a donc �t� supprim�!");
    			}
    		}
    	}
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
