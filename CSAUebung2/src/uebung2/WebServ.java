package uebung2;

/* ExperimentalWebServer.java */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Ein ganz einfacher Web-Server auf TCP und einem
 * beliebigen Port. Der Server ist in der Lage,
 * Seitenanforderungen lokal zu dem Verzeichnis,
 * aus dem er gestartet wurde, zu bearbeiten. Wurde
 * der Server z.B. im Verzeichnis c:\tmp gestartet, so
 * würde eine Seitenanforderung
 * http://localhost:80/test/index.html die Datei
 * c:\tmp\test\index.html laden. CGIs, SSIs, Servlets
 * oder ähnliches wird nicht unterstützt.
 * <p>
 * Die Dateitypen .htm, .html, .gif, .jpg und .jpeg werden
 * erkannt und mit korrekten MIME-Headern übertragen, alle
 * anderen Dateien werden als "application/octet-stream"
 * übertragen. Jeder Request wird durch einen eigenen
 * Client-Thread bearbeitet, nach Übertragung der Antwort
 * schließt der Server den Socket. Antworten werden mit
 * HTTP/1.0-Header gesendet.
 */
public class WebServ implements Runnable{
	
	
	public WebServ(){
		this.run();
	}
	
  private void run(int port){
    try {
      System.out.println("Listening to port " + port);
      int calls = 0;
      ServerSocket httpd = new ServerSocket(port);
      while (true) {
        Socket socket = httpd.accept();
        Class.forName("uebung2.BrowserClientThread");
//        (new BrowserClientThread(++calls, socket)).start();
        (new BrowserClientThread(++calls, socket)).run();
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }

	@Override
	public void run() {
		run(80);	
	}
}

