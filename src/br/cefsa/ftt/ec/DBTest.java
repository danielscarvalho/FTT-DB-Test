package br.cefsa.ftt.ec;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/*
 * JDBC:
 * 
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html
 * 
 * MySQL: https://dev.mysql.com/doc/connector-j/8.0/en/
 * Oracle: http://www.oracle.com/technetwork/database/application-development/jdbc/overview/quickstart-4308895.html
 * MS SQLServer: https://www.microsoft.com/pt-BR/download/details.aspx?id=11774
 * 
 */

public class DBTest {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/appdb";

	private static final String DB_USER = "scott";
	private static final String DB_PASSWORD = "tiger";	
	
	public static void main(String[] args) {
		
		String testQuery = "SELECT VERSION() VERSION, CURRENT_DATE, USER() USER";
		
		try {
			
			Class.forName(DB_DRIVER);
			Connection conn = null;
			conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(testQuery);
			
			while (rs.next()) {
				
				String dbVersion = rs.getString("VERSION");
				String dbDate = rs.getString("CURRENT_DATE");
				String dbUser = rs.getString("USER");

				System.out.println(new Date() + " - " + 
				                   dbVersion + " - "  + 
				                   dbDate + " - "     + 
				                   dbUser);

			} //while
						
			conn.close();
		
		} catch(ClassNotFoundException sqlCNFound) {
			
			System.out.println(new Date() + " - Where is the MySQL JDBC Driver?");
			sqlCNFound.printStackTrace();
		
		} catch(SQLException sqlError) {
		
			sqlError.printStackTrace();
		
		} //try
		
		System.out.println(new Date() + " - Hi!");

	}//main

}//Test
