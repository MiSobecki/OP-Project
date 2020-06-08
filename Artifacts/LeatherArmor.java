
public class LeatherArmor extends ChestArtifact {
	
	public LeatherArmor(ArtifactTemplate artifact) {
		super(artifact);
	}
	
	public int getAmount() {
		return super.getAmount() + 2;
	}
	
	public String getName() {
		return "Leather armor";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	

}
