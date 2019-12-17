package projetJava.model;

import java.time.LocalDate;
import java.time.Period;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;


public class Task implements Comparable<Task>{

	private IntegerProperty id;
	private StringProperty name;
	private ObjectProperty<Student> student;
	private ObjectProperty<LocalDate> deadLine;
	private BooleanProperty accomplished;
	
	private static int nbrOfTasks;
	
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	/**
	 * constructeur par défaut
	 */
	public Task() {
		this(null);
		
	}
	/**
	 * @param name, the name of the task
	 */
	public Task(String name) {
		nbrOfTasks ++;
		this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(nbrOfTasks); 
        this.accomplished = new SimpleBooleanProperty(false);
        this.student = new SimpleObjectProperty<Student>(null);
        this.deadLine = new SimpleObjectProperty<LocalDate>(null);
	}
	
	/**
	 * @param name: String the name of the taks
	 * @param deadLine : LocalDate the deadline of the date
	 */
	public Task(String name, LocalDate deadLine) {
		nbrOfTasks ++;
		this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(nbrOfTasks); 
        this.accomplished = new SimpleBooleanProperty(false);
        this.student = new SimpleObjectProperty<Student>(null);
        this.deadLine = new SimpleObjectProperty<LocalDate>(deadLine);
	}

	//------------------------------------GETTERS SETTERS------------------------------------------//
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id.get();
	}
	
	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id.set(id);
	}
	
	/**
	 * @return the Property Id
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
	 * @param name
	 */
	public void setName(String name) {
		this.name.set(name);
	}
	
	/**
	 * @return return the PropertyName
	 */
	public StringProperty nameProperty() {
		return this.name;
	}
	
	/**
	 * @return the Student
	 */
	public Student getStudent() {
		return student.get();
	}
	
	/**
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student.set(student);
	}
	
	/**
	 * @return the propertyStudent
	 */
	public ObjectProperty<Student> studentProperty(){
		return student;
	}
	
	/**
	 * @return the deadLine
	 */
	public LocalDate getDeadLine() {
		return deadLine.get();
	}
	
	/**
	 * @param deadline
	 */
	public void setDeadLine(LocalDate deadline) {
		this.deadLine.set(deadline);
	}
	
	/**
	 * @return the localDateProperty
	 */
	public ObjectProperty<LocalDate> localDateProperty(){
		return deadLine;
	}
	
	/**
	 * @return true if the task is accomplished, else false 
	 */
	public boolean getAccomplished() {
		return this.accomplished.get();
	}
	
	/**
	 * @param accomplished
	 */
	public void setAccomplished(boolean accomplished) {
		this.accomplished.set(accomplished);
	}
	
	/**
	 * @return true if the task is accomplished, else false
	 */
	public BooleanProperty accomplishedProperty() {
		return this.accomplished;
	}
	
	/**
	 * @return the nbrOfTasks
	 */
	public static int getNbrOfTasks() {
		return nbrOfTasks;
	}
	
	/**
	 * @param nbrOfTasks the nbrOfTasks to set
	 */
	public static void setNbrOfTasks(int nbrOfTasks) {
		Task.nbrOfTasks = nbrOfTasks;
	}
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {  
		return "Task " + getId() + ": " + name + 
				"\t student= " + getStudent().getName() + 
				"\t deadline= " + this.getDeadLine() +
				"\t Accomplish= " + this.getAccomplished() + "\n";
	}

	@Override 
	public int compareTo(Task task) {
		return this.getDeadLine().compareTo(task.getDeadLine());
	}
	//--------------------------------------METHODES-----------------------------------------------//
	
	 /**
     * @return result : int, le nombre de jour entre aujourd'hui et la date limite de la tache traitée
     */
    public int timeLeft() {
    	Period intervalPeriod = Period.between(LocalDate.now(), this.getDeadLine());
    	
    	int daysLeft = intervalPeriod.getDays() + (intervalPeriod.getMonths() * 30) + (intervalPeriod.getYears() * 365);
    	
    	return daysLeft;
		
    }
    
}
