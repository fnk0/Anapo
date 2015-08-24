package character;

public class StatModifier {
	
	public enum Stat {
		HP, STR, DEF, DEX, AGI
	}
	
	
	public enum Type {
		ADD, SUB, MULT, DIV
	}
	
	private Stat stat = Stat.HP;
	
	private Type type = Type.ADD;
	
	private double value = 0;
	
	public StatModifier(Stat stat, Type type, Double value) {
		this.stat = stat;
		this.type = type;
		this.value = value;
	}
	
	public Stat getStat() {
		return stat;
	}
	
	public void setStat(Stat stat) {
		this.stat = stat;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
}
