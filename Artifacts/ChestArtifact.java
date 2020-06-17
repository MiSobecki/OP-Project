
public class ChestArtifact extends ArtifactDecorator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5502435561076275344L;

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
