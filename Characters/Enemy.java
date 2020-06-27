
public class Enemy extends CharacterTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 241595758663054555L;
	private int award, lvl;
	private String name;
	private boolean locked;
	private String img;
	private String description;

	public Enemy(int hp, int attack, int armor, int award, String name, int lvl, int stamina, int defence,
			int maxStamina, String img) {
		this.hp = hp;
		this.attack = attack;
		this.armor = armor;
		this.award = award;
		this.name = name;
		this.locked = true;
		this.lvl = lvl;
		this.stamina = stamina;
		this.defence = defence;
		this.maxStamina = maxStamina;
		this.img = "pictures/" + img;
		this.description = name + ", hp: " + hp + "/100, attack: " + attack + ", armor: " + armor + ", award: " + award;
	}

	public int makeAttack() {
		return attack;
	}

	/**
	 * Makes decision which action Enemy will do
	 * 
	 * @return
	 */
	public String makeDecision() {
		if (stamina >= 5) {
			return "Attack";
		}
		if (stamina >= 3) {
			return "Defend";
		}
		return "Wait";
	}

	@Override
	public String toString() {
		return description;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
