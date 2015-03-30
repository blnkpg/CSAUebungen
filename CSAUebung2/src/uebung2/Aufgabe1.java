package uebung2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

public class Aufgabe1 {

	/**
	 * Erzeugt ein InetAdress Objekt aus der eingegebenen URL
	 * die eingegebene URL kann entweder direkt, oder auch als IP, eingegeben werden.
	 * Es wird dann jeweils das pendand ausgegeben.
	 * 
	 * Ergo: bei einer eingegebene <strong>IP</strong> wird die <strong>Hostname</strong> und bei einer einem eingegebenen <strong>Hostname</strong> die <strong>IP</strong> ausgegeben.
	 * 
	 * @param out
	 * @param in
	 * @return Objekt aus der eingegebenen URL zur weiterverarbeitung
	 */
	static public InetAddress run(PrintWriter out , BufferedReader in){
		boolean wasError = false;
		do{
			out.println(HtmlLogger.write("Geben sie eine URL an (ohne http://): "));
			String url;
			try {
				url = in.readLine();
				url = url.replaceAll("\b", "");
				HtmlLogger.write("Benutzereingabe: " + url);
				boolean isIP = false;
				byte[] ipV4Adress = new byte[4];
				try {
					isIP = (Long.valueOf(url.replace(String.valueOf('.'), "")) != 0)? true: false;
					String temp = url;
					int stelle = 0;
					int i = 0;
					do{
						ipV4Adress[i] = Integer.valueOf(temp.substring(0, ((stelle = temp.indexOf("."))<0)?temp.length():stelle )).byteValue();
						temp = temp.substring(stelle+1);
						System.out.println(temp);
						i++;
					}while(i<=3);	
				}catch (NumberFormatException e) {
					
				}
				InetAddress adresse = (isIP)?InetAddress.getByAddress(ipV4Adress):InetAddress.getByName(url);
				
				if(isIP){
					url += " : "  + adresse.getHostName();
				}else{
					url += " : "  + adresse.getHostAddress();
				}
				out.println("\n\r" + HtmlLogger.write(url));
				return adresse;
			} catch (IOException e1) {
				e1.printStackTrace();
				wasError = true;
			}
		}while(wasError);
		return null;	
	}
	
}
