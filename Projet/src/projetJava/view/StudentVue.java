package projetJava.view;

import java.util.Observer;

import projetJava.controller.StudentController;
import projetJava.model.Student;

public abstract class StudentVue implements Observer{
	
	protected Student model;
	protected StudentController controller;
	
	StudentVue(Student model, StudentController controller){
		this.model = model;
		this.controller = controller;
		
		//connexion entre la vue et le modele
		this.model.addObserver(this);
	}
	
	public abstract void affiche(String string);
	
	public abstract void enableWarning();
    public abstract void disableWarning();
}
