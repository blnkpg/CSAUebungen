package uebung3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseConnector {
	
	private Connection connection = null;
	private Statement statement = null;
	
	
	public DataBaseConnector(){
			try {
				this.connection = DriverManager.getConnection("jdbc:mysql://localhost/db_uebung3.1", "mysqluser" , "mysqluser" );
				this.statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	private void sendStatement(String sqlStatement ){
		try {
			this.statement.executeQuery(sqlStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendUpdateStatement(String sqlstatement){
		try {
			this.statement.executeUpdate("INSERT INTO country VALUES ('Bulgaria','Leva')");
		} catch (Exception e) {
			
		}
	}
	
	public void tiereanzeigen(){
		sendStatement("SELECT * FROM Tier");
	}
	
	public void futteranzeigen(){
		sendStatement("SELECT * FROM Futter");
	}
	
	/**
	 * Führt die entsprechende 
	 * 
	 * @param tier
	 */
	public void erzeugeTier(Tier tier){
		sendUpdateStatement("INSERT INTO TIER values ('" + 
									tier.getName() +"' , '" + 
									tier.getWeight() + "' , '" + 
									tier.getHeight() + " ')"
						);
	}
	
	
	
}
