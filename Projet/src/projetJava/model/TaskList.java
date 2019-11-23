package projetJava.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import projetJava.DateTempsRestantInvalideException;

public class TaskList extends Observable{
	/*
	 * Nous avons opt� pour une ArrayList() dans notre programme car nous effectuons beaucoup de lecture sans
	 * se soucier de l'ordre des �l�ments.
	 */
	private List<Task> tasks = new ArrayList<>();
	
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public TaskList() {
		
	}

	//------------------------------------GETTERS SETTERS------------------------------------------//
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		return "TaskList [tasks=" + tasks + "]";
	}
	
	
	
	//--------------------------------------METHODES-----------------------------------------------//
	
	
	/**
	 * @param name : nom de la t�che que l'on veut ajouter
	 * @param student : Etudiant � qui on attibue la t�che
	 * @param deadLine : date limite pour effectuer cette t�che
	 * @throws DateTempsRestantInvalideException
	 */
	public void addTask(String name, Student student, Date deadLine) throws DateTempsRestantInvalideException{
		
				//------------------------VERIFIE INPUT DATE VALIDE----------------------------//
		
		Date ajd = new Date();
		boolean test = true;
		
		if(ajd.getYear() + 1900 > deadLine.getYear()) {
			test = false;
			throw new DateTempsRestantInvalideException("ann�e encod� inf�rieur � cette ann�e-ci:" 
					+ deadLine.getYear());
		}
		if(ajd.getYear() + 1900 == deadLine.getYear() && ajd.getMonth() > deadLine.getMonth()) {
			test = false;
			throw new DateTempsRestantInvalideException("mois encod� inf�rieur � celui de cette ann�e-ci:" 
					+ deadLine.getMonth());
		}
		if(ajd.getYear() + 1900 == deadLine.getYear() && ajd.getMonth() == deadLine.getMonth() &&
				ajd.getDate() > deadLine.getDate()) {
			test = false;
			throw new DateTempsRestantInvalideException("jours encod� inf�rieur � celui de cette ann�e-ci:" 
					+ deadLine.getDate());
		}
				//---------------------FIN DE VERIFIE INPUT DATE VALIDE-----------------------//
	
		
		if(test) {
			Task task = new Task (name, student, deadLine);
			this.getTasks().add(task);
			
			this.setChanged();
	        this.notifyObservers();
		}
	}
	
	
	
	/**
	 * @param taskName : nom de la t�che que l'on traite
	 * @return result : int, le nombre de jour entre aujourd'hui et la date limite de la tache trait�e
	 */
	public int timeLeft(String taskName) {
		Calendar cal = Calendar.getInstance(); // Date de ce jour-ci
		
		int jourActuel = cal.get(Calendar.YEAR);
		int moisActuel = cal.get(Calendar.MONTH);
		int anneeActuel = cal.get(Calendar.DATE);
		
		Date today = new Date(jourActuel, moisActuel, anneeActuel); // Pour avoir le m�me format
		
		long dateToCalculate = 0;
		
		boolean test = false;
		for(Task t : this.getTasks()) {
			if(t.getName() == taskName) {
				test = true;
				dateToCalculate = t.getDeadline().getTime();
			}
		}
		if(test) {
			long difference = dateToCalculate - today.getTime();
			int result = (int)(difference/(1000*60*60*24));
			
			return result;
		}
		
		return 0;
	}
	
	
	/**
	 * @param taskName : nom de la t�che � v�rifier
	 * @return true si la t�che est accomplie et false sinon
	 */
	public Boolean checkIfTaskIsAccomplished(String taskName) {
		for(Task t : this.getTasks()) {
			if(t.getName() == taskName) {
				return t.getAccomplished();
			}
		}
		return false;
	}
	
	
	/**
	 * Si la t�che recherch� existe et qu'elle n'est pas accomplie, alors celle-ci est mise � 
	 * "true" pour le setAccomplished();
	 *
	 * @param taskName : nom de la t�che � v�rifier
	 * 
	 */
	public void accomplishTask(String taskName) {
		for(int i = 0; i < this.getTasks().size(); i ++) {
			if(this.getTasks().get(i).getName() == taskName) {
				this.getTasks().get(i).setAccomplished(true);
				
				this.setChanged();
		        this.notifyObservers();
			}
		}
	}
}
