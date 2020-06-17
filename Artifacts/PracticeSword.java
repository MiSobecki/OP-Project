public class PracticeSword extends RightHandArtifact {

	/**
	 * 
	 */
	private static final long serialVersionUID = -630305300774558039L;

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