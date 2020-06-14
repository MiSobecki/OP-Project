
public class PracticeShield extends LeftHandArtifact {

	public PracticeShield(ArtifactTemplate artifact) {
		super(artifact);
	}

	public int getAmount() {
		return super.getAmount() + 3;
	}

	public String getName() {
		return "Practice Shield";
	}
	
	public String getDescription() {
		return "Defence: + 3";
	}

}
