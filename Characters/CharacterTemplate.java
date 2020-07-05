import java.io.Serializable;

/**
 * Template for the Character and Enemy classes
 * 
 * @author MiSobecki
 *
 */
public abstract class CharacterTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783281753918653219L;
	protected int hp, attack, armor, stamina, maxStamina, defence;

	public abstract int makeAttack();

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	public int getStamina() {
		return stamina;
	}
	
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public int getDefence() {
		return defence;
	}
	
	public void setDefence(int defence) {
		this.defence = defence;
	}
	
	public int getMaxStamina() {
		return maxStamina;
	}
	
	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

}
