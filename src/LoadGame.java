import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoadGame extends JFrame {
	
	private JLabel label;

	public LoadGame() {
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);
		
		label = new JLabel("Select game save to load");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(125, 20, 250, 40);
		getContentPane().add(label);

		// Frame settings
		setLocation(800, 400);
		setSize(500, 600);
		setResizable(false);
		setTitle("Load Game");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
