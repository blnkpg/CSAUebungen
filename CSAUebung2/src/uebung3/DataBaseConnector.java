package uebung3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DataBaseConnector {
	
	private Connection connection = null;
	private Statement statement = null;
	
	
	public DataBaseConnector(){
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				this.connection = DriverManager.getConnection("jdbc:mysql://localhost/db_uebung3-1", "mysqluser" , "mysqluser" );
				this.statement = connection.createStatement();
				
				System.out.println("I'm connected... yess....");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	
	public ResultSet sendStatement(String sqlStatement ){
		try {
			ResultSet rueckgabe = this.statement.executeQuery(sqlStatement);
			return rueckgabe;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendUpdateStatement(String sqlstatement){
		try {
			this.statement.executeUpdate(sqlstatement);
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
