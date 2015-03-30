package uebung2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	/**
	 * Rudimentäre auswahl
	 * @param args
	 * @author Christopher Lucas
	 */
	public static void main(String[] args) {
		int auswahl = 2;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
				threadPool.submit(new TelnetServer());
				threadPool.submit(new WebServ());
			System.out.println("Server gestartet");	
	}	
}
