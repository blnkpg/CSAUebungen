package uebung2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetServer implements Runnable{
	
	private ServerSocket server = null;

	/**
	 * Stellt den ServerSocket zur verfügung und startet die clientSockets
	 */
	@Override
	public void run() {
		try {
			server = new ServerSocket(23);
			Class.forName("uebung2.HtmlLogger");
			HtmlLogger log = new HtmlLogger();

			while(server.isBound()){
				Socket clientSocket = server.accept();
				System.out.println("client socket initialized");
				HtmlLogger.write(clientSocket.toString());
				ClientHandler handler = new ClientHandler(clientSocket);
				handler.run();				
			}		
			
			log.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
