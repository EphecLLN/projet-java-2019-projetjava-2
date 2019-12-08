/**
 * 
 */
package projetJava.view;


import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import projetJava.MainApp;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.util.DateUtil;

/**
 * @author robin
 *
 */
public class TaskOverviewController {

	@FXML
	private TableView<Task> taskTable;
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
    @FXML
    private Label dayLabel;
    @FXML
    private Label monthLabel;
    @FXML
    private Label yearLabel;

    @FXML
	private TableView<Student> studentTable;
	@FXML
	private TableColumn<Student, Integer> idColumnStud;
	@FXML
	private TableColumn<Student, String> NameColumnStud;
	
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
        // Initialize les tâches avec les 2 colonnes (id et name).
    	//La flèche -> indique que nous utilisons une fonctionnalité de Java 8 nommée fonctions Lambdas
    	idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        idColumnStud.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        NameColumnStud.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        //clear le détail de la tâche
        showTaskDetails(null);
        
        
        
        /*
         * Pour être informé quand l’utilisateur sélectionne une tâche dans la tableau , nous avons besoin d’écouter les changements.
           Il y a une interface dans JavaFX appelée ChangeListener comprenant une méthode nommé changed(...). Cette méthode possède 
           trois paramètres: observable, oldValue, et newValue.
		   Création d'un ChangeListener via les expressions lambda introduite dans Java 8.
         * 
         */
        
        //Faire attention lorsqu'il y a des changement et regarder le détail de la tâche quand ça change
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
        taskTable.setItems(mainApp.getAllTasks());
        studentTable.setItems(mainApp.getAllStudents());
    }
    
    /**
     * Rempli Tout les text (labels) pour montrer le détail d'un tâche
     * Si la tâche est null, alors tout les text(labels) sont clear.
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
        	deadlineLabel.setText(DateUtil.format(task.getDeadLine()));
        	accomplishedLabel.setText(Boolean.toString(task.getAccomplished()));
        } else {
            // Person is null, remove all the text.
        	idLabel.setText("");
        	NameLabel.setText("");
        	studentLabel.setText("");
        	deadlineLabel.setText("");
        	accomplishedLabel.setText("");
        }
    }
    
    @FXML
    private void handleDeleteTask() {
    	//On regarde quel SelectionModel il a choisi puis on prend l'index, et après cela on remove dans l'arrayList
    	int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
    	
    	//On gère l'erreur au cas ou l'utilisateur n'a sélectionné aucune tâche 
    	if (selectedIndex >= 0) {
    		
    		//Alerte s'ouvre pour que l'utilisateur confirme s'il veut supprimer la tâche
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
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a Task in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Appelez lorsque l'utilisateur appuie sur le boutton "nouveau"
     * Ouvre une interface pour editer les détails d'une tâche.
     * @throws DateTempsRestantInvalideException 
     */
    @FXML
    private void handleNewTask(){
        Task tempTask = new Task();
        Task.setNbrOfTasks(taskTable.getItems().size() + 1);
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
    
    @FXML
    private void handleAccomplishTask() {
    	Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTask != null) {
    		selectedTask.setAccomplished(true);
    	}else {
    		 // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a Task in the table.");

            alert.showAndWait();
    	}
    }
    @FXML
    private void handleTimeLeft() {
    	Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTask != null) {
    		Period intervalPeriod = Period.between(LocalDate.now(), selectedTask.getDeadLine());
    		
    		dayLabel.setText("Difference of days: " + intervalPeriod.getDays());
    		monthLabel.setText("Difference of months: " + intervalPeriod.getMonths());
    		yearLabel.setText("Difference of years: " + intervalPeriod.getYears());
    	}
    }
    
    @FXML
    private void handleDeleteStudent() {
    	//On regarde quel SelectionModel il a choisi puis on prend l'index, et après cela on remove dans l'arrayList
    	int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
    	
    	//On gère l'erreur au cas ou l'utilisateur n'a sélectionné aucune tâche 
    	if (selectedIndex >= 0) {
    		
    		//Alerte s'ouvre pour que l'utilisateur confirme s'il veut supprimer la tâche
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Confirmation");
        	alert.setHeaderText("Confirm");
        	alert.setContentText(null);
        	
        	Optional<ButtonType> result = alert.showAndWait();
        	if(result.get() == ButtonType.OK) {
        		studentTable.getItems().remove(selectedIndex);
        	}
        	else {
        		alert.close();
        	}
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a Student in the table.");

            alert.showAndWait();
        }
    	
    }
    /**
     * Appelez lorsque l'utilisateur appuie sur le boutton "New"
     * Ouvre une interface pour editer les détails d'un étudiant.
     */
    @FXML
    private void handleNewstudent(){
    	Student tempStud = new Student();
    	Student.setNbrOfStudents(studentTable.getItems().size() + 1);
        boolean okClicked = mainApp.showStudentEditDialog(tempStud);
        
        if (okClicked) {
        	//Le add ne se fait pas !!!?? 
            mainApp.getAllStudents().add(tempStud);
        }
    }
    
}
