/**
 * 
 */
package projetJava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import projetJava.DateTempsRestantInvalideException;

/**
 * @author robin
 *
 */
public class TaskManagement extends Observable {

	private static List<Student> allStudents = new ArrayList<>();
	private static List<Task> allTasks = new ArrayList<>();
	
	private Student currentStudent;
	private Task currentTask;
	
	
	//-------------------------------------CONSTRUCTEURS----------------------------------------------------//
	public TaskManagement() {
		Student student1 = new Student("Victor"); 
        Student student2 = new Student ("Florent");
        Student student3 = new Student("Julien");
        Student student4 = new Student("Robin");
		
        try {
			Task task = new Task("Faire le ménage", new Date(2019, 11, 25)); //1
			Task task1 = new Task("Faire la vaiselle", new Date(2019, 11, 26)); //2
            Task task2 = new Task("Passer l'aspirateur", new Date(2019, 11, 27)); //3
            Task task3 = new Task("Ranger le commu", new Date(2019, 11, 26)); //4
            try {
				student1.addTask(task);
				student2.addTask(task1);
				student3.addTask(task2);
				student3.addTask(task3);
			} catch (DateTempsRestantInvalideException e) {		}
            
            
		} catch (projetJava.model.DateTempsRestantInvalideException e) {	}
	}
	public TaskManagement(Student currentStudent) {
		this.currentStudent = currentStudent;
	}
	
	//----------------------------------GETTERS && SETTERS-------------------------------------------------//
	public static List<Student> getAllStudents(){
    	return allStudents;
    }
	public static List<Task> getAllTasks(){
    	return allTasks;
    }
	public Task getCurrentTask() {
		return this.currentTask;
	}
	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	public String getAllStudentsList() {
    	String result = "";
    	for(Student student : allStudents) {
    		result += "Etu " + student.getId() + " : " + student.getName() + "\n";
    	}
    	return result;
    }
	
	public String getAllTasksList() {
    	String result = "";
    	for(Task task : allTasks) {
    		String date = task.getDeadline().getDate() + "-" + 
    				(task.getDeadline().getMonth()+ 1) + "-" +
    				task.getDeadline().getYear();
    		
    		result += "Tâche " + task.getId() + " : " + 
    				task.getName() + "\t etu : " + 
    				task.getStudent().getName() + " (id : "+ task.getStudent().getId() + ")\t pour le : " +
    				date + "\t Accomplie: " + task.getAccomplished() + "\n";
    	}
    	return result;
    }
	
	//-------------------------------------METHODE------------------------------------------------------//
	
	public void addTask(Task task, Student stud) throws DateTempsRestantInvalideException {
		task.setStudent(stud);
		getAllTasks().add(task);
		//currentStudent.addTask(task);
		this.setChanged();
        this.notifyObservers();
	}
	public void timeLeft() {
		currentTask.timeLeft();
	}
	public boolean getAccomplished() {
		return currentTask.getAccomplished();
	}
	public void setAccomplished(boolean accomplished) {
		currentTask.setAccomplished(accomplished);
		//this.setChanged();
        //this.notifyObservers();
	}
	//Pas utilisé pour le moment
	public boolean login(int id) {
		for(Student student : TaskManagement.getAllStudents()) {
            if(student.getId() == (id)) { // use equals(id)
                return true;
            }
        }
		return false;
	}
}
