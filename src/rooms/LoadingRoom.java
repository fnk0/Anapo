package rooms;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FilenameUtils;

import engine.Command;
import engine.CommandCenter;
import engine.In;

public class LoadingRoom extends Room {
	
	static final String DEFAULT_ENTRY_FILE = "resources/default/default.ana";
	static final String DEFAULT_SAVE_DIRECTORY = "resources/saves/";
	
	static String entryFileName;
	
	private static final Logger log = Logger.getLogger(LoadingRoom.class.getName());
	
	private final int ID = 0;
	private final String NAME = "LoadingRoom";
	
	private final String description = "";
	

	public LoadingRoom() {
		initLogger();
		
		loadSaveSelectionCommands();
	}
	
	public String getName() {
		return NAME;
	}
	
	public int getID() {
		return ID;
	}

	public String getDescription() {
		return description;
	}

	private void initLogger() {
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
	}
	
	private void loadSaveSelectionCommands() {
		//register commands for save selection
		
				File[] entryFiles = getEntryFiles();
				
				for (int i = 0; i < entryFiles.length; i++) {
					//TODO parse .ana files for name instead of using filename
					String name = FilenameUtils.getBaseName(entryFiles[i].getName());
					CommandCenter.register(this, new Command(name) {				
						@SuppressWarnings("unused")
						public void execute(String args) {
							entryFileName = getName();
						}
					});
				}
				
				CommandCenter.register(this, new Command("New") {
					@SuppressWarnings("unused")
					public void execute(String args) {
						entryFileName = DEFAULT_ENTRY_FILE;
					}
				});
				
				//get user input on which save to use
				boolean breakOnCorrectCommand = false;
				while (!breakOnCorrectCommand) {
					String input = In.getInput();
					if (CommandCenter.parseCommand(input)) {
						breakOnCorrectCommand = true;
					}
				}
				
				if (entryFileName.equals(DEFAULT_ENTRY_FILE)) {
					//verify and load defaults
					if (verifyDefaults()) {
						
					}
				} else {
					//load save data
				}
	}
	
	public static File[] getEntryFiles() {
		
		File dir = new File(DEFAULT_SAVE_DIRECTORY);
		
		File[] matches = dir.listFiles(
			new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".ana");
			}
		});
		
		if (matches == null) {
			matches = new File[0];
		}
		
		return matches;
		
	}
	
	public static boolean verifyDefaults() {
		File defaults = new File(DEFAULT_ENTRY_FILE);
		return defaults.isFile();
	}

	public void update(double elapsed) {
	}

	@Override
	public void leaving() {
		// TODO Auto-generated method stub
		
	}
}
