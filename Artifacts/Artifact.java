import java.io.Serializable;

public class Artifact implements Serializable, ArtifactTemplate {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;

	@Override
	public int getAmount() {
		return 0;
	}

	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public String toString() {
		return "Artifact";
	}

	@Override
	public String getName() {
		return "Artifact";
	}

	@Override
	public String getType() {
		return "None";
	}
	
	@Override public String getDescription() {
		return "None";
	}

}
