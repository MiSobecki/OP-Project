
public class PlateArmor extends ArtifactDecorator {

	public PlateArmor(ArtifactTemplate artifact) {
		super(artifact);
	}

	public int getAmount() {
		return super.getAmount() + 5;
	}

	public int getCost() {
		return super.getCost() + 3500;
	}

	public String getName() {
		return "Plate armor";
	}

}
