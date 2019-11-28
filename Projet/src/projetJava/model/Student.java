package projetJava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import projetJava.DateTempsRestantInvalideException;



public class Student extends Observable{

	private int id;
	private String name;
	private List<Task> taskList = new ArrayList<>();
	
	private static int nbrOfStudents;
	
	public static List<Student> allStudents = new ArrayList<>();
	
	
	//--------------------------------------CONSTRUCTEURS------------------------------------------//
	public Student () {}
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
    
    public List<Student> getAllStudents(){
    	return this.allStudents;
    }
    
    public String getAllStudentsList() {
    	String result = "";
    	for(Student student : allStudents) {
    		result += "Etu " + student.getId() + " : " + student.getName() + "\n";
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
        
        this.setChanged();
        this.notifyObservers();
        
    }
	
	
	//Pas utilisé pour le moment
	public boolean login(int id) {
		for(Student student : allStudents) {
            if(student.getId() == (id)) { // use equals(id)
                return true;
            }
        }
		return false;
	}
	
	
	public static void main(String[] args) {
	
		Student student = new Student("robin");
		
	
		try {
			Task task1 = new Task("Faire le ménage", new Date(2019, 11, 29));
			Task task2 = new Task("Faire la vaisselle", new Date(2019, 11, 29));
			Task task3 = new Task("Passer l'aspirateur", new Date(2019, 11, 29));
			Task task4 = new Task("Ranger le commu", new Date(2019, 11, 29));
			
			try {
				student.addTask(task1);
				student.addTask(task2);
				student.addTask(task3);
				student.addTask(task4);
			} catch (DateTempsRestantInvalideException e) {
			}
			
		} catch (projetJava.model.DateTempsRestantInvalideException e) {}
		
		//Task task = new Task();
		//System.out.println(task.getAllTasks());
		//System.out.println(student.getAllStudents());
		
		
		
		 System.out.println("Enter your name:"); 
	        Scanner scan = new Scanner(System.in);
	        String name="";

	        name+=scan.nextLine();
	       // scan.close();

	        System.out.println("Your name is :"+name); 
	        
	        
	        System.out.print("Date limtie de la tâche sous le format 'YYYY-MM-DD' : ");
	        Scanner sc = new Scanner(System.in);
			String date = scan.next();
			System.out.println("date: " + date);
			
    }
    
}
