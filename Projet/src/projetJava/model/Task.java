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


public class Task{

	private IntegerProperty id;
	private StringProperty name;
	private ObjectProperty<Student> student;
	private ObjectProperty<LocalDate> deadLine;
	private BooleanProperty accomplished;
	
	private static int nbrOfTasks;
	
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Task() {
		this(null);
		
	}
	public Task(String name) {
		nbrOfTasks ++;
		this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(nbrOfTasks); 
        this.accomplished = new SimpleBooleanProperty(false);
        this.student = new SimpleObjectProperty<Student>(null);
        this.deadLine = new SimpleObjectProperty<LocalDate>(null);
	}
	
	public Task(String name, LocalDate deadLine) {
		nbrOfTasks ++;
		this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(nbrOfTasks); 
        this.accomplished = new SimpleBooleanProperty(false);
        this.student = new SimpleObjectProperty<Student>(null);
        this.deadLine = new SimpleObjectProperty<LocalDate>(deadLine);
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
	
	public Student getStudent() {
		return student.get();
	}
	
	public void setStudent(Student student) {
		this.student.set(student);
	}
	
	public ObjectProperty<Student> studentProperty(){
		return student;
	}
	
	public LocalDate getDeadLine() {
		return deadLine.get();
	}
	
	public void setDeadLine(LocalDate deadline) {
		this.deadLine.set(deadline);
	}
	
	public ObjectProperty<LocalDate> localDateProperty(){
		return deadLine;
	}
	
	public boolean getAccomplished() {
		return this.accomplished.get();
	}
	
	public void setAccomplished(boolean accomplished) {
		this.accomplished.set(accomplished);
	}
	
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
