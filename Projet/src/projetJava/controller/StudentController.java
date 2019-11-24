package projetJava.controller;

import java.util.Date;

import projetJava.model.DateTempsRestantInvalideException;
import projetJava.model.Student;
import projetJava.model.Task;
import projetJava.view.StudentVue;

public class StudentController {
	
	private Student currentStudent = null; // model = currentStudent
	private Task currentTask = null;
	
	private StudentVue vue = null; 
	
	public StudentController(Student currentStudent) {
		this.currentStudent = currentStudent;
	}
	
	public StudentController(Task currentTask) {
		this.currentTask = currentTask;
	}
	
	//------------------------------------Method------------------------------------------------//
	
	
	public void addTask(String taskName, Date deadline) throws projetJava.DateTempsRestantInvalideException {
		if(currentStudent != null) {
			
			try {
			Task task = new Task(taskName, deadline); //create task
			this.currentStudent.addTask(task); //add task to logged student
			} 
			catch (DateTempsRestantInvalideException e){
			}
		}
	}
	
	//Pas utilisé pour le moment
	public boolean login(int id){
		if(currentStudent != null) {
			return currentStudent.login(id);
		}
		return false;
	}

	public int timeLeft(int taskId) {
		if(currentTask != null) {
			for(Task task : currentTask.getAllTasks()) {
				if(task.getId() == (taskId)) {
					return task.timeLeft();				
				}
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
