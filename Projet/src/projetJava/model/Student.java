package projetJava.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student{

	private IntegerProperty id;
	private StringProperty name;
	
	//private static int nbrOfStudents;
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Student () {}
	public Student(String name){
		//nbrOfStudents ++;
		
		this.id = new SimpleIntegerProperty(1);//nbOfStudents
		this.name = new SimpleStringProperty(name);
		TaskManagement.getAllStudents().add(this);
	}
	
	//------------------------------------GETTERS SETTERS------------------------------------------//
	public int getId() {
		return this.id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public IntegerProperty idProperty() {
		return this.id;
	}
	
	public String getName() {
		return this.name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return this.name;
	}
	
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		return "Student " + id.intValue() + ", name= " + name.getValue() + "\n";
	}
	//--------------------------------------METHODES-----------------------------------------------//
	
	public void addTask(Task task) throws DateTempsRestantInvalideException{
        task.setStudent(this);
        TaskManagement.getAllTasks().add(task);
       
    }
    
}
