package projetJava.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student{

	private IntegerProperty id;
	private StringProperty name;
	
	private static int nbrOfStudents;
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	/**
	 * the default constructor
	 */
	public Student () { 
		this(null); 
	}
	/**
	 * @param name the name of the Student
	 */
	public Student(String name){
		nbrOfStudents ++;
		
		this.id = new SimpleIntegerProperty(getNbrOfStudents());
		this.name = new SimpleStringProperty(name);
	}
	
	//------------------------------------GETTERS SETTERS------------------------------------------//
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id.get();
	}
	
	/**
	 * @param id the id of Student to set
	 */
	public void setId(int id) {
		this.id.set(id);
	}
	
	/**
	 * @return the id IntegerProperty 
	 */
	public IntegerProperty idProperty() {
		return this.id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name.get();
	}
	
	/**
	 * @param name the name of Student to set
	 */
	public void setName(String name) {
		this.name.set(name);
	}
	
	/**
	 * @return the name StringProperty
	 */
	public StringProperty nameProperty() {
		return this.name;
	}
	
	/**
	 * @return the nbrOfStudents
	 */
	public static int getNbrOfStudents() {
		return nbrOfStudents;
	}
	/**
	 * @param nbrOfStudents the number Of Students to set
	 */
	public static void setNbrOfStudents(int nbrOfStudents) {
		Student.nbrOfStudents = nbrOfStudents;
	}
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		return "Student " + id.intValue() + ", name= " + name.getValue() + "\n";
	}
	
}
