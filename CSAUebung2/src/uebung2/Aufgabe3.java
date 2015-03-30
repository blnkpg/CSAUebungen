package uebung2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

/**
 * NTP Client 
 * 
 * 
 * <strong>laut http://www.urz.uni-heidelberg.de/netz/ntp.html</strong><br/>
 * 	<strong>stratum:</strong></li> 2 <em> - Gibt die Hierarchiestufe des NTP Server an </em><br/> 
 * 	<strong>>Version:</strong> 3 <em> Die Version des NTP Dienstes </em><br/>
 * 	<strong>Mode:</strong> <br/>
 * 			vonn @see <a href="http://doc-tcpip.org/Ntp/basics.html">http://doc-tcpip.org/Ntp/basics.html</a>  
 * 			<br/><br/><b>Symmetric Active Mode (1)</b> Hei�t, da� der Host seine Zeitinformation weitergibt und auch die Zeitinformation anderer Server empfangen m�chte. Das wird bei der Konfiguration mit dem "peer" Statement ausgedr�ckt.
 * 			<br/><br/><b>Symmetric Passive Mode (2)</b> Ist �hnlich, nur das hierbei der Host nicht selbst aktiv einen Server anspricht.
 * 			<br/><br/><b>Client Mode (3)</b> Hier fragt der Host einen definierten Server nach der Zeitinformation. Den Rhythmus dieser Anfragen bestimmt der Client nach seinen Bed�rfnissen - man spricht vom pollen. (Das gilt auch f�r die beiden symmetrischen Modi).
 * 			<br/><br/><b>Server Mode (4)</b> Entsteht dann, wenn ein Host gewillt ist, eine gezielte Client-Anfrage zu bedienen.
 * 			<br/><br/><b>Broadcast Mode (5)</b> Hierbei schickt eine Server-Maschine regelm��ig Pakete mit der Zeitinformation aufs Netz, unabh�ngig vom Zustand der Clients.
 * 	<br/><br/><strong>poll:</strong> <em> der Zeitzyklus der Serverafrage </em>
 * 	<br/><strong>precision:</strong> -6 <em> abweichung des Client- von der Serverzeit  (6 ms) </em>
 * 	<br/><strong>delay:</strong> 2048 <em>  die Dauer die das Netzwerkpaketben�tigt </em>
 * 	<br/><strong>dispersion:</strong> <em> ist die h�lfte des Delay und des m�gliche Fehlerwertes und wird dazu verwendet um die Zeit zu errechnen</em> 
 * @author Christopher Lucas
 *
 */
public class Aufgabe3 {

	/**
	 * Spricht das �bergebene InetAdress objekt �ber den NTP port 
	 * mithilfe eines NTP Clients an und gibt zwischen informationen direkt in 
	 * der Telnet-Console aus und gibt das endresultat als String zur�ck
	 * 
	 	 * 	
	 * @param address
	 * @param out
	 * @return - die Telnet-Server antwort
	 */
	public static String run(InetAddress address , PrintWriter out) {
		if(address == null){
			return "No URL entered";
		}
		NTPUDPClient ntpClient = new NTPUDPClient();
		try {
			TimeInfo timeStamp = ntpClient.getTime(address);
			NtpV3Packet nachricht = timeStamp.getMessage();
			
			out.write("Stratum: " + nachricht.getModeName());
			/*
			  */
			
			out.write(nachricht.toString());
			
			Date uebergebeneZeit = new Date(nachricht.getTransmitTimeStamp().getTime());
			return "Die Zeit vom Server " + address.getHostName() + " : "  + uebergebeneZeit;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return "Error: was no NTP Server";
	}

}
