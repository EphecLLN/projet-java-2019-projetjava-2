package projetJava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import projetJava.DateTempsRestantInvalideException;



public class Student extends Observable{

	private int id;
	private String name;
	private List<Task> taskList = new ArrayList<>();
	
	private static int nbrOfStudents;
	
	public static List<Student> allStudents = new ArrayList<>();
	public static List<Task> allTasks = new ArrayList<>();
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Student(String name){
		nbrOfStudents ++;
		
		this.id = nbrOfStudents;
		this.name = name;
		
		allStudents.add(this);
	}
	
	//------------------------------------GETTERS SETTERS------------------------------------------//
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Task> getTaskList() {
        return this.taskList;
    }
    
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    public String getAllTasks() {
    	String result = "";
		for(Task t : allTasks) {
			result +=  t;
		}
    	return result;
    }
    
    public String getAllStudents() {
    	String result = "";
		for(Student t : allStudents) {
			result +=  t;
		}
    	return result;
    }
	//--------------------------------------TO STRING----------------------------------------------//
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "] \n";
	}

	//--------------------------------------METHODES-----------------------------------------------//
	public void addTask(Task task) throws DateTempsRestantInvalideException{
        task.setStudent(this);
        this.getTaskList().add(task);
        
        allTasks.add(task);
        
        this.setChanged();
        this.notifyObservers();
    }
	public boolean login(int id) {
		for(Student student : allStudents) {
            if(student.getId() == (id)) { // use equals(id)
                return true;
            }
        }
		return false;
	}

	public static void main(String[] args) {
        Student student1 = new Student("Robin");
        Student student2 = new Student("Victor");       
        
		try {
			Task task = new Task("Faire le ménage", new Date(2019, 10, 25));
			Task task1 = new Task("Faire la vaiselle", new Date(2019, 10, 26)); //2
            Task task2 = new Task("passer l'aspirateur", new Date(2019, 10, 27)); //3
            
            allTasks.add(task);
            allTasks.add(task1);
            allTasks.add(task2); 
            
            task.setStudent(student1);
            task1.setStudent(student1);
            task2.setStudent(student1);
            
            System.out.println(student1.getAllTasks());
            System.out.println(student1.getAllStudents());
            
		} catch (projetJava.model.DateTempsRestantInvalideException e) {}
		
		
    }
}
