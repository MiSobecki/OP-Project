
public class RightHandArtifact extends ArtifactDecorator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5665507818367366560L;

	public RightHandArtifact(ArtifactTemplate artifact) {
		super(artifact);
	}

	public String getType() {
		return "Right-hand";
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
