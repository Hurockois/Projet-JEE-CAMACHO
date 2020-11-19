package edu.orsys.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class Connecteur {
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	private static final String DBNAME = "projet_cybersecurite";

	public Connection c;
	
	public static Connection getConnection(){
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver introuvable !!! ");
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(URL + DBNAME, LOGIN, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Connection impossible!!! ");
			e.printStackTrace();
		}
		System.out.println("!connection établie !!!");
		return c;
	}
	 public int executeQuery(String query) throws ClassNotFoundException, SQLException {
		    return c.createStatement().executeUpdate(query);
		  }


}
