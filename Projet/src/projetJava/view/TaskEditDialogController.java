/**
 * 
 */
package projetJava.view;

//import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
//import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projetJava.MainApp;
import projetJava.model.Student;
import projetJava.model.Task;


/**
 * @author robin
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
    public void setTask(Task task) {
        this.task = task;

        idField.setText(Integer.toString(task.getId()));
        nameField.setText(task.getName());
        
        if(task.getStudent() == null) {
        	studentField.setText("");
        }else {
        	studentField.setText(task.getStudent().getName());
        }
        
        /*
        deadlineField.setText(task.getDeadline().getDate() + "/" +
				task.getDeadline().getMonth() + "/" +
				task.getDeadline().getYear());
        //deadlineField.setPromptText("dd/mm/yyyy");
          */
         
        
        accomplishedField.setText(Boolean.toString(task.getAccomplished()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {
    		
        	
    		int myId = Integer.parseInt(idField.getText());
    		task.setId(myId);
    		
        	task.setName(nameField.getText());
        	
        	setStudent();
            //setDate();
        	
        	task.setAccomplished(false);
        	
        	okClicked = true;
        	dialogStage.close();
    	}
    }
    
    public void setStudent() {
    	boolean test = false;
    	MainApp tableAcces = new MainApp();
    	System.out.println(studentField.getText());
        for(Student stud : tableAcces.getAllStudents()) {
        	if(studentField.getText().equals(stud.getName())) {
        		task.setStudent(stud);
        		test = true;
        		System.out.println("réussi");
        	}
        }
        if(!test) {
        	System.out.println("raté");
        	task.setStudent(null);
        }
    }
    /*
    public void setDate() {
    	String day = deadlineField.getText().charAt(0) + "" + deadlineField.getText().charAt(1);
    	String month = deadlineField.getText().charAt(3) + "" + deadlineField.getText().charAt(4);
    	String year = deadlineField.getText().charAt(6) + "" + deadlineField.getText().charAt(7) +
    			deadlineField.getText().charAt(8) + "" + deadlineField.getText().charAt(9);
    	
    	Date assignedDate = new Date(Integer.parseInt(year), Integer.parseInt(month) -1, Integer.parseInt(day));
        
        task.setDeadline(assignedDate);
    }
    */
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        /*
        if (deadlineField.getText().length() != 10) {
            errorMessage += "No valid deadline!\n";
        }
		*/
        
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
