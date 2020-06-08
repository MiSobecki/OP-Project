
public class PlateArmor extends ArtifactDecorator {

	public PlateArmor(ArtifactTemplate artifact) {
		super(artifact);
	}
	
	public int getAmount() {
		return super.getAmount() + 5;
	}
	
	public String getName() {
		return "Plate armor";
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
