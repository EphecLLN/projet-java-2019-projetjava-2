package projetJava.view;

import java.util.Observer;
import projetJava.controller.TaskManagementController;
import projetJava.model.TaskManagement;

public abstract class TaskManagementVue implements Observer{
	
	protected TaskManagement model;
	
	protected TaskManagementController controller;
	
	public TaskManagementVue(TaskManagement model, TaskManagementController controller){
		this.model = model;
		this.controller = controller;
		
		//connexion entre la vue et le modele
		this.model.addObserver(this);
	}
	
	public abstract void affiche(String string);
	
	public abstract void enableWarning();
    public abstract void disableWarning();
}
