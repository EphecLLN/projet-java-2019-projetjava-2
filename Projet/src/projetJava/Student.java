/**
 * 
 */
package projetJava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robin
 *
 */
public class Student {
	
	private Long id;
	private String name;
	private List<Task> tasks ;

	Student(long id, String name, List<Task> Tasks){
		this.id = id;
		this.name = name;
		this.tasks = tasks;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Student etu1 = new Student(5, "Robin", new ArrayList<Task>());
		

	}

}
