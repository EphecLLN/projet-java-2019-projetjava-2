package projetJava.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projetJava.model.DateTempsRestantInvalideException;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.StudentVue;

public class StudentController {
	
	private Student model = null; // model = currentStudent
	private StudentVue vue = null; 
	
	public StudentController(Student model) {
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
	
	public boolean login(int id){
		if(model != null) {
			return model.login(id);
		}
		return false;
	}

	public int timeLeft(int taskId) {
		if(model != null) {
			for(Task task : model.getTaskList()) {
				if(task.getId() == (taskId)) {
					return task.timeLeft();				}
			}
		}
		return 0;
	}
	
	public Boolean checkIfTaskIsAccomplishedStudent(String taskName) { 
		return true; 
	}
	
	public void accomplishTaskStudent(String taskName) {
		
	}
	
	public void addView(StudentVue vue) {
		this.vue = vue;
	}
}
