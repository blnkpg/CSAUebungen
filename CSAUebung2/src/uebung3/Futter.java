package uebung3;

public class Futter {

	private int fID = 0;
	private double nutrition = 0;
	private String color = "";
	private String name = "";
	
	/**
	 * 
	 * @param nutrition
	 * @param color
	 * @param name
	 */
	public Futter(int fID , String name, String color , double nutrition) {
		this.fID = fID; 
		this.nutrition = nutrition;
		this.color = color;
		this.name = name;
	}

	public Futter(String name, String color , double nutrition) {
		this.fID = 0; 
		this.nutrition = nutrition;
		this.color = color;
		this.name = name;
	}
	
	@Override
	public String toString(){
		return 		"Name: " + this.getName() + "\t|\t"
				+ 	"Farbe: " + this.getColor() + "\t|\t"
				+ 	"Naehrwert: " + this.getNutrition() + "\n"
				;
	}

	
	public int getfID() {
		return fID;
	}

	public void setfID(int fID) {
		this.fID = fID;
	}

	public double getNutrition() {
		return nutrition;
	}

	public void setNutrition(double nutrition) {
		this.nutrition = nutrition;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
