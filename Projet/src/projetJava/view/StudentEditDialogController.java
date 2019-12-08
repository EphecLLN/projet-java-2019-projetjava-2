package projetJava.view;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import projetJava.model.Student;


public class StudentEditDialogController {

	@FXML
	private TextField nameField;
	
	private Stage dialogStage;
	private Student student;
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
     * Mettre l'étudiant qui va être éditer dans le dialogue
     * 
     * @param Student
     */
    public void setStudent(Student student) {
    	this.student = student;
    	
    	nameField.setText(student.getName());
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
    	if(isInputValid()) {
    		student.setName(nameField.getText());
    		
    		okClicked = true;
        	dialogStage.close();
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


















