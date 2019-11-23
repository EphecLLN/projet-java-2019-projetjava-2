package projetJava.model;

import java.util.Date;
import projetJava.DateTempsRestantInvalideException;



public class Student {

	private int id;
	private String name;
	private TaskList taskList;
	
	
	private static int nbrOfStudents;
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	Student(String name){
		nbrOfStudents ++;
		
		this.id = nbrOfStudents;
		this.name = name;
		this.taskList = new TaskList();
	}
	
	//------------------------------------GETTERS SETTERS------------------------------------------//
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TaskList getTaskList() {
		return this.taskList;
	}
	
	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}
	
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

	//--------------------------------------METHODES-----------------------------------------------//
	
	
	//---------------------------------------MAIN-----------s---------------------------------------//
	public static void main(String[] args) {

		Student student = new Student("Robin");
		
		
		try {
			student.taskList.addTask("Faire le ménage", student, new Date(2019, 10, 25));
		}
		catch (DateTempsRestantInvalideException e){}
		
		System.out.println(student.taskList.checkIfTaskIsAccomplished("Faire le ménage"));
		student.taskList.accomplishTask("Faire le ménage");
		System.out.println(student.taskList.checkIfTaskIsAccomplished("Faire le ménage"));

	}

}
