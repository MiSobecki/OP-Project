import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton newBut, exitBut, loadBut;

	public Menu() {
		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		newBut = new JButton("New Game");
		newBut.setAlignmentX(0.5f);
		getContentPane().add(newBut);

		loadBut = new JButton("Load");
		loadBut.setAlignmentX(0.5f);
		getContentPane().add(loadBut);

		exitBut = new JButton("Exit");
		exitBut.setAlignmentX(0.5f);
		getContentPane().add(exitBut);

		// Button to create a new game
		newBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				City city = new City(createNewCharacter());
				dispose();
			}
		});

		// Button to exit game
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});

		// Button to load game
		loadBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					@SuppressWarnings("unused")
					City city = new City((Character) sr.read("Character2"));
					dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
					System.out.println("Save doesn't exist");
					@SuppressWarnings("unused")
					City city = new City(createNewCharacter());
					dispose();
				}
			}
		});

		setLocation(800, 400);
		setSize(200, 200);
		setResizable(false);
		setTitle("Menu");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Character createNewCharacter() {
		Character character = Character.builder().hp(100).attack(3).wealth(0).arenaLvl(0).armor(0).defence(3)
				.artifacts(new ArrayList<ArtifactTemplate>()).addArrtifact(new PracticeSword(new Artifact()))
				.addArrtifact(new PracticeShield(new Artifact())).build();
		return character;
	}

}
