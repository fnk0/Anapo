package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FilenameUtils;

public class Anapo {
	
	private static final Logger log = Logger.getLogger(Anapo.class.getName());
	
	static final String DEFAULT_ENTRY_FILE = "resources/default/default.ana";
	static final String DEFAULT_MOTD_FILE = "resources/motd";
	
	static String ENTRY_FILE_NAME;
	
	private static CommandCenter comCenter;

	public static void main(String[] args) {
		
		log.setUseParentHandlers(false);
		
		 try {  

	        // This block configure the logger with handler and formatter
			 String date = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());
	        FileHandler fh = new FileHandler("logs/" + date + ".txt");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);   

	    } catch (SecurityException e) {  
	       //TODO Later Error Handling
	    } catch (IOException e) {  
	       //TODO Later Error Handling  
	    }

		 
		printMotd();
		
		comCenter = new CommandCenter();
		
		File[] entryFiles = getEntryFiles();
		
		for (int i = 0; i < entryFiles.length; i++) {
			//TODO parse .ana files for name instead of using filename
			String name = FilenameUtils.getBaseName(entryFiles[i].getName());
			comCenter.register(new Command(name) {				
				@SuppressWarnings("unused")
				public void execute(String args) {
					Anapo.ENTRY_FILE_NAME = getName();
				}
			});
		}
		
		comCenter.register(new Command("New") {
			@SuppressWarnings("unused")
			public void execute(String args) {
				Anapo.ENTRY_FILE_NAME = Anapo.DEFAULT_ENTRY_FILE;
			}
		});
		
		boolean breakOnCorrectCommand = false;
		while (!breakOnCorrectCommand) {
			String input = In.getInput();
			if (comCenter.parseCommand(input)) {
				breakOnCorrectCommand = true;
			}
		}
		
		
		
		
		


	}
	
	public static File[] getEntryFiles() {
		String DEFAULT_SAVE_DIRECTORY = "resources/saves/";
		
		File dir = new File(DEFAULT_SAVE_DIRECTORY);
		
		File[] matches = dir.listFiles(
			new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".ana");
			}
		});
		
		return matches;
		
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

}
