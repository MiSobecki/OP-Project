import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Inn extends JFrame {

	private JPanel healthBarPanel;
	private JProgressBar hpBar;
	private JLabel hpLabel, cashLabel, histLab;
	private JButton beerBut, chickenBut, exitBut, returnBut, menuBut;
	private JToolBar toolBar;
	private JButton soupBut;

	public Inn(Character character, String savefile) {
		initialize(character, savefile);
	}

	private void initialize(Character character, String savefile) {
		getContentPane().setLayout(null);

		// Beer button
		beerBut = new JButton("Beer");
		beerBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		beerBut.setToolTipText("Cost: 200, Heal: + 10");
		beerBut.setBounds(244, 465, 104, 30);
		getContentPane().add(beerBut);
		if (character.getHp() == 100 || character.getWealth() < 200) {
			beerBut.setEnabled(false);
		}
		beerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				character.setHp(character.getHp() + 10);
				if (character.getHp() > 100)
					character.setHp(100);
				hpLabel.setText("HP: " + character.getHp() + "/100");
				hpBar.setValue(character.getHp());
				character.setWealth(character.getWealth() - 200);
				setButtonsDisabled(character);
			}
		});

		// Chicken button
		chickenBut = new JButton("Chicken");
		chickenBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		chickenBut.setToolTipText("Cost: 500, Heal: + 30");
		chickenBut.setBounds(387, 465, 104, 30);
		getContentPane().add(chickenBut);
		if (character.getHp() == 100 || character.getWealth() < 500) {
			chickenBut.setEnabled(false);
		}
		chickenBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				character.setHp(character.getHp() + 30);
				if (character.getHp() > 100)
					character.setHp(100);
				hpLabel.setText("HP: " + character.getHp() + "/100");
				hpBar.setValue(character.getHp());
				character.setWealth(character.getWealth() - 500);
				setButtonsDisabled(character);
			}
		});

		soupBut = new JButton("Soup");
		soupBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		soupBut.setToolTipText("Cost: 300, Heal: + 17");
		soupBut.setBounds(96, 465, 104, 30);
		getContentPane().add(soupBut);
		if (character.getHp() == 100 || character.getWealth() < 300) {
			soupBut.setEnabled(false);
		}
		soupBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				character.setHp(character.getHp() + 17);
				if (character.getHp() > 100)
					character.setHp(100);
				hpLabel.setText("HP: " + character.getHp() + "/100");
				hpBar.setValue(character.getHp());
				character.setWealth(character.getWealth() - 300);
				setButtonsDisabled(character);
			}
		});

		// ToolBar
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 181, 23);
		getContentPane().add(toolBar);

		// Button to exit game
		exitBut = new JButton("Exit");
		exitBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		toolBar.add(exitBut);
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, "saves/" + savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});

		// Button to return to the menu
		menuBut = new JButton("Menu");
		toolBar.add(menuBut);
		menuBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		menuBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, "saves/" + savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				new Menu();
				dispose();
			}
		});

		// Button to return to the previous window
		returnBut = new JButton("Return");
		toolBar.add(returnBut);
		returnBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, "saves/" + savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				new City(character, savefile);
				dispose();
			}
		});

		// HP bar
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(200, 520, 200, 30);
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
		hpLabel.setBounds(50, 520, 150, 30);
		getContentPane().add(hpLabel);

		// Label to inform about wealth of character
		cashLabel = new JLabel("Cash: " + character.getWealth());
		cashLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		cashLabel.setBounds(410, 520, 89, 31);
		getContentPane().add(cashLabel);
		
		histLab = new JLabel("");
		histLab.setVerticalAlignment(SwingConstants.TOP);
		histLab.setBounds(10, 34, 574, 420);
		histLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		getContentPane().add(histLab);
		setHistory();

		// Frame settings
		setTitle("City");
		setLocation(700, 250);
		setResizable(false);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setButtonsDisabled(Character character) {
		if (character.getHp() == 100 || character.getWealth() < 200) {
			beerBut.setEnabled(false);
			chickenBut.setEnabled(false);
			soupBut.setEnabled(false);
		} else if (character.getHp() == 100 || character.getWealth() < 300) {
			beerBut.setEnabled(false);
			soupBut.setEnabled(false);
		} else if (character.getHp() == 100 || character.getWealth() < 500) {
			chickenBut.setEnabled(false);
		}
	}
	
	private void setHistory() {
		SaveAndRead sr = new SaveAndRead();
		
		ArrayList<String> hists = new ArrayList<String>();
		hists.add("firstStory.txt");
		hists.add("secondStory.txt");
		hists.add("thirdStory.txt");
		
		int x = ThreadLocalRandom.current().nextInt(0, hists.size());
		
		histLab.setText(sr.readHistory(hists.get(x)));
	}
}
