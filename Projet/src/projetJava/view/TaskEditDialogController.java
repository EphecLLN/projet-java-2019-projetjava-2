/**
 * 
 */
package projetJava.view;


import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projetJava.MainApp;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.util.DateUtil;


/**
 * @author robin & Victor
 *
 */
public class TaskEditDialogController {
	@FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField studentField;
    @FXML
    private TextField deadlineField;
    
    @FXML
    private TextField accomplishedField;

    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;
    private MainApp mainApp;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the Task to be edited in the dialog.
     *
     * @param Task
     */
    public void setTask(Task task, MainApp mainApp) {
        this.task = task;
        //System.out.println(mainApp.getAllTasks());
        this.mainApp = mainApp;
        
        idField.setEditable(false);
        
        if(task.getName() == null) {
        	idField.setText(Integer.toString(Task.getNbrOfTasks()));
        	nameField.setPromptText("Name...");
        }else {
        	idField.setText(Integer.toString(task.getId()));
        	nameField.setText(task.getName());
        }
        if(task.getStudent() == null) {
        	studentField.setPromptText("Unknow Student");
        }else {
        	studentField.setText(task.getStudent().getName());
        }
        if(task.getDeadLine() == null) {
        	deadlineField.setPromptText("dd/mm/yyyy");
        }else {
        	deadlineField.setText(DateUtil.format(task.getDeadLine()));
        	//deadlineField.setPromptText("dd/mm/yyyy");
        }
        accomplishedField.setEditable(false);
        accomplishedField.setText(Boolean.toString(task.getAccomplished()));
    }

    /**
     * Renvoie true si l'utilisateur click sur Ok, false sinon.
     *
     * @return true si l'utilisateur click sur Ok, false sinon.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Appelé quand l'utilisateur click sur OK
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {
    		
    		if(task.getName() == null) {
            	task.setId(Task.getNbrOfTasks());
            }else {
            	System.out.println("");
            }
    		int myId = Integer.parseInt(idField.getText());
    		task.setId(myId);
    		
        	task.setName(nameField.getText());
        	
        	setStudent();
        	task.setDeadLine(DateUtil.parse(deadlineField.getText()));
        	
        	task.setAccomplished(false);
        	
        	okClicked = true;
        	dialogStage.close();
    	}
    }
    
    public void setStudent() {
    	boolean test = false;
    	
        for(Student stud : mainApp.getAllStudents()) {
        	if(studentField.getText().equals(stud.getName())) {
        		task.setStudent(stud);
        		test = true;
        	}
        }
        if(!test) {
        	task.setStudent(null);
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Valide les "input" inséré par l'utilisateur
     *
     * @return true Si les input sont valide
     */
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        
        boolean testDateValide = true;
        
        if(deadlineField.getText() == null || deadlineField.getText().length() == 0) {
        	errorMessage += "No valid deadline!\n";
        	testDateValide = false;
        }else {
        	if(!DateUtil.validDate(deadlineField.getText())) {
        		errorMessage += "No valid deadline. Use the format dd/mm/yyyy!\n";
        		testDateValide = false;
        	}
        }
        
        if(testDateValide) {
        	if(DateUtil.parse(deadlineField.getText()).compareTo(LocalDate.now()) < 0) {
        		errorMessage += "No valid deadline. Date lower than today!\n";
        	}
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	// Montrer le message d'erreur
        	Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
