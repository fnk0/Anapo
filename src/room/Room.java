package room;

import item.Item;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	int id;
	
	String name;
	String description;
	
	List<Item> items = new ArrayList<>();
	List<Character> characters = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void update(double elapsed) {
		
	}
	
	public void leaving() {
		
	}
	
	public void up() {
		
	}
	
	public void down() {
		
	}
	
	public void north() {
		
	}
	
	public void south() {
		
	}
	
	public void west() {
		
	}
	
	public void east() {
		
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void addCharacter(Character character) {
		characters.add(character);
	}
	
	public void removeCharacter(Character character) {
		characters.remove(character);
	}
	
	public List<Character> getCharacters() {
		return characters;
	}
}
