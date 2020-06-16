import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class City extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton shopBut, arenaBut, invBut, innBut, exitBut, menuBut;
	private JPanel healthBarPanel;
	private JToolBar toolBar;
	private JProgressBar hpBar;
	private JLabel hpLabel, cashLabel;

	public City(Character character, String savefile) {
		initialize(character, savefile);
	}

	private void initialize(Character character, String savefile) {
		getContentPane().setLayout(null);

		// Button to open shop window
		shopBut = new JButton("Shop");
		shopBut.setBounds(550, 552, 150, 50);
		getContentPane().add(shopBut);
		shopBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		shopBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Shop(character, savefile);
				dispose();
			}
		});

		// Button to open arena list window
		arenaBut = new JButton("Arena");
		arenaBut.setBounds(350, 99, 150, 50);
		getContentPane().add(arenaBut);
		arenaBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		arenaBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new ArenaList(character, savefile);
				dispose();
			}
		});

		// Button to open inventory window
		invBut = new JButton("Inventory");
		invBut.setBounds(100, 552, 150, 50);
		getContentPane().add(invBut);
		invBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		invBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Inventory(character, savefile);
				dispose();
			}
		});

		// Button to open Inn window
		innBut = new JButton("Inn");
		innBut.setBounds(100, 251, 150, 50);
		getContentPane().add(innBut);
		innBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		innBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Inn(character, savefile);
				dispose();
			}
		});

		// HP bar
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(350, 700, 200, 30);
		getContentPane().add(healthBarPanel);

		hpBar = new JProgressBar(0, 100);
		hpBar.setPreferredSize(new Dimension(200, 30));
		hpBar.setBackground(Color.red);
		hpBar.setForeground(Color.green);
		healthBarPanel.add(hpBar);
		hpBar.setValue(character.getHp());

		hpLabel = new JLabel("HP: " + character.getHp() + "/100");
		hpLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabel.setBounds(202, 700, 138, 30);
		getContentPane().add(hpLabel);

		// ToolBar
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 122, 30);
		getContentPane().add(toolBar);

		// Button to exit game
		exitBut = new JButton("Exit");
		exitBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		toolBar.add(exitBut);
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});

		// Button to return to the menu
		menuBut = new JButton("Menu");
		menuBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		toolBar.add(menuBut);
		menuBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Menu();
				dispose();
			}
		});

		// Label to inform about wealth of character
		cashLabel = new JLabel("Cash: " + character.getWealth());
		cashLabel.setBounds(618, 699, 89, 31);
		getContentPane().add(cashLabel);
		cashLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// Frame settings
		setTitle("City");
		setLocation(450, 200);
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
