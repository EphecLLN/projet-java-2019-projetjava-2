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
 * @author robin & Victor
 *
 */
public class TaskManagementOverviewController {

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
	
    // Reference vers la main application.
    private MainApp mainApp;

    /**
     * The constructor is called before the initialize() method.
     */
    public TaskManagementOverviewController() {
    }

    /**
	 * @return the taskTable : la table des t�ches 
	 */
	public TableView<Task> getTaskTable() {
		return taskTable;
	}
	
	/**
	 * @return the studentTable : la table des etudiants 
	 */
	public TableView<Student> getStudentTable() {
		return studentTable;
	}
	
    /**
     * Initialise la classe de contr�leur. 
     * Cette m�thode est automatiquement appel�e apr�s le chargement du fichier fxml.
     */
    @FXML
    private void initialize() {
        // Initialize les t�ches avec les 2 colonnes (id et name).
    	//La fl�che -> indique que nous utilisons une fonctionnalit� de Java 8 nomm�e fonctions Lambdas
    	idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        idColumnStud.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        NameColumnStud.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        //clear le d�tail de la t�che
        showTaskDetails(null);
        
        
        
        /*
         * Pour savoir quand une valeur est s�lectionn�e dans une TableView, 
         * il faut r�cup�rer le mod�le de s�lection dans la table en invoquant sa m�thode getSelectionModel() 
         * et �couter les changements de valeur de sa propri�t� selectedIndex ou selectedItem avec �couteur de 
         * type InvalidationListener ou un ChangeListener.
         * 
         */
        
        //Faire attention lorsqu'il y a des changement et regarder le d�tail de la t�che quand �a change
        taskTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTaskDetails(newValue));
    }

    /**
     * Est appel� par l'application principale pour se renvoyer une r�f�rence.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Ajoute les donn�es de l'observalbe list dans les table
        taskTable.setItems(mainApp.getAllTasks());
        studentTable.setItems(mainApp.getAllStudents());
    }
    
    /**
     * Rempli Tout les text (labels) pour montrer le d�tail d'un t�che
     * Si la t�che est null, alors tout les text(labels) sont clear.
     *
     * @param Tache ou null
     */
    private void showTaskDetails(Task task) {
        if (task != null) {
            // Rempli les labels avec les infos de l'objet Task.
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
            // Task est null, retirer tout les textes.
        	idLabel.setText("");
        	NameLabel.setText("");
        	studentLabel.setText("");
        	deadlineLabel.setText("");
        	accomplishedLabel.setText("");
        }
    }
    
    /**
     * Si une t�che est s�lectionn� dans le tableau et que l'utilisateur click sur Delete, alors celle-ci est supprim�
     */
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
            // Si rien n'est s�lectionn�.
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
     * Ouvre une interface pour editer les d�tails d'une t�che.
     */
    @FXML
    private void handleNewTask(){
        Task tempTask = new Task();
        Task.setNbrOfTasks(taskTable.getItems().size() + 1);
        
        boolean okClicked = mainApp.showTaskEditDialog(tempTask, mainApp);
        if (okClicked) {
            mainApp.getAllTasks().add(tempTask);
           
        }
    }

    /**
     * Appel� lorsque l'utilisateur clique sur le bouton Edit. 
     * Ouvre une bo�te de dialogue pour modifier le d�tails pour la Task s�lectionn�e.
     */
    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean okClicked = mainApp.showTaskEditDialog(selectedTask, this.mainApp);
            if (okClicked) {
                showTaskDetails(selectedTask);
            }

        } else {
            // Si rien n'est s�lectionn�.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a Task in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Si une t�che est s�lectionn�e dans le tablea et qu'elle n'est pas accomplie, elle devient donc accomplie
     * et Invers�ment si elle l'�tait d�j� ( au cas ou l'utilisateur ferait une mauvaise manip)
     */
    @FXML
    private void handleAccomplishTask() {
    	Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
    	
    	if(selectedTask != null) {
    		if(selectedTask.getAccomplished()) {
    			selectedTask.setAccomplished(false);
    		}else {
    			selectedTask.setAccomplished(true);
    		}
    		showTaskDetails(selectedTask);
    	}else {
    		 //  Si rien n'est s�lectionn�.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a Task in the table.");

            alert.showAndWait();
    	}
    }
    
    /**
     *  affiche 3 lignes de textes dans la zone "ShowDetailsTask" et montre le nombre de jours/mois/ann�e restant d'une t�che.
     */
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
    
    /**
     * Si un Etudiant est s�lectionn� dans le tableau et que l'utilisateur click sur Delete, alors cellui-ci est supprim�.
     */
    @FXML
    private void handleDeleteStudent() {
    	//On regarde quel SelectionModel il a choisi puis on prend l'index, et apr�s cela on remove dans l'arrayList
    	int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
    	
    	//On g�re l'erreur au cas ou l'utilisateur n'a s�lectionn� aucune t�che 
    	if (selectedIndex >= 0) {
    		
    		//Alerte s'ouvre pour que l'utilisateur confirme s'il veut supprimer la t�che
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
            //  Si rien n'est s�lectionn�.
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
     * Ouvre une interface pour editer les d�tails d'un �tudiant.
     */
    @FXML
    private void handleNewstudent(){
    	Student tempStud = new Student();
    	Student.setNbrOfStudents(studentTable.getItems().size() + 1);
    	
        boolean okClicked = mainApp.showStudentEditDialog(tempStud, mainApp);
        
        if (okClicked) {
        	//System.out.println(mainApp.getAllStudents());
            mainApp.getAllStudents().add(tempStud);
            //System.out.println(mainApp.getAllStudents());
        }
    }
    
}
