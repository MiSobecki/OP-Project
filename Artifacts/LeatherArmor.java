
public class LeatherArmor extends ChestArtifact {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8619494584444978390L;

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
