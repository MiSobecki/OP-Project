
public class LeatherShoes extends LegsArtifact {

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
}
