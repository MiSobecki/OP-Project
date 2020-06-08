
public class LeftHandArtifact extends ArtifactDecorator {
	
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
