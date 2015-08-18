package rooms;

public abstract class Room {
	public abstract int getID();
	public abstract String getDescription();
	public abstract void update(double elapsed);
	public abstract void leaving();
}
