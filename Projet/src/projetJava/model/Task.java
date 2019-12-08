package projetJava.model;


import java.time.LocalDate;
//import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task{

	private IntegerProperty id;
	private final StringProperty name; //pourquoi le final
	private ObjectProperty<Student> student;
	private ObjectProperty<LocalDate> deadLine;
	private ObjectProperty<Date> deadline;
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
	/*
	public Task(String name, Date deadLine) throws DateTempsRestantInvalideException{
		nbrOfTasks ++;
		
		Date ajd = new Date();
		 if(ajd.getYear() + 1900 > deadLine.getYear()) {
			 nbrOfTasks --;
	            throw new DateTempsRestantInvalideException("année encodé inférieur à cette année-ci:" + deadLine.getYear());
	        }
	        if(ajd.getYear() + 1900 == deadLine.getYear() && ajd.getMonth() > deadLine.getMonth()) {
	        	nbrOfTasks --;
	            throw new DateTempsRestantInvalideException("mois encodé inférieur à celui de cette année-ci:" + deadLine.getMonth());
	        }
	        if(ajd.getYear() + 1900 == deadLine.getYear() && ajd.getMonth() == deadLine.getMonth() && ajd.getDate() > deadLine.getDate()) {
	        	nbrOfTasks --;
	            throw new DateTempsRestantInvalideException("jours encodé inférieur à celui de cette année-ci:" + deadLine.getDate());
	        }
	        
	        this.name = new SimpleStringProperty(name);
	        this.id = new SimpleIntegerProperty(nbrOfTasks);
	        this.student =  new SimpleObjectProperty<Student>(null);
	        this.deadline = new SimpleObjectProperty<Date>(new Date(2019,12,29));
	        this.accomplished = new SimpleBooleanProperty(false);
	        
	        
	}
	*/

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
	
	public Date getDeadline() {
		return deadline.get();
	}
	
	public void setDeadline(Date deadline) {
		this.deadline.set(deadline);
	}
	
	public ObjectProperty<Date> dateProperty(){
		return deadline;
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
		/*
		String date = this.deadline.getDate() + "-" + 
					  this.deadline.getMonth()+ "-" +
					  this.deadline.getYear();
				*/	  
		return "Task " + getId() + ": " + name + "\t student= " + 
					  student + 
					  //"\t deadline= " + date +
					  " \tAccomplie: " + this.getAccomplished() + "\n";
	}

	//--------------------------------------METHODES-----------------------------------------------//
	
	 /**
     * @return result : int, le nombre de jour entre aujourd'hui et la date limite de la tache traitée
     *
    public int timeLeft() {
		//return DAYS.numberOfDays(LocalDate.now(), this.deadLine);
    	
    	
    	Calendar cal = Calendar.getInstance(); // Date de ce jour-ci
		
		int jourActuel = cal.get(Calendar.YEAR);
		int moisActuel = cal.get(Calendar.MONTH);
		int anneeActuel = cal.get(Calendar.DATE);
		
		Date today = new Date(jourActuel, moisActuel, anneeActuel); // Pour avoir le même format
		
		long dateToCalculate = this.getDeadline().getTime();
		long difference = dateToCalculate - today.getTime();
		int result = (int)(difference/(1000*60*60*24));
		
		return result;
		
    }*/
}
