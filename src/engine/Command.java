package engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Command {
	
	private Set<String> aliases;
	
	private String name;
	
	public Command(String name) {
		this.name = name;
		aliases = new HashSet<>();
		aliases.add(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getAliases() {
		return aliases;
	}

	public void setAliases(Set<String> aliases) {
		this.aliases = aliases;
	}
	
	public void addAlias(String alias) {
		aliases.add(alias);
	}
	
	public void removeAlias(String alias) {
		aliases.remove(alias);
	}
	
	public boolean matches(String str) {
		
		if (str != null) {
			Iterator<String> i = aliases.iterator();
		
			while(i.hasNext()) {
				if (str.equals(i.next())) {
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	public boolean execute(String[] args) {return false;}
	

}
