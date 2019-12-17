package projetJavaDC.controller;

import java.util.Date;

import projetJavaDC.model.DateTempsRestantInvalideException;
import projetJavaDC.model.Student;
import projetJavaDC.model.Task;
import projetJavaDC.model.TaskManagement;
import projetJavaDC.view.TaskManagementVue;

/**
 * @author vic91
 *
 */
public class TaskManagementController {
	
	private TaskManagement model = null;	
	private TaskManagementVue vue = null; 
	
	public TaskManagementController(TaskManagement model) {
		this.model = model;
	}
	
	//------------------------------------Method------------------------------------------------//
	
	
	/**
	 * @param taskName le nom de la tâche 
	 * @param deadline la date limite pour effectuer la tache 
	 * @param stud l'etudiant 
	 * @throws projetJavaDC.DateTempsRestantInvalideException
	 */
	public void addTask(String taskName, Date deadline, Student stud) throws projetJavaDC.DateTempsRestantInvalideException {
		if(model != null) {
			try {
			Task task = new Task(taskName, deadline); //create task
			
			this.model.addTask(task, stud); //add task to logged student
			
			} 
			catch (DateTempsRestantInvalideException e){
			}
		}
	}

	
	/**
	 * @param taskId
	 * @return le temps restant sur base de l'id tache 
	 * @return -1 Dans le cas ou la tâche n'a pas été trouvé 
	 */
	public int timeLeft(int taskId) {
		if(model != null) {
			for(Task task : TaskManagement.getAllTasks()) {
				if(task.getId() == (taskId)) {
					this.model.setCurrentTask(task);
					return this.model.getCurrentTask().timeLeft();
				}
			}
		}
		return -1;
	}
	
	
	/**
	 * @param id l'id de la tache à accomplir 
	 */
	public void accomplishTaskStudent(int id) {
		boolean test = false;
		boolean test2 = false;
		
		if(model != null) {
			for(Task task : TaskManagement.getAllTasks()) {
				if(task.getId() == (id)) {
					test = true;
					if(task.getAccomplished() == false) {
						this.model.setCurrentTask(task);
						model.getCurrentTask().setAccomplished(true);
						test2 = true;
						System.out.println("La tâche " + id + " est désormais accomplie \n");
					}
				}
			}
			if(test) {
				if(!test2) {
					System.out.println("La tâche est déjà acccomplie \n");
				}
			}
			else if(!test){
				System.out.println("La tâche n'existe pas \n");
			}
		}
	}
	
	
		/**
		 * !!Cette methode n'est pas effective!!
		 * @param id l'id de login 
		 * @return false si la connexion n'a pas pu se faire 
		 */
		public boolean login(int id){
			if(model != null) {
				return model.login(id);
			}
			return false;
		}
	
	/**
	 * @param vue la vue de taskManagement
	 */
	public void addView(TaskManagementVue vue) {
		this.vue = vue;
	}
	
}
