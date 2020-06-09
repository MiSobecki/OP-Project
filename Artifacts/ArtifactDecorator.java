import java.io.Serializable;

public abstract class ArtifactDecorator implements Serializable, ArtifactTemplate {

	private ArtifactTemplate artifact;

	public ArtifactDecorator(ArtifactTemplate artifact) {
		this.artifact = artifact;
	}

	@Override
	public int getAmount() {
		return artifact.getAmount();
	}

	@Override
	public int getCost() {
		return artifact.getCost();
	}

	@Override
	public String getName() {
		return artifact.getName();
	}

	@Override
	public String getType() {
		return artifact.getType();
	}
	
	@Override
	public String getDescription() {
		return artifact.getDescription();
	}
}
