package engine;

import java.util.Arrays;
import java.util.HashMap;

public class CommandCenter {
	HashMap<String, Command> commands;
	
	public CommandCenter() {
		commands = new HashMap<>();
	}
	
	public boolean parseCommand(String input) {
		if (!input.isEmpty()) {
			
			String[] exploded = input.split(" ");
			
			if (exploded.length > 0) {
				String commandName = exploded[0];
				String[] args = null;
				if (exploded.length > 1) {
					args = Arrays.copyOfRange(exploded, 1, exploded.length-1);
				}
				
				return execute(commandName, args);
			}
			
		}
		return false;
	}
	
	public boolean execute(String comName, String[] args) {
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i).matches(comName)) {
				commands.get(i).execute(args);
				return true;
			}
		}
		return false;
	}
	
	public void register(Command com) {
		commands.put(com.getName(), com);
	}
	
	public void unregister(Command com) {
		commands.remove(com.getName());
	}
	
	public void unregister(String name) {
		commands.remove(name);
	}
	
	public void clear() {
		commands = null;
		commands = new HashMap<>();
	}
}
