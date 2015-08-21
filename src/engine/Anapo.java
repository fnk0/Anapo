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

import room.LoadingRoom;
import room.Room;
import room.RoomManager;
import console.Console;
import console.ConsoleEvent;
import console.ConsoleListener;

public class Anapo {
	
	public static final Logger log = Logger.getLogger(Anapo.class.getName());
	private static FileHandler fh;
	
	static final String DEFAULT_MOTD_FILE = "resources/motd";
	

	public static void main(String[] args) {

		initLogger();
		
		Console console = new Console("Anapo");
		console.addConsoleListener(new ConsoleListener() {
			public void inputRecieved(ConsoleEvent e) {
				CommandCenter.parseCommand(e.getMsg());
			}
		});
		
		
		log.log(Level.INFO, "Opening Console");
		
		//Testing
		//list commands
		
		CommandCenter.register(null, new Command("list") {
			public boolean execute(String[] args) {
				
				if (args.length > 0 && args[0].equals("commands")) {
					Out.println("");
					CommandCenter.printCommands();
					Out.println("");
				} else {
					Out.println("Usage: list commands");
				}
				
				return true;
			}
		});
		 
		printMotd();
		
		//Registering main commands
		registerMainCommands();
		
		log.info("Setting up loading room");
		LoadingRoom lr = new LoadingRoom();
		RoomManager.add(lr);
		RoomManager.setCurrRoom(lr.getId());
		
		
		log.info("Starting Game Loop");
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
					Out.println(in.readLine());
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
	        fh = new FileHandler("logs/" + date + ".txt");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);   

	    } catch (SecurityException e) {  
	    	Out.println("Problem initiating Logger");
	    } catch (IOException e) {
	    	Out.println("Problem initiating Logger"); 
	    }
	}
	
	public static void close() {
		log.warning("closing application");
		fh.close();
	}
	
	private static void registerMainCommands() {
//		CommandCenter.register(null, new Command("list") {
//			public boolean execute(String[] args) {
//				
//				if (args.length > 0 && args[0].equals("commands")) {
//					Out.println("");
//					CommandCenter.printCommands();
//					Out.println("");
//				} else {
//					Out.println("Usage: list commands");
//				}
//				
//				return true;
//			}
//		});
	}
	
}
