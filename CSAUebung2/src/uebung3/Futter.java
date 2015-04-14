package uebung3;

public class Futter {

	int fID = 0;
	double nutrition = 0;
	String color = "";
	String name = "";
	
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
	
	
}
