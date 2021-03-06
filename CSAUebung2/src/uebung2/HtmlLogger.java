package uebung2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.net.ntp.TimeStamp;


public class HtmlLogger extends Thread {
	
		private static File index = new File("c:\\tmp\\test\\index.html");
		
				
		public static String write(String entry){
			if (!index.canRead() || !index.canWrite()){
				return entry;
			}
			try {
				BufferedReader reader = new BufferedReader(new FileReader(index));
				LinkedList <String> lines = new LinkedList<String>();
				String line = null;
				while( (line = reader.readLine()) != null ){
					if(line.contains("</body>")){
						lines.add(getTime() + entry + "<br/>");
						lines.add(line);
					}else{
						lines.add(line);
					}
				}
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(index));
				for (String cachedLine : lines) {
					writer.write(cachedLine);
				}
				
				
				
				writer.close();
				
				
				reader.close();
				
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return entry;
		}

		
		
		@Override
		public void run() {
			if(!index.exists()){
				BufferedWriter writer;
				try {
					writer = new BufferedWriter(new FileWriter(index));
					String []htmlTags = {"<html>","<head>","</head>","<body>","</body>","</html>"}; 
					for (String tag : htmlTags) {
						writer.write(tag + "\n\r");
						writer.newLine();
					}
					writer.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		private static String getTime(){
			return new TimeStamp(new Date()).toDateString() + " : ";
		}
		
		public void dispose(){
			try {
				super.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
}
