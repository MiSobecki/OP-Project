import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	private JLabel hpLabel, cashLabel;
	private JButton beerBut, exitBut, returnBut, menuBut;
	private JToolBar toolBar;

	public Inn(Character character, String savefile) {
		initialize(character, savefile);
	}

	private void initialize(Character character, String savefile) {
		getContentPane().setLayout(null);

		// Beer button
		beerBut = new JButton("Beer");
		beerBut.setBounds(148, 273, 89, 23);
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
				character.setWealth(character.getWealth() - 200);
				if (character.getHp() == 100 || character.getWealth() < 200) {
					beerBut.setEnabled(false);
				}
			}
		});

		// ToolBar
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 150, 23);
		getContentPane().add(toolBar);

		// Button to exit game
		exitBut = new JButton("Exit");
		toolBar.add(exitBut);
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});

		// Button to return to the menu
		menuBut = new JButton("Menu");
		toolBar.add(menuBut);

		menuBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				@SuppressWarnings("unused")
				Menu menu = new Menu();
				dispose();
			}
		});

		// Button to return to the previous window
		returnBut = new JButton("Return");
		toolBar.add(returnBut);

		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				@SuppressWarnings("unused")
				City city = new City(character, savefile);
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
