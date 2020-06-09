
public class LeatherArmor extends ChestArtifact {

	public LeatherArmor(ArtifactTemplate artifact) {
		super(artifact);
	}

	public int getAmount() {
		return super.getAmount() + 2;
	}

	public int getCost() {
		return super.getCost() + 700;
	}

	public String getName() {
		return "Leather armor";
	}
	
	public String getDescription() {
		return "Armor: +  2";
	}

}
