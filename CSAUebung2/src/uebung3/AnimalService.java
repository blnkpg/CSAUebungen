package uebung3;

import java.util.ArrayList;

public class AnimalService {

	private static String sqlFetchAll = "SELECT * FROM tier";
	
	
	private static ArrayList<Tier> tiere = new ArrayList<Tier>();
	private DatabaseService service = null;
	
	public AnimalService(DatabaseService service){
		this.service = service;
		
	}
	
	
	private void pullAnimalsFromDB(){
		tiere.addAll();
	}
}
