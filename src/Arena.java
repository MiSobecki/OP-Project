import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Arena extends JFrame {

	private JPanel healthBarPanel, healthBarPanelE, staminaBarPanel, staminaBarPanelE;
	private JProgressBar hpBar, hpBarE, sBar, sBarE;
	private JLabel hpLabel, hpLabelE, sLabel, sLabelE, enemyLab;
	private JButton attackBut, defendBut, waitBut;

	private Character character;
	private Enemy enemy;
	private String savefile;

	public Arena(Character character, Enemy enemy, String savefile) {
		this.character = character;
		this.enemy = enemy;
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(null);

		// Character HP bar
		createCharacterBars();

		// Enemy HP bar
		createEnemyBars();

		// Enemy portrait
		enemyLab = new JLabel(enemy.getName());
		enemyLab.setHorizontalAlignment(SwingConstants.CENTER);
		enemyLab.setBounds(300, 11, 200, 78);
		getContentPane().add(enemyLab);
		
		JLabel upArrowLab = new JLabel("");
		ImageIcon imgThisImg = new ImageIcon("upArrow.png");
		upArrowLab.setIcon(imgThisImg);
		upArrowLab.setBounds(227, 183, 102, 358);
		getContentPane().add(upArrowLab);

		// Attack button
		attackBut = new JButton("Attack");
		attackBut.setBounds(240, 633, 89, 23);
		getContentPane().add(attackBut);
		attackBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				attackAction();
			}
		});

		// Defend button
		defendBut = new JButton("Defend");
		defendBut.setBounds(340, 633, 89, 23);
		getContentPane().add(defendBut);
		defendBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				defendAction();
			}
		});

		// Wait button
		waitBut = new JButton("Wait");
		waitBut.setBounds(440, 633, 89, 23);
		getContentPane().add(waitBut);
		waitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				waitAction();
			}
		});

		// Setting to frame
		setTitle("Arena List");
		setLocation(450, 100);
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// actions after attackBut pressed
	private void attackAction() {
		String dec = enemy.makeDecision();
		charactersAttack(dec);

		if (enemy.getHp() == 0) {
			enemyLost();
		} else {
			Timer timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enemysMove("Attack", dec);
				}
			});

			timer.setRepeats(false);
			timer.start();

			if (character.getHp() == 0) {
				characterLost();
			}

		}
	}

	// actions after defendBut pressed
	private void defendAction() {
		character.setStamina(character.getStamina() - 3);
		if (character.getStamina() < 0)
			character.setStamina(0);

		attackBut.setEnabled(false);
		defendBut.setEnabled(false);
		waitBut.setEnabled(false);

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enemysMove("Defend", enemy.makeDecision());
			}
		});

		timer.setRepeats(false);
		timer.start();

		if (character.getHp() == 0) {
			characterLost();
		}
	}

	// actions after waitBut pressed
	private void waitAction() {
		character.setStamina(character.getStamina() + 4);
		if (character.getStamina() > character.getMaxStamina())
			character.setStamina(character.getMaxStamina());
		
		attackBut.setEnabled(false);
		defendBut.setEnabled(false);
		waitBut.setEnabled(false);
		
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enemysMove("Wait", enemy.makeDecision());
			}
		});
		
		timer.setRepeats(false);
		timer.start();
		
		if (character.getHp() == 0) {
			characterLost();
		}
	}

	private void charactersAttack(String Edecision) {
		int dmg = 0;
		if (Edecision.compareTo("Attack") == 0 || Edecision.compareTo("Wait") == 0) {
			dmg = enemy.getArmor() - character.makeAttack();
		} else {
			dmg = enemy.getArmor() + enemy.getDefence() - character.makeAttack();
		}
		if (dmg > 0)
			dmg = 0;

		character.setStamina(character.getStamina() - 5);
		if (character.getStamina() < 0)
			character.setStamina(0);

		enemy.setHp(enemy.getHp() + dmg);
		if (enemy.getHp() < 0)
			enemy.setHp(0);
		hpBarE.setValue(enemy.getHp());
		hpLabelE.setText("HP: " + enemy.getHp() + "/100");
		attackBut.setEnabled(false);
		defendBut.setEnabled(false);
		waitBut.setEnabled(false);

	}

	private void enemysMove(String Cdecision, String Edecision) {
		if (Edecision.compareTo("Attack") == 0) {
			int dmg = 0;
			if (Cdecision.compareTo("Attack") == 0 || Cdecision.compareTo("Wait") == 0) {
				dmg = character.getArmor() - enemy.makeAttack();
			} else {
				dmg = character.getArmor() + character.getDefence() - enemy.makeAttack();
			}
			if (dmg > 0)
				dmg = 0;

			character.setHp(character.getHp() + dmg);
			if (character.getHp() < 0)
				character.setHp(0);
			hpBar.setValue(character.getHp());
			hpLabel.setText("HP: " + character.getHp() + "/100");
			enemy.setStamina(enemy.getStamina() - 5);
		} else if (Edecision.compareTo("Defend") == 0) {
			enemy.setStamina(enemy.getStamina() - 3);
		} else {
			enemy.setStamina(enemy.getStamina() + 4);
		}

		attackBut.setEnabled(true);
		defendBut.setEnabled(true);
		waitBut.setEnabled(true);
	}

	// happens if enemy has 0 hp
	private void enemyLost() {
		character.setArenaLvl(character.getArenaLvl() + 1);
		character.setWealth(character.getWealth() + enemy.getAward());
		enemy.setHp(100);

		try {
			SaveAndRead sr = new SaveAndRead();
			sr.save(character, "saves/" + savefile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		City city = new City(character, savefile);
		dispose();
	}

	// happens if character has 0 hp
	private void characterLost() {
		dispose();
	}
	
	private void createCharacterBars() {
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(150, 700, 200, 30);
		getContentPane().add(healthBarPanel);

		hpBar = new JProgressBar(0, 100);
		hpBar.setPreferredSize(new Dimension(200, 30));
		hpBar.setBackground(Color.black);
		hpBar.setForeground(Color.red);
		healthBarPanel.add(hpBar);
		hpBar.setValue(character.getHp());

		hpLabel = new JLabel("HP: " + character.getHp() + "/100");
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabel.setBounds(50, 700, 90, 30);
		getContentPane().add(hpLabel);
		
		staminaBarPanel = new JPanel();
		staminaBarPanel.setBounds(500, 700, 200, 30);
		getContentPane().add(staminaBarPanel);
		
		sBar = new JProgressBar(0, character.getMaxStamina());
		sBar.setPreferredSize(new Dimension(200, 30));
		sBar.setBackground(Color.DARK_GRAY);
		sBar.setForeground(Color.green);
		staminaBarPanel.add(sBar);
		sBar.setValue(character.getStamina());
		
		sLabel = new JLabel("Stamina: " + character.getStamina() + "/" + character.getMaxStamina());
		sLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sLabel.setBounds(400, 700, 90, 30);
		getContentPane().add(sLabel);
	}
	
	private void createEnemyBars() {
		healthBarPanelE = new JPanel();
		healthBarPanelE.setBounds(150, 100, 200, 30);
		getContentPane().add(healthBarPanelE);

		hpBarE = new JProgressBar(0, 100);
		hpBarE.setPreferredSize(new Dimension(200, 30));
		hpBarE.setBackground(Color.black);
		hpBarE.setForeground(Color.red);
		healthBarPanelE.add(hpBarE);
		hpBarE.setValue(enemy.getHp());

		hpLabelE = new JLabel("HP: " + enemy.getHp() + "/100");
		hpLabelE.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabelE.setBounds(50, 100, 90, 30);
		getContentPane().add(hpLabelE);
		
		staminaBarPanelE = new JPanel();
		staminaBarPanelE.setBounds(500, 100, 200, 30);
		getContentPane().add(staminaBarPanelE);
		
		sBarE = new JProgressBar(0, enemy.getMaxStamina());
		sBarE.setPreferredSize(new Dimension(200, 30));
		sBarE.setBackground(Color.DARK_GRAY);
		sBarE.setForeground(Color.green);
		staminaBarPanelE.add(sBarE);
		sBarE.setValue(enemy.getStamina());
		
		sLabelE = new JLabel("Stamina: " + enemy.getStamina() + "/" + enemy.getMaxStamina());
		sLabelE.setHorizontalAlignment(SwingConstants.CENTER);
		sLabelE.setBounds(400, 100, 90, 30);
		getContentPane().add(sLabelE);
		System.out.println("aaa");
	}
}
