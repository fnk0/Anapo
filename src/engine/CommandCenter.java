package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class CommandCenter {
	
	public static final String COMMAND_INCORRECT_TEXT = "Not a valid registered command";
	
	static HashMap<String, Command> commands = new HashMap<>();
	static HashMap<String, Object> sources = new HashMap<>();
	
	public static boolean parseCommand(String input) {
		if (!input.isEmpty()) {
			
			String[] exploded = input.split(" ");
//			Out.println(Arrays.toString(exploded));
			if (exploded.length > 0) {
				String commandName = exploded[0];
				String[] args = new String[0];
				if (exploded.length > 1) {
					args = Arrays.copyOfRange(exploded, 1, exploded.length);
//					Out.println(Arrays.toString(args));
				}
				
				return execute(commandName, args);
			}
			
		}
		return false;
	}
	
	public static boolean execute(String comName, String[] args) {
		Iterator<String> i = commands.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			if (commands.get(key).matches(comName)) {
				commands.get(key).execute(args);
				return true;
			}
		}
		
		Out.println(COMMAND_INCORRECT_TEXT);
		return false;
	}
	
	public static void register(Object source, Command com) {
		commands.put(com.getName(), com);
		sources.put(com.getName(), source);
	}
	
	public static void unregister(Command com) {
		commands.remove(com.getName());
		sources.remove(com.getName());
	}
	
	public static void unregister(String name) {
		commands.remove(name);
		sources.remove(name);
	}
	
	public static void removeAllFrom(Object source) {
		
		Iterator<String> i = sources.keySet().iterator();
		ArrayList<String> toRemove = new ArrayList<>(); 
		
		//find entries with particular source
		while(i.hasNext()) {
			String name = i.next();
			if (source == sources.get(name)) {
				toRemove.add(name);
			}
		}
		
		//remove those entries
		for (int n = 0; n < toRemove.size(); n++) {
			sources.remove(toRemove.get(n));
			commands.remove(toRemove.get(n));
		}
	}
	
	public static void printCommands() {
		Iterator<String> i = commands.keySet().iterator();
		while(i.hasNext()) {
			Out.println(i.next());
		}
	}
	
	
	public static void clear() {
		commands = null;
		commands = new HashMap<>();
	}
}
