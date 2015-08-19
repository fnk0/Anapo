package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import rooms.LoadingRoom;
import rooms.Room;
import rooms.RoomManager;
import console.Console;

public class Anapo {
	
	private static final Logger log = Logger.getLogger(Anapo.class.getName());
	
	static final String DEFAULT_MOTD_FILE = "resources/motd";
	

	public static void main(String[] args) {

		initLogger();
		
		new Console();
		 
		printMotd();
		
		LoadingRoom lr = new LoadingRoom();
		RoomManager.add(lr);
		RoomManager.setCurrRoom(lr.getID());
		
		final double MAX_FRAME_TIME = 1000/30;
		
		double lastTime = System.currentTimeMillis();
		while (true)
		{
		  double current = System.currentTimeMillis();
		  double elapsed = current - lastTime;
		  
		  Room currRoom = RoomManager.getCurrRoom();
		  if( currRoom != null) {
			  currRoom.update(elapsed);
		  }
		  
		  if (elapsed < MAX_FRAME_TIME) {
			  try {
				Thread.sleep((long) (MAX_FRAME_TIME - elapsed));
			} catch (InterruptedException e) {
			}
		  }
		  
		  lastTime = current;
		  
		}

		
		
	}
	
	public static void printMotd() {
		File motd = new File(DEFAULT_MOTD_FILE);
		if (motd.isFile()) {
			try (BufferedReader in = new BufferedReader(new FileReader(motd));){
				while(in.ready()) {
					Out.print(in.readLine());
				}
			} catch (IOException e){
				log.log(Level.SEVERE, e.toString(), e);
			}
		} else {
			log.log(Level.WARNING, "Could not find MOTD");
		}
	}
	
	private static void initLogger() {
		log.setUseParentHandlers(false);
		
		 try {  
			 
			File logFolder = new File("logs");
			if (!logFolder.exists()) {
				logFolder.mkdir();
			}

	        // This block configure the logger with handler and formatter
			 String date = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());
	        FileHandler fh = new FileHandler("logs/" + date + ".txt");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);   

	    } catch (SecurityException e) {  
	    	Out.println("Problem initiating Logger");
	    } catch (IOException e) {
	    	Out.println("Problem initiating Logger"); 
	    }
	}
	
}
