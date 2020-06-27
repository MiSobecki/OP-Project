import java.io.Serializable;
import java.util.ArrayList;

public class Character extends CharacterTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArtifactTemplate> artifacts; // headList of artifacts character have
	private ArtifactTemplate head, chest, legs, hands, rightHand, leftHand;
	private int wealth, arenaLvl;
	private static final int basicAttack = 3;
	private static final int basicArmor = 0;
	private static final int basicDefence = 3;

	/**
	 * return Character's damage on attack
	 */
	public int makeAttack() {
		return attack;
	}
	
	/**
	 * Checks if Character has artifact with the "name"
	 * 
	 * @param name
	 * @return
	 */
	public boolean containArtifact(String name) {
		for (ArtifactTemplate a : artifacts) {
			if (a.getName().compareTo(name) == 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns Artifact with the "name" if Character owns it
	 * 
	 * @param name
	 * @return
	 */
	public ArtifactTemplate searchArtifactByName(String name) {
		for (ArtifactTemplate a : artifacts) {
			if (a.getName().compareTo(name) == 0)
				return a;
		}
		return new Artifact();
	}

	/**
	 * Builder class
	 * 
	 * @author MiSobecki
	 *
	 */
	public static final class Builder {
		private int hp, attack, armor, wealth, arenaLvl, defence, stamina, maxStamina;
		private ArrayList<ArtifactTemplate> artifacts; // headList of artifacts character have
		private ArtifactTemplate head = new Artifact(), chest = new Artifact(), legs = new Artifact(),
				hands = new Artifact(), rightHand = new Artifact(), leftHand = new Artifact();

		public Builder hp(int hp) {
			this.hp = hp;
			return this;
		}

		public Builder attack(int attack) {
			this.attack = attack;
			return this;
		}

		public Builder armor(int armor) {
			this.armor = armor;
			return this;
		}

		public Builder wealth(int wealth) {
			this.wealth = wealth;
			return this;
		}

		public Builder arenaLvl(int arenaLvl) {
			this.arenaLvl = arenaLvl;
			return this;
		}

		public Builder artifacts(ArrayList<ArtifactTemplate> artifacts) {
			this.artifacts = artifacts;
			return this;
		}

		public Builder head(ArtifactTemplate head) {
			this.head = head;
			return this;
		}

		public Builder chest(ArtifactTemplate chest) {
			this.chest = chest;
			return this;
		}

		public Builder legs(ArtifactTemplate legs) {
			this.legs = legs;
			return this;
		}

		public Builder hands(ArtifactTemplate hands) {
			this.hands = hands;
			return this;
		}

		public Builder leftHand(ArtifactTemplate leftHand) {
			this.leftHand = leftHand;
			return this;
		}

		public Builder rightHand(ArtifactTemplate rightHand) {
			this.rightHand = rightHand;
			return this;
		}

		public Builder addArrtifact(ArtifactTemplate artifact) {
			this.artifacts.add(artifact);
			return this;
		}

		public Builder defence(int defence) {
			this.defence = defence;
			return this;
		}

		public Builder stamina(int stamina) {
			this.stamina = stamina;
			return this;
		}

		public Builder maxStamina(int maxStamina) {
			this.maxStamina = maxStamina;
			return this;
		}

		public Character build() {
			if (hp <= 0)
				throw new IllegalStateException("Hp can't be <= 0");
			if (attack <= 0)
				throw new IllegalStateException("Attack can't be <= 0");
			if (defence <= 0)
				throw new IllegalStateException("Defense can't be <= 0");

			Character character = new Character();
			character.arenaLvl = this.arenaLvl;
			character.artifacts = this.artifacts;
			character.attack = this.attack;
			character.chest = this.chest;
			character.hands = this.hands;
			character.hp = this.hp;
			character.wealth = this.wealth;
			character.head = this.head;
			character.leftHand = this.leftHand;
			character.rightHand = this.rightHand;
			character.legs = this.legs;
			character.armor = this.armor;
			character.defence = this.defence;
			character.stamina = this.stamina;
			character.maxStamina = this.maxStamina;

			return character;
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	// GETTERS AND SETTERS
	public ArrayList<ArtifactTemplate> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(ArrayList<ArtifactTemplate> artifacts) {
		this.artifacts = artifacts;
	}

	public ArtifactTemplate getHead() {
		return head;
	}

	public void setHead(ArtifactTemplate head) {
		this.head = head;
	}

	public ArtifactTemplate getChest() {
		return chest;
	}

	public void setChest(ArtifactTemplate chest) {
		this.chest = chest;
	}

	public ArtifactTemplate getLegs() {
		return legs;
	}

	public void setLegs(ArtifactTemplate legs) {
		this.legs = legs;
	}

	public ArtifactTemplate getHands() {
		return hands;
	}

	public void setHands(ArtifactTemplate hands) {
		this.hands = hands;
	}

	public ArtifactTemplate getRightHand() {
		return rightHand;
	}

	public void setRightHand(ArtifactTemplate rightHand) {
		this.rightHand = rightHand;
	}

	public ArtifactTemplate getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(ArtifactTemplate leftHand) {
		this.leftHand = leftHand;
	}

	public int getWealth() {
		return wealth;
	}

	public void setWealth(int wealth) {
		this.wealth = wealth;
	}

	public int getArenaLvl() {
		return arenaLvl;
	}

	public void setArenaLvl(int arenaLvl) {
		this.arenaLvl = arenaLvl;
	}

	public static int getBasicAttack() {
		return basicAttack;
	}

	public static int getBasicArmor() {
		return basicArmor;
	}

	public static int getBasicDefence() {
		return basicDefence;
	}

	/**
	 * Adds artifact to Character's artifact List
	 * 
	 * @param artifact
	 */
	public void addArtifact(ArtifactTemplate artifact) {
		artifacts.add(artifact);
	}

}