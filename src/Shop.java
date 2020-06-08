import java.util.ArrayList;

import javax.swing.JFrame;

public class Shop extends JFrame {
	
	private ArrayList<ArtifactTemplate> randomList;
	private ArrayList<ArtifactTemplate> allArtifacts;


	public Shop() {
		initialize();
	}

	private void initialize() {
		
		getContentPane().setLayout(null);
		
		setAllArtifacts();
		
		//Setting to frame
		setTitle("Shop");
        setLocation(450, 100);
        setResizable(false);
        setSize(1000, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setAllArtifacts() {
		allArtifacts = new ArrayList<ArtifactTemplate>();
		allArtifacts.add(new PracticeShield(new Artifact()));
		allArtifacts.add(new PracticeSword(new Artifact()));
		allArtifacts.add(new IronSword(new Artifact()));
		allArtifacts.add(new LeatherArmor(new Artifact()));
		allArtifacts.add(new PlateArmor(new Artifact()));
		allArtifacts.add(new LeatherShoes(new Artifact()));
	}
	
	private void setRandomList() {
		SaveAndRead sr = new SaveAndRead();
	}

}
