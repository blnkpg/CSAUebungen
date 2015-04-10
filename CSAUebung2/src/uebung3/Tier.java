package uebung3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tier {
	int tID = 0;
	String name = "";
	double weight = 0;
	double height = 0;
	LinkedList<Futter> happa = new LinkedList<Futter>();
	
	/**
	 * 
	 * @param name
	 * @param weight
	 * @param height
	 */
	public Tier(String name , double weight , double height){
		this.name = name;
		this.weight = weight;
		this.height = height;
	}
	
	public int gettID() {
		return tID;
	}
	public void settID(int tID) {
		this.tID = tID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public LinkedList<Futter> getHappa() {
		return happa;
	}
	public void setHappa(LinkedList<Futter> happa) {
		this.happa = happa;
	}
}
