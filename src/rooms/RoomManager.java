package rooms;

import java.util.HashMap;

import engine.CommandCenter;

public class RoomManager {
	
	private static HashMap<Integer, Room> roomsById = new HashMap<>();
	private static HashMap<String, Room> roomsByName = new HashMap<>();
	
	private static Room currRoom = null;
	
	public static Room getRoom(int id) {
		return roomsById.get(id);
	}
	
	public static Room getRoom(String str) {
		return roomsByName.get(str);
	}
	
	public static Room getCurrRoom() {
		return currRoom;
	}

	public static void setCurrRoom(int id) {
		currRoom.leaving();
		CommandCenter.removeAllFrom(currRoom);
		RoomManager.currRoom = roomsById.get(id);
	}
	
	public static void setCurrRoom(String name) {
		currRoom.leaving();
		CommandCenter.removeAllFrom(currRoom);
		RoomManager.currRoom = roomsByName.get(name);
	}

	public static HashMap<Integer, Room> getRoomsByID() {
		return roomsById;
	}
	
	public static HashMap<String, Room> getRoomsByName() {
		return roomsByName;
	}

	public static void add(Room room) {
		roomsById.put(room.getID(), room);
		roomsByName.put(room.getName(), room);
	}
	
	public static void remove(Room room) {
		roomsById.remove(room.getID());
		roomsByName.remove(room.getName());
	}
	
	public static void remove(int id) {
		roomsByName.remove(roomsById.get(id).getName());
		roomsById.remove(id);
	}
	
	public static void remove(String name) {
		roomsById.remove(roomsByName.get(name).getName());
		roomsByName.remove(name);
	}
}
