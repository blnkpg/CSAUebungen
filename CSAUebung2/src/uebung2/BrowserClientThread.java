package uebung2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Die Thread-Klasse für die Client-Verbindung.
 */
class BrowserClientThread extends Thread{
  static final String[][] mimetypes = {
    {"html", "text/html"},
    {"htm",  "text/html"},
    {"txt",  "text/plain"},
    {"gif",  "image/gif"},
    {"jpg",  "image/jpeg"},
    {"jpeg", "image/jpeg"},
    {"jnlp", "application/x-java-jnlp-file"}
  };

  private Socket       socket;
  private int          id;
  private PrintStream  out;
  private InputStream  in;
  private String       cmd;
  private String       url;
//  private String	   httpversion;

  /**
   * Erzeugt einen neuen Client-Thread mit der angegebenen
   * id und dem angegebenen Socket.
   */
  public BrowserClientThread(int id, Socket socket)
  {
    this.id     = id;
    this.socket = socket;
  }

  /**
   * Hauptschleife für den Thread.
   */
  public void run()
  {
    try {
      System.out.println(id + ": Incoming call...");
      out = new PrintStream(socket.getOutputStream());
      in = socket.getInputStream();
      readRequest();
      createResponse();
      socket.close();
      System.out.println(id + ": Closed.");
    } catch (IOException e) {
      System.out.println(id + ": " + e.toString());
      System.out.println(id + ": Aborted.");
    }
  }

  /**
   * Liest den nächsten HTTP-Request vom Browser ein.
   */
  @SuppressWarnings("unchecked")
private void readRequest()
  throws IOException
  {
    //Request-Zeilen lesen
    @SuppressWarnings("rawtypes")
	Vector request = new Vector(10);
    StringBuffer sb = new StringBuffer(100);
    int c;
    while ((c = in.read()) != -1) {
      if (c == '\r') {
        //ignore
      } else if (c == '\n') { //line terminator
        if (sb.length() <= 0) {
          break;
        } else {
          request.addElement(sb);
          sb = new StringBuffer(100);
        }
      } else {
        sb.append((char)c);
      }
    }
    //Request-Zeilen auf der Konsole ausgeben
	Enumeration<StringBuffer> e = request.elements();
    while (e.hasMoreElements()) {
      sb = (StringBuffer)e.nextElement();
      System.out.println("< " + sb.toString());
    }
    //Kommando, URL und HTTP-Version extrahieren
    String s = "";
    if(!request.isEmpty()){
    	 s = ((StringBuffer)request.firstElement()).toString();
    }
    cmd = "";
    url = "";
//    httpversion = "";
    int pos = s.indexOf(' ');
    if (pos != -1) {
      cmd = s.substring(0, pos).toUpperCase();
      s = s.substring(pos + 1);
      //URL
      pos = s.indexOf(' ');
      if (pos != -1) {
        url = s.substring(0, pos);
        s = s.substring(pos + 1);
        //HTTP-Version
        pos = s.indexOf('\r');
        if (pos != -1) {
//          httpversion = s.substring(0, pos);
        } else {
//          httpversion = s;
        }
      } else {
        url = s;
      }
    }
  }

  /**
   * Request bearbeiten und Antwort erzeugen.
   */
  private void createResponse()
  {
    if (cmd.equals("GET") || cmd.equals("HEAD")) {
      if (!url.startsWith("/")) {
        httpError(400, "Bad Request");
      } else {
        //MIME-Typ aus Dateierweiterung bestimmen
        String mimestring = "application/octet-stream";
        for (int i = 0; i < mimetypes.length; ++i) {
          if (url.endsWith(mimetypes[i][0])) {
            mimestring = mimetypes[i][1];
            break;
          }
        }
        //URL in lokalen Dateinamen konvertieren
        String fsep = System.getProperty("file.separator", "/");
        StringBuffer sb = new StringBuffer(url.length());
        for (int i = 1; i < url.length(); ++i) {
          char c = url.charAt(i);
          if (c == '/') {
            sb.append(fsep);
          } else {
            sb.append(c);
          }
        }
        try {
          FileInputStream is = new FileInputStream("c:\\tmp\\test\\" +sb.toString());
          //HTTP-Header senden
          out.print("HTTP/1.0 200 OK\r\n");
          System.out.println("> HTTP/1.0 200 OK");
          out.print("Server: ExperimentalWebServer 0.5\r\n");
          System.out.println(
            "> Server: ExperimentalWebServer 0.5"
          );
          out.print("Content-type: " + mimestring + "\r\n\r\n");
          System.out.println("> Content-type: " + mimestring);
          if (cmd.equals("GET")) {
            //Dateiinhalt senden
            byte[] buf = new byte[256];
            int len;
            while ((len = is.read(buf)) != -1) {
              out.write(buf, 0, len);
            }
          }
          is.close();
        } catch (FileNotFoundException e) {
          httpError(404, "Error Reading File");
        } catch (IOException e) {
          httpError(404, "Not Found");
        } catch (Exception e) {
          httpError(404, "Unknown exception");
        }
      }
    } else {
      httpError(501, "Not implemented");
    }
  }

  /**
   * Eine Fehlerseite an den Browser senden.
   */
  private void httpError(int code, String description)
  {
    System.out.println("> ***" + code + ": " + description + "***");
    out.print("HTTP/1.0 " + code + " " + description + "\r\n");
    out.print("Content-type: text/html\r\n\r\n");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>ExperimentalWebServer-Error</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>HTTP/1.0 " + code + "</h1>");
    out.println("<h3>" + description + "</h3>");
    out.println("</body>");
    out.println("</html>");
  }
}