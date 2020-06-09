
public class IronSword extends PracticeSword {

	public IronSword(ArtifactTemplate artifact) {
		super(artifact);
	}

	public int getAmount() {
		return super.getAmount() + 5;
	}

	public int getCost() {
		return super.getCost() + 1000;
	}

	public String getName() {
		return "Iron sword";
	}
	
	public String getDescription() {
		return "Attack: + 5";
	}

}
