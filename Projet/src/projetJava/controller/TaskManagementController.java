package projetJava.controller;

import java.util.Date;

import projetJava.model.DateTempsRestantInvalideException;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.model.TaskManagement;
import projetJava.view.TaskManagementVue;

public class TaskManagementController {
	
	private TaskManagement model = null;	
	private TaskManagementVue vue = null; 
	
	public TaskManagementController(TaskManagement model) {
		this.model = model;
	}
	
	//------------------------------------Method------------------------------------------------//
	
	
	public void addTask(String taskName, Date deadline, Student stud) throws projetJava.DateTempsRestantInvalideException {
		if(model != null) {
			try {
			Task task = new Task(taskName, deadline); //create task
			
			this.model.addTask(task, stud); //add task to logged student
			
			} 
			catch (DateTempsRestantInvalideException e){
			}
		}
	}

	
	public int timeLeft(int taskId) {
		if(model != null) {
			for(Task task : TaskManagement.getAllTasks()) {
				if(task.getId() == (taskId)) {
					this.model.setCurrentTask(task);
					return this.model.getCurrentTask().timeLeft();
				}
			}
		}
		return -1; //Dans le cas ou la tâche n'a pas été trouvé 
	}
	
	public void accomplishTaskStudent(int id) {
		boolean test = false;
		boolean test2 = false;
		
		if(model != null) {
			for(Task task : TaskManagement.getAllTasks()) {
				if(task.getId() == (id)) {
					test = true;
					if(task.getAccomplished() == false) {
						this.model.setCurrentTask(task);
						model.getCurrentTask().setAccomplished(true);
						test2 = true;
						System.out.println("La tâche " + id + " est désormais accomplie \n");
					}
				}
			}
			if(test) {
				if(!test2) {
					System.out.println("La tâche est déjà acccomplie \n");
				}
			}
			else if(!test){
				System.out.println("La tâche n'existe pas \n");
			}
		}
	}
	
	//Pas utilisé pour le moment
		public boolean login(int id){
			if(model != null) {
				return model.login(id);
			}
			return false;
		}
	
	public void addView(TaskManagementVue vue) {
		this.vue = vue;
	}
	
}
