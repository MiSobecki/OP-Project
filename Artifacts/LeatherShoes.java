
public class LeatherShoes extends LegsArtifact {

	/**
	 * 
	 */
	private static final long serialVersionUID = 848218629901879798L;

	public LeatherShoes(ArtifactTemplate artifact) {
		super(artifact);
	}

	public int getAmount() {
		return super.getAmount() + 3;
	}

	public int getCost() {
		return super.getCost() + 450;
	}

	public String getName() {
		return "Leather shoes";
	}
	
	public String getDescription() {
		return "Armor: + 3";
	}
}
