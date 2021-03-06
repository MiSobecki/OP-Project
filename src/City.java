import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * City window, where player can move to other locations
 * 
 * @author MiSobecki
 *
 */
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
	
	private Character character;

	public City(Character character, String savefile) {
		this.character = character;
		initialize(savefile);
	}

	private void initialize(String savefile) {
		getContentPane().setLayout(null);

		// Button to open shop window
		shopBut = new JButton("Shop");
		shopBut.setBounds(550, 552, 150, 50);
		getContentPane().add(shopBut);
		shopBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		shopBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Shop(character, savefile);
				dispose();
			}
		});
		shopBut.setBackground(Color.black);
		shopBut.setForeground(Color.red);
		
		// Button to open arena list window
		arenaBut = new JButton("Arena");
		arenaBut.setBounds(350, 99, 150, 50);
		getContentPane().add(arenaBut);
		arenaBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		arenaBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new ArenaList(character, savefile);
				dispose();
			}
		});
		arenaBut.setBackground(Color.black);
		arenaBut.setForeground(Color.red);

		// Button to open inventory window
		invBut = new JButton("Inventory");
		invBut.setBounds(100, 552, 150, 50);
		getContentPane().add(invBut);
		invBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		invBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Inventory(character, savefile);
				dispose();
			}
		});
		invBut.setBackground(Color.black);
		invBut.setForeground(Color.red);

		// Button to open Inn window
		innBut = new JButton("Inn");
		innBut.setBounds(100, 251, 150, 50);
		getContentPane().add(innBut);
		innBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		innBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Inn(character, savefile);
				dispose();
			}
		});
		innBut.setBackground(Color.black);
		innBut.setForeground(Color.red);

		setupHpBar();

		setupToolBar();

		// Label to inform about wealth of character
		cashLabel = new JLabel("Cash: " + character.getWealth());
		cashLabel.setBounds(618, 699, 89, 31);
		getContentPane().add(cashLabel);
		cashLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		cashLabel.setBackground(Color.black);
		cashLabel.setForeground(Color.red);

		// Frame settings
		setTitle("City");
		setLocation(450, 200);
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setFocusable(true);
	}
	
	/**
	 * Creates Character Hp Bar
	 */
	private void setupHpBar() {
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(350, 700, 200, 30);
		healthBarPanel.setBackground(Color.black);
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
		hpLabel.setBackground(Color.black);
		hpLabel.setForeground(Color.red);
	}
	
	/**
	 * Creates Character Hp Bar
	 */
	private void setupToolBar() {
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 122, 30);
		getContentPane().add(toolBar);
		toolBar.setBackground(Color.black);
		toolBar.setForeground(Color.red);

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
		exitBut.setBackground(Color.black);
		exitBut.setForeground(Color.red);

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
		menuBut.setBackground(Color.black);
		menuBut.setForeground(Color.red);
	}
}
