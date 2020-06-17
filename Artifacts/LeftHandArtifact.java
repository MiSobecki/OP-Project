
public class LeftHandArtifact extends ArtifactDecorator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5627468294397069130L;

	public LeftHandArtifact(ArtifactTemplate artifact) {
		super(artifact);
	}
	
	public String getType() {
		return "Left-hand";
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
