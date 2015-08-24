package character;

import item.EquipableArmor;
import item.EquipableWeapon;
import item.Item;

public class Character {
	
	int lvl;
	public static final int LVL_CAP = 100;
	
	int exp;
	
	int hp;
	int currHp;
	
	int str;
	int def;
	int dex;
	int agi;
	
	boolean isDead;
	
	EquipableWeapon weapon;
	EquipableArmor armor;
	
	public static final int INV_SIZE = 20;
	
	Item[] inventory = new Item[INV_SIZE];
	
	public Character(int exp) {
		addExp(exp);
	}
	
	public void addExp(int expGain) {
		while (expGain > 0 && lvl <= 100) {
			
			//in the form of y = 100(x-1)^2 (the -1 makes 0 exp equal to lvl 1)
			int expToNextLvl = ((int) Math.pow(100*((lvl+1)-1), 2)) - exp;
			
			if (expGain < expToNextLvl) {
				exp += expGain;
				break;
			} else if (expGain > expToNextLvl) {
				exp += expToNextLvl;
				expGain -= expToNextLvl;
				lvlUp();
			}

		}
	}
	
	public void lvlUp() {
		lvl += 1;
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public int getExp() {
		return exp;
	}
	
	public static void main(String args[]) {
		
		boolean passed = true;
		
		Character c = new Character(20);
		
		System.out.println("New charater");
		System.out.println("   lvl: " + c.getLvl());
		System.out.println("   exp: " + c.getExp() + "\n");
		
		if (c.getLvl() != 4) {
			System.out.println("Lvl mismatch");
			System.out.println("    is " + c.getLvl());
			System.out.println("    should be 4\n");
			passed = false;
		}
		
		c.addExp(400);
		
		if (c.getLvl() != 20) {
			System.out.println("Lvl mismatch");
			System.out.println("    is " + c.getLvl());
			System.out.println("    should be 20\n");
			passed = false;
		}
		
		if (passed) {
			System.out.println("all tests passed;");
		}
		
		
	}
	
}
