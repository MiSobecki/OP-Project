
public class PlateArmor extends ArtifactDecorator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1182332846536463169L;

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
	
	public String getDescription() {
		return "Armor: + 5";
	}

}
