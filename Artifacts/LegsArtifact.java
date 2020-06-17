
public class LegsArtifact extends ArtifactDecorator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7688052184037772743L;

	public LegsArtifact(ArtifactTemplate artifact) {
		super(artifact);
	}

	public String getType() {
		return "Legs";
	}

	@Override
	public String toString() {
		return getName();
	}

}
