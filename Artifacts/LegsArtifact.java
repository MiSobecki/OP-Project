
public class LegsArtifact extends ArtifactDecorator {

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
