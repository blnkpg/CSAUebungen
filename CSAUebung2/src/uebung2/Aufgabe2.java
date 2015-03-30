package uebung2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class Aufgabe2 {

	/**
	 * Spricht das InetAddress Objekt über den Unix-DayTimeServer port an und gibt die Rückgabe als string zurück
	 * 
	 * <strong>mögliche Daytimeserver</strong>
	 * <li>time.fu-berlin.de</li>
	 * <li>zeit.fu-berlin.de</li>
	 * @param address
	 * @return - Die rückgabe des Servers
	 */
	public static String run(InetAddress address) {
		int port = 13;
		String result = "";
		try {
			Socket sock = new Socket(address, port);
			InputStream input = sock.getInputStream();
			byte[] streamedBytes = new byte[100];
			int length = 0;
			while ((length = input.read(streamedBytes)) != -1) {				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				ps.write(streamedBytes, 0, length);
				result = baos.toString();
				System.out.write(streamedBytes, 0, length);
			}
			input.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
			result = address.getHostAddress() + "ist kein Unix-Daytimeserver";
		}
		return result;
	}
}
