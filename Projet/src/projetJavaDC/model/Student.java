package projetJavaDC.model;

public class Student{

	private int id;
	private String name;
	
	private static int nbrOfStudents;
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	
	/**
	 * Constructeur par default de etudiant 
	 */
	public Student () {}
	
	
	/**
	 * @param name le nom de l'etudiant 
	 */
	public Student(String name){
		nbrOfStudents ++;
		
		this.id = nbrOfStudents;
		this.name = (name);
		TaskManagement.getAllStudents().add(this);
	}
	
	//------------------------------------GETTERS SETTERS------------------------------------------//
	
	/**
	 * @return l'id de l'etudiant 
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @param id a set 
	 */
	public void setId(int id) {
		this.id = (id);
	}
	
	
	/**
	 * @return le nom de l'etudiant
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * @param name le nom de l'etudiant a set 
	 */
	public void setName(String name) {
		this.name = (name);
	}
	
	//--------------------------------------TO STRING----------------------------------------------//
	
	@Override
	public String toString() {
		return "Student " + id + ", name= " + name + "\n";
	}
	//--------------------------------------METHODES-----------------------------------------------//
	
	/**
	 * @param task la tache à ajouter 
	 * @throws DateTempsRestantInvalideException
	 */
	public void addTask(Task task) throws DateTempsRestantInvalideException{
        task.setStudent(this);
        TaskManagement.getAllTasks().add(task);
       
    }
    
}
