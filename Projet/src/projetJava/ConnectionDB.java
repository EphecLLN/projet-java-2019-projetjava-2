package projetJava;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import projetJava.model.Student;
import projetJava.model.Task;

//Cette classe n'est pas implémentée et donc n'est pas effective.

public class ConnectionDB {
	
	public List<Student> studentData = new ArrayList<Student>(); 

	public static void sauverEnBaseStudent(String name) {
		String url = "jdbc:mysql://localhost/MyTaskManager";
		String login = "root";
		String passwrd = "";
		Connection cn = null;
		Statement st = null; 
		
		try {
			//chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Recuperation de la connexion
			cn = (Connection) DriverManager.getConnection(url, login, passwrd);
			
			//Creation d'un statement
			st = (Statement) cn.createStatement();
			String sql = "INSERT INTO `students` (`name`) VALUES ('"+ name +"')"; 	
			
			//Exécution requête 
			st.executeUpdate(sql); 
			
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//Liberer ressource de la memoire
				cn.close();
				st.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void lireEnBaseStudent() {
		String url = "jdbc:mysql://localhost/MyTaskManager";
		String login = "root";
		String passwrd = "";
		Connection cn = null;
		Statement st = null; 
		ResultSet rs = null;
		
		try {
			//chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Recuperation de la connexion
			cn = (Connection) DriverManager.getConnection(url, login, passwrd);
			
			//Creation d'un statement
			st = (Statement) cn.createStatement();
			String sql = "SELECT * FROM students"; 	
			
			//Exécution requête 
			rs = (ResultSet) st.executeQuery(sql); 
			
			//Parcour de ResultatSet
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				
			}
			} 
			catch(SQLException e) {
			e.printStackTrace();
			}
			catch(ClassNotFoundException e) {
			e.printStackTrace();
			}
			finally {
				try {
					//Liberer ressource de la memoire
					cn.close();
					st.close();
				}
				catch (SQLException e) {
				e.printStackTrace();
				}
			}
		
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//resetBaseStudent(); 
			sauverEnBaseStudent("Robin");
			lireEnBaseStudent(); 
	}

}

