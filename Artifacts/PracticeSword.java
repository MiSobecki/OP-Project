public class PracticeSword extends RightHandArtifact {

	public PracticeSword(ArtifactTemplate artifact) {
		super(artifact);
	}

	public int getAmount() {
		return super.getAmount() + 3;
	}

	public String getName() {
		return "Practice sword";
	}
	
	public String getDescription() {
		return "Attack: + 3";
	}

}