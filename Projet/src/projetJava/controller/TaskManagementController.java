package projetJava.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	
	public void addTask(String taskName, Date deadline) throws projetJava.DateTempsRestantInvalideException {
		if(model != null) {
			
			try {
			Task task = new Task(taskName, deadline); //create task
			this.model.addTask(task); //add task to logged student
			
			} 
			catch (DateTempsRestantInvalideException e){
			}
		}
	}

	public int timeLeft(int taskId) {
		if(model != null) {
			for(Task task : model.getAllTasks()) {
				if(task.getId() == (taskId)) {
					this.model.setCurrentTask(task);
					return this.model.getCurrentTask().timeLeft();
				}
			}
		}
		return -1;
	}
	
	public Boolean checkIfTaskIsAccomplishedStudent(int id) { 
		return true; 
	}
	
	public void accomplishTaskStudent(int id) {
		boolean test = false;
		boolean test2 = false;
		
		if(model != null) {
			for(Task task : model.getAllTasks()) {
				if(task.getId() == (id)) {
					test = true;
					if(task.getAccomplished() == false) {
						
						//task.setAccomplished(true);
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
	/*
	//Pas utilisé pour le moment
		public boolean login(int id){
			if(model != null) {
				return model.login(id);
			}
			return false;
		}
	*/
	public void addView(TaskManagementVue vue) {
		this.vue = vue;
	}
	
}
