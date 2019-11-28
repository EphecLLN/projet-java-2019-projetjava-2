package projetJava.view;

import java.util.Observer;

import projetJava.controller.StudentController;
import projetJava.model.Student;
import projetJava.model.Task;

public abstract class StudentVue implements Observer{
	
	protected Student model;
	protected Task modelTask;
	
	protected StudentController controller;
	protected StudentController controllerTask;
	
	StudentVue(Student model, StudentController controller, Task modelTask, StudentController controllerTask){
		this.model = model;
		this.controller = controller;
		
		this.modelTask = modelTask;
		this.controllerTask = controllerTask;
		
		//connexion entre la vue et le modele
		this.modelTask.addObserver(this);
		this.model.addObserver(this);
	}
	
	public abstract void affiche(String string);
	
	public abstract void enableWarning();
    public abstract void disableWarning();
}
