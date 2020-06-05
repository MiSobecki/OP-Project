public class PracticeSword extends RightHandArtifact{
	
	public PracticeSword(ArtifactTemplate artifact) {
		super(artifact);
	}
	
	public int getAmount() {
		return super.getAmount() + 5;
	}
	
	public String getName() {
		return "Practice sword";
	}
	
	@Override
	public String toString(){
		return getName();
	}

}