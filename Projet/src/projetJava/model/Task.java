package projetJava.model;

import java.util.Date;



public class Task {

	private int id;
	private String name;
	private Student student;
	private Date deadline;
	private boolean accomplished;
	
	private static int nbrOfTasks;
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Task() {
		
	}
	public Task(String name, Student student, Date deadLine) {
		nbrOfTasks ++;
		
		this.name = name;
		this.id = nbrOfTasks;
		this.student = student;
		this.deadline = deadLine;
		this.accomplished = false;
	}
	
	
	//------------------------------------GETTERS SETTERS------------------------------------------//
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Date getDeadline() {
		return this.deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public boolean getAccomplished() {
		return this.accomplished;
	}
	
	public void setAccomplished(boolean accomplished) {
		this.accomplished = accomplished;
	}
	
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		String date = this.deadline.getDate() + "-" + 
					  this.deadline.getMonth()+ "-" +
					  this.deadline.getYear();
		return "Task [name=" + name + ", student=" + student.getName() + ", deadline=" + date + "]";
	}

	//--------------------------------------METHODES-----------------------------------------------//
	
}
