package uebung3;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseService {
	
	private DataBaseConnector connector = null;
		
	public DatabaseService() {
		connector = new DataBaseConnector();
	}
	
	
	public ResultSet pullAllFromTable(String tableName){
		return this.connector.sendStatement("SELECT * FROM " + tableName);
	}

	public void insertInTable(String tableName , String[] columnnames, String[] values){
		String sqlQuery = "INSERT INTO " + tableName + " ("; 
			
		for (String columnname : columnnames) {
			sqlQuery += ""+ columnname +",";
		}		
			
		sqlQuery = sqlQuery.substring(0, sqlQuery.length()-1) + ") VALUES (";
		for (String value : values) {
			sqlQuery += "'"+ value +"',";
		}
		sqlQuery = sqlQuery.substring(0, sqlQuery.length()-1) +  ")";
		System.out.println(sqlQuery);
		
		this.connector.sendUpdateStatement(sqlQuery);
	}
	
	
	public ResultSet searchInTable(String tableName , String[] columnNames , String[] columnValues){
		String sqlQuery = "SELECT " + listValuesSQLLike(columnNames, "");
		sqlQuery += "FROM " + tableName;
		sqlQuery += " WHERE " ;
		sqlQuery += listWhereValues(columnNames, columnValues);
		
		System.out.println(sqlQuery);
		return this.connector.sendStatement(sqlQuery);
		
	}
	
	
	public ArrayList<Tier> searchAnimal(String columnName , String value){
		return new ArrayList<Tier>();
	}
	
	public void deleteAnimal(String id){
		this.connector.sendStatement("SELECT * FROM Tier WHERE ID =" + id );
	}
	
	private String listValuesSQLLike(String[] values , String quotation){
		String result ="";
		for (String value : values) {
			result += quotation + value + quotation +",";
		}		
			
		result = result.substring(0, result.length()-1);
		
		return result;
	}
	
	private String listWhereValues (String[] columnNames , String[] columnValues){
		String result = "";
		if(columnNames.length != columnValues.length){
			return "";
		}
		
		for (int i = 0; i < columnValues.length; i++) {
			result = columnNames[i] + " = " + columnValues[i];
		}
		
		return result;
	}
}
