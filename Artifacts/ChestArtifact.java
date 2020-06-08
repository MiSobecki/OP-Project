
public class ChestArtifact extends ArtifactDecorator {

	public ChestArtifact(ArtifactTemplate artifact) {
		super(artifact);
	}

	public String getType() {
		return "Chest";
	}

	@Override
	public String toString() {
		return getName();
	}

}
