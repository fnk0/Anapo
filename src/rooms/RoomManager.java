package rooms;

import java.util.HashMap;

import engine.CommandCenter;

public class RoomManager {
	
	private static HashMap<Integer, Room> rooms = new HashMap<>();
	private static Room currRoom = null;
	
	public static Room getRoom(int id) {
		return rooms.get(id);
	}
	
	public static Room getCurrRoom() {
		return currRoom;
	}

	public static void setCurrRoom(Room currRoom) {
		currRoom.leaving();
		CommandCenter.removeAllFrom(currRoom);
		RoomManager.currRoom = currRoom;
	}

	public static HashMap<Integer, Room> getRooms() {
		return rooms;
	}

	public static void add(Room room) {
		rooms.put(room.getID(), room);
	}
	
	public static void remove(Room room) {
		rooms.remove(room.getID());
	}
	
	public static void remove(int id) {
		rooms.remove(id);
	}
}
