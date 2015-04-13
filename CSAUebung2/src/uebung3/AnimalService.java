package uebung3;

import java.util.ArrayList;

public class AnimalService {

	private static ArrayList<Tier> tiere = new ArrayList<Tier>();
	private static DataBaseConnector connector = null;
	
	public AnimalService(DataBaseConnector connector){
		this.connector = connector;
	}
	
}
