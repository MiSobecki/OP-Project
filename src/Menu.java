import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Font;
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
		newBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		newBut.setAlignmentX(0.5f);
		getContentPane().add(newBut);

		loadBut = new JButton("Load");
		loadBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		loadBut.setAlignmentX(0.5f);
		getContentPane().add(loadBut);

		exitBut = new JButton("Exit");
		exitBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		exitBut.setAlignmentX(0.5f);
		getContentPane().add(exitBut);

		// Button to create a new game
		newBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				NewGame ng = new NewGame();
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
				@SuppressWarnings("unused")
				LoadGame lg = new LoadGame();
				dispose();
			}
		});

		// Frame settings
		setLocation(800, 400);
		setSize(200, 200);
		setResizable(false);
		setTitle("Menu");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
