package projetJava;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class ConnectionDB {

	public static void sauverEnBase(String student) {
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
			String sql = "INSERT INTO `students` (`personne`) VALUES ('"+ student +"')"; 	
			
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
	
	public static void lireEnBase() {
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
					System.out.println(rs.getString("personne"));
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
			sauverEnBase("test");
			lireEnBase(); 
	}

}

