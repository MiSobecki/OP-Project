import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		shopBut.setBounds(280, 285, 89, 23);
		getContentPane().add(shopBut);
		shopBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				Shop shop = new Shop(character, savefile);
				dispose();
			}
		});

		// Button to open arena list window
		arenaBut = new JButton("Arena");
		arenaBut.setBounds(152, 36, 89, 23);
		getContentPane().add(arenaBut);
		arenaBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				ArenaList arenaList = new ArenaList(character, savefile);
				dispose();
			}
		});

		// Button to open inventory window
		invBut = new JButton("Inventory");
		invBut.setBounds(20, 285, 89, 23);
		getContentPane().add(invBut);
		invBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				Inventory inv = new Inventory(character, savefile);
				dispose();
			}
		});

		// Button to open Inn window
		innBut = new JButton("Inn");
		innBut.setBounds(20, 123, 89, 23);
		getContentPane().add(innBut);
		innBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				Inn inn = new Inn(character);
				dispose();
			}
		});

		// HP bar
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(100, 320, 200, 30);
		getContentPane().add(healthBarPanel);

		hpBar = new JProgressBar(0, 100);
		hpBar.setPreferredSize(new Dimension(200, 30));
		hpBar.setBackground(Color.red);
		hpBar.setForeground(Color.green);
		healthBarPanel.add(hpBar);
		hpBar.setValue(character.getHp());
		System.out.println(character.getHp());

		hpLabel = new JLabel("HP: " + character.getHp() + "/100");
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabel.setBounds(10, 320, 90, 30);
		getContentPane().add(hpLabel);

		// ToolBar
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 100, 23);
		getContentPane().add(toolBar);

		// Button to exit game
		exitBut = new JButton("Exit");
		toolBar.add(exitBut);
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});

		// Button to return to the menu
		menuBut = new JButton("Menu");
		toolBar.add(menuBut);
		menuBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				Menu menu = new Menu();
				dispose();
			}
		});

		// Label to inform about wealth of character
		cashLabel = new JLabel("Cash: " + character.getWealth());
		cashLabel.setBounds(310, 319, 89, 31);
		getContentPane().add(cashLabel);

		// Frame settings
		setTitle("City");
		setLocation(700, 250);
		setResizable(false);
		setSize(420, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
