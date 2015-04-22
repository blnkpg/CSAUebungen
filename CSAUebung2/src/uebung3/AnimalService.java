package uebung3;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalService {

	private static String tableName = "tiere";

	private ArrayList<Tier> tiere = new ArrayList<Tier>();
	private DatabaseService service = null;

	public AnimalService(DatabaseService service) {
		this.service = service;
	}

	private void pullAnimalsFromDB() {
		try {
			tiere = TierMapper.mapResults(service.pullAllFromTable(tableName));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void erstelleTier(Tier tier) {
		String[] columnNames = new String[] { "name", "weight", "height" };
		String[] columnValues = new String[] { tier.getName(), String.valueOf(tier.getWeight()), String.valueOf(tier.getHeight()) };

		this.service.insertInTable(tableName, columnNames, columnValues);
		// TODO liste auswerten
		try {
			FutterMapper.mapResults(service.searchInTable(tableName, columnNames, columnValues));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String showAllTiere() {
		String result = "";
		for (Tier tier : this.tiere) {
			result += tier.toString();
		}
		return result;
	}
}
