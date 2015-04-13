package uebung3;

import java.util.ArrayList;

public class DatabaseService {
	
	private DataBaseConnector connector = null;
	
	public DatabaseService() {
		connector = new DataBaseConnector();
	}
	

	public void tiereanzeigen(){
		this.connector.sendStatement("SELECT * FROM Tier");
	}
	
	public void futteranzeigen(){
		this.connector.sendStatement("SELECT * FROM Futter");
	} 
	
	/**
	 * Führt die entsprechende 
	 * 
	 * @param tier
	 */
	public void erzeugeTier(Tier tier){
		this.connector.sendUpdateStatement("INSERT INTO TIER values ('" + 
									tier.getName() +"' , '" + 
									tier.getWeight() + "' , '" + 
									tier.getHeight() + " ')"
						);
	}

	public ArrayList<Tier> searchAnimal(String columnName , String value){
		return new ArrayList<Tier>();
	}
	
	public void deleteAnimal(String id){
		this.connector.sendStatement("SELECT * FROM Tier WHERE ID =" + id );
	}
	
}
