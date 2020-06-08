
public class Enemy extends CharacterTemplate {

	private int award, lvl;
	private String name;
	private boolean locked;

	public Enemy(int hp, int attack, int armor, int award, String name, int lvl) {
		this.hp = hp;
		this.attack = attack;
		this.armor = armor;
		this.award = award;
		this.name = name;
		this.locked = true;
		this.lvl = lvl;
	}

	public int makeAttack() {
		return attack;
	}

	@Override
	public String toString() {
		String lock;
		if (locked)
			lock = "LOCKED";
		else
			lock = "UNLOCKED";

		return name + ", hp: " + hp + "/100, attack: " + attack + ", armor: " + armor + ", award: " + award + ", "
				+ lock;
	}

	// GETTERS AND SETTERS
	public int getAward() {
		return award;
	}

	public void setAward(int award) {
		this.award = award;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

}
