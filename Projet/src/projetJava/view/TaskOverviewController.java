/**
 * 
 */
package projetJava.view;


import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import projetJava.MainApp;
import projetJava.model.DateTempsRestantInvalideException;
import projetJava.model.Student;
import projetJava.model.Task;

/**
 * @author robin
 *
 */
public class TaskOverviewController {

	@FXML
	private TableView<Task> taskTable;
	@FXML
	private TableView<Student> studentTable;
	@FXML
	private TableColumn<Task, Integer> idColumn;
	@FXML
    private TableColumn<Task, String> NameColumn;

    @FXML
    private Label idLabel;
    @FXML
    private Label NameLabel;
    
    @FXML
    private Label studentLabel;
    @FXML
    private Label deadlineLabel;
    
    @FXML
    private Label accomplishedLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TaskOverviewController() {
    }

    /**
	 * @return the taskTable
	 */
	public TableView<Task> getTaskTable() {
		return taskTable;
	}
	
	/**
	 * @return the studentTable
	 */
	public TableView<Student> getStudentTable() {
		return studentTable;
	}
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize les t�ches avec les 2 colonnes (id et name).
    	//La fl�che -> indique que nous utilisons une fonctionnalit� de Java 8 nomm�e fonctions Lambdas
    	idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        //cleat le d�tail de la t�che
        showTaskDetails(null);
        
        /*
         * Pour �tre inform� quand l�utilisateur s�lectionne une t�che dans la tableau , nous avons besoin d��couter les changements.
           Il y a une interface dans JavaFX appel�e ChangeListener comprenant une m�thode nomm� changed(...). Cette m�thode poss�de 
           trois param�tres: observable, oldValue, et newValue.
		   Cr�ation d'un ChangeListener via les expressions lambda introduite dans Java 8.
         * 
         */
        
        //Faire attention lorsqu'il y a des changement et regarder le d�tail de la t�che quand �a change
        taskTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTaskDetails(newValue));
        
       
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        System.out.println(mainApp.getAllTasks());
        taskTable.setItems(mainApp.getAllTasks());
        //studentTable.setItems(mainApp.getAllStudents());
    }
    
    /**
     * Rempli Tout les text (labels) pour montrer le d�tail d'un t�che
     * Si la t�che est null, alors tout les text(labels) sont clear.
     *
     * @param Tache ou null
     */
    private void showTaskDetails(Task task) {
        if (task != null) {
            // Fill the labels with info from the person object.
        	idLabel.setText(Integer.toString(task.getId()));
        	NameLabel.setText(task.getName());
        	
        	if(task.getStudent() == null) {
        		studentLabel.setText("Unknow Student");
        	}else {
        		studentLabel.setText(task.getStudent().getName());
        	}
        	
        	/*
        	deadlineLabel.setText(task.getDeadline().getDate() + "/" +
        							task.getDeadline().getMonth() + "/" +
        							task.getDeadline().getYear());
        	*/
        	
        	accomplishedLabel.setText(Boolean.toString(task.getAccomplished()));

            
        } else {
            // Person is null, remove all the text.
        	idLabel.setText("");
        	NameLabel.setText("");
        	studentLabel.setText("");
        	/*
        	deadlineLabel.setText("");
        	*/
        	
        	accomplishedLabel.setText("");
        }
    }
    
    @FXML
    private void handleDeleteTask() {
    	//On regarde quel SelectionModel il a choisi puis on prend l'index, et apr�s cela on remove dans l'arrayList
    	int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
    	
    	//On g�re l'erreur au cas ou l'utilisateur n'a s�lectionn� aucune t�che 
    	if (selectedIndex >= 0) {
    		
    		//Alerte s'ouvre pour que l'utilisateur confirme s'il veut supprimer la t�che
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Confirmation");
        	alert.setHeaderText("Confirm");
        	alert.setContentText(null);
        	
        	Optional<ButtonType> result = alert.showAndWait();
        	if(result.get() == ButtonType.OK) {
        		taskTable.getItems().remove(selectedIndex);
        	}
        	else {
        		alert.close();
        	}
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Appelez lorsque l'utilisateur appuie sur le boutton "nouveau"
     * Ouvre une interface pour editer les d�tails d'une t�che.
     * @throws DateTempsRestantInvalideException 
     */
    @FXML
    private void handleNewTask(){
        Task tempTask = new Task();
        boolean okClicked = mainApp.showTaskEditDialog(tempTask);
        
        if (okClicked) {
            mainApp.getAllTasks().add(tempTask);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean okClicked = mainApp.showTaskEditDialog(selectedTask);
            if (okClicked) {
                showTaskDetails(selectedTask);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a Task in the table.");

            alert.showAndWait();
        }
    }
}
