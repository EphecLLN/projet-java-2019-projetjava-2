package projetJavaDC.model;

import java.util.Calendar;
import java.util.Date;

public class Task{

	private int id;
	private String name; 
	private Student student;
	private Date deadline;
	private boolean accomplished;
	
	private static int nbrOfTasks;
	

	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Task() {}
	
	public Task(String name, Date deadLine) throws DateTempsRestantInvalideException{
		nbrOfTasks ++;
		
		Date ajd = new Date();
		 if(ajd.getYear() + 1900 > deadLine.getYear()) {
			 nbrOfTasks --;
	            throw new DateTempsRestantInvalideException("ann�e encod� inf�rieur � cette ann�e-ci:" + deadLine.getYear());
	        }
	        if(ajd.getYear() + 1900 == deadLine.getYear() && ajd.getMonth() > deadLine.getMonth()) {
	        	nbrOfTasks --;
	            throw new DateTempsRestantInvalideException("mois encod� inf�rieur � celui de cette ann�e-ci:" + deadLine.getMonth());
	        }
	        if(ajd.getYear() + 1900 == deadLine.getYear() && ajd.getMonth() == deadLine.getMonth() && ajd.getDate() > deadLine.getDate()) {
	        	nbrOfTasks --;
	            throw new DateTempsRestantInvalideException("jours encod� inf�rieur � celui de cette ann�e-ci:" + deadLine.getDate());
	        }
		this.name = (name);
	    this.id = (nbrOfTasks); 
	    this.accomplished = (false);
	    this.student = (null);
	    this.deadline = (deadLine);
	    this.accomplished = (false);  
	}
	

	//------------------------------------GETTERS SETTERS------------------------------------------//
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = (id);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name= (name);
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = (student);
	}
	
	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline= (deadline);
	}
	
	public boolean getAccomplished() {
		return this.accomplished;
	}
	
	public void setAccomplished(boolean accomplished) {
		this.accomplished = (accomplished);
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
		
		String date = this.deadline.getDate() + "-" + 
					  this.deadline.getMonth()+ "-" +
					  this.deadline.getYear();
					  
		return "Task " + getId() + ": " + name + 
				"\t student= " + student + 
				"\t deadline= " + date +
				" \tAccomplie: " + this.getAccomplished() + "\n";
	}

	//--------------------------------------METHODES-----------------------------------------------//
	
	 /**
     * @return result : int, le nombre de jour entre aujourd'hui et la date limite de la tache trait�e
     */
    public int timeLeft() {
		//return DAYS.numberOfDays(LocalDate.now(), this.deadline);
    	
    	
    	Calendar cal = Calendar.getInstance(); // Date de ce jour-ci
		
		int jourActuel = cal.get(Calendar.YEAR);
		int moisActuel = cal.get(Calendar.MONTH);
		int anneeActuel = cal.get(Calendar.DATE);
		
		Date today = new Date(jourActuel, moisActuel, anneeActuel); // Pour avoir le m�me format
		
		long dateToCalculate = this.getDeadline().getTime();
		long difference = dateToCalculate - today.getTime();
		int result = (int)(difference/(1000*60*60*24));
		
		return result;
		
    }
}
