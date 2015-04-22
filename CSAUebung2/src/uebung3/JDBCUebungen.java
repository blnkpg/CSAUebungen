package uebung3;

import java.util.ArrayList;
import java.util.Scanner;

public class JDBCUebungen {

	private static Scanner benutzereingabe = new Scanner(System.in);
	private static DatabaseService service = new DatabaseService();
	private static AnimalService animalService = null;
	private static FutterService futterService = null;

	public static void main(String[] args) {
		animalService = new AnimalService(service);
		futterService = new FutterService(service);

		Boolean exit = false;

		do {
			System.out.println("[1] Tiere Verwalten" + "\n[2] Futter verwalten" + "\n[0]Beenden");

			int auswahl;

			if (exit = (auswahl = benutzereingabe.nextInt()) == 0) {
				System.out.println("Auf wiedersehen...");
			} else {
				String name = "";
				switch (auswahl) {
					case 1:
						printMenu("Tier");
						switch (benutzereingabe.nextInt()) {

							case 1:
								System.out.println("Tier Anlegen:");
								animalService.erstelleTier(editAnimal(null));
								break;

							case 2:
								System.out.println("Tier ausloeschen");
								animalService.showAllTiere();
								deleteAnimal(name);
								break;

							case 3:
								System.out.println("Tier bearbeiten");

								break;

							case 4:
								System.out.println("Tiere anzeigen");
								animalService.showAllTiere();
								break;

							default:
								break;
						}
						break;
					case 2:
						printMenu("Futter");

						switch (benutzereingabe.nextInt()) {

							case 1:
								System.out.println("Futter Anlegen:");
								futterService.erstelleFutter(editFood(null));
								break;

							case 2:
								System.out.println("Futter ausloeschen");
								break;

							case 3:
								System.out.println("Futter bearbeiten");
								break;

							case 4:
								System.out.println("Futter anzeigen");
								System.out.println(futterService.showAllFutter());
								break;

							default:
								break;
						}

						break;

					default:
						System.out.println("Falscher Wert bitte erneut eingeben.");
						break;
				}
			}
		} while (!exit);
		benutzereingabe.close();
	}

	/**
	 * Gibt dein Standardmenu mit übergebenen Prefix aus
	 * 
	 * @param what
	 *            - Prefix der vor die Ausgaben gesetzt wird <br/>
	 *            <b>what</b> = <i>"example"</i> --> <code>
	 *   [1] example anlegen <br/>
	 * 	 [2] example ausloeschen <br/>
	 * 	 [3] example anzeigen <br/>
	 * 	</code>
	 */
	private static void printMenu(String what) {
		System.out.println("[1] " + what + " anlegen \n" + "[2] " + what + " ausloeschen \n " + "[3]" + what + " bearbeiten \n" + "[4] " + what + " anzeigen");

	}

	private static Tier editAnimal(Tier animal) {
		boolean newAnimal = false;

		if (newAnimal = (animal == null)) {
			animal = new Tier("new", 0, 0);
		}

		String[] printouts = new String[3];
		boolean notOK = true;

		do {
			if (newAnimal) {
				printouts[0] = printErsteingabe("Name");
				printouts[1] = printErsteingabe("Groesse");
				printouts[2] = printErsteingabe("Gewicht");
			} else {
				printouts[0] = printChange("Name", animal.getName());
				printouts[1] = printChange("Groesse", String.valueOf(animal.getHeight()));
				printouts[2] = printChange("Gewicht", String.valueOf(animal.getWeight()));
			}

			System.out.println(printouts[0]);
			animal.setName(benutzereingabe.next());
			System.out.println(printouts[1]);
			animal.setHeight(benutzereingabe.nextDouble());
			System.out.println(printouts[2]);
			animal.setWeight(benutzereingabe.nextDouble());

			System.out.print("Eingaben OK? [y = ja | n = nein]: ");
			notOK = !benutzereingabe.next().startsWith("y") || !benutzereingabe.next().startsWith("j");
		} while (notOK);

		return animal;
	}

	private static Futter editFood(Futter food) {
		boolean newFood = false;

		if (newFood = (food == null)) {
			food = new Futter("new", "", 0);
		}

		String[] printouts = new String[4];
		boolean notOK = true;

		do {
			if (newFood) {
				printouts[0] = printErsteingabe("Name");
				printouts[1] = printErsteingabe("Farbe");
				printouts[2] = printErsteingabe("Naehrwert");
			} else {
				printouts[0] = printChange("Name", food.getName());
				printouts[1] = printChange("Farbe", String.valueOf(food.getColor()));
				printouts[2] = printChange("Naehrwert", String.valueOf(food.getNutrition()));

			}

			System.out.println(printouts[0]);
			food.setName(benutzereingabe.next());
			System.out.println(printouts[1]);
			food.setColor(benutzereingabe.next());
			System.out.println(printouts[2]);
			food.setNutrition(benutzereingabe.nextDouble());

			System.out.print("Eingaben OK? [y = ja | n = nein]: ");
			notOK = !benutzereingabe.next().startsWith("y");
		} while (notOK);

		return food;
	}

	private static void deleteAnimal(String name) {
		ArrayList<Tier> tiereMitDemNamen = service.searchAnimal("Name", name);
		int id = 0;
		if (tiereMitDemNamen.size() > 1) {
			boolean istVorhanden = false;
			do {
				System.out.println("Mehrere Eintraege gefunden...");
				for (Tier tier : tiereMitDemNamen) {
					System.out.println(tier.toString() + "\n");
				}
				printErsteingabe("Bitte geben Sie die ID des Tieres ein");
				id = benutzereingabe.nextInt();
				for (Tier tier : tiereMitDemNamen) {
					if (istVorhanden == false) {
						istVorhanden = (tier.gettID() == id);
					}
				}
			} while (!istVorhanden);

			service.deleteAnimal(String.valueOf(id));
		}

	}

	private static String printErsteingabe(String what) {
		return (what + ": ");
	}

	private static String printChange(String what , String old) {
		return what + "(vorher: " + old + ") : ";
	}

}
