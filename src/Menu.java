import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

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
		getContentPane().setLayout(new GridLayout(6, 1, 5, 5));

		newBut = new JButton("New Game");
		newBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		newBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(newBut);
		newBut.setBackground(Color.black);
		newBut.setForeground(Color.red);

		loadBut = new JButton("Load");
		loadBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		loadBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(loadBut);
		loadBut.setBackground(Color.black);
		loadBut.setForeground(Color.red);

		exitBut = new JButton("Exit");
		exitBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		exitBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(exitBut);
		exitBut.setBackground(Color.black);
		exitBut.setForeground(Color.red);

		// Button to create a new game
		newBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new NewGame();
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
				new LoadGame();
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
		getContentPane().setBackground(Color.black);
		setFocusable(true);
	}

}
