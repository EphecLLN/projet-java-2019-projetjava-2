package projetJavaDC.model;

public class Student{

	private int id;
	private String name;
	
	private static int nbrOfStudents;
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Student () {}
	public Student(String name){
		nbrOfStudents ++;
		
		this.id = nbrOfStudents;
		this.name = (name);
		TaskManagement.getAllStudents().add(this);
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
		this.name = (name);
	}
	
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		return "Student " + id + ", name= " + name + "\n";
	}
	//--------------------------------------METHODES-----------------------------------------------//
	
	public void addTask(Task task) throws DateTempsRestantInvalideException{
        task.setStudent(this);
        TaskManagement.getAllTasks().add(task);
       
    }
    
}
