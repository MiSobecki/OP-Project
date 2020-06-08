
public class IronSword extends PracticeSword {

	public IronSword(ArtifactTemplate artifact) {
		super(artifact);
	}
	
	public int getAmount() {
		return super.getAmount() + 5;
	}
	
	public String getName() {
		return "Iron sword";
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
