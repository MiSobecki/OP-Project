import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 3556826648760581683L;
	private JPanel healthBarPanel, healthBarPanelE, staminaBarPanel, staminaBarPanelE;
	private JProgressBar hpBar, hpBarE, sBar, sBarE;
	private JLabel hpLabel, hpLabelE, sLabel, sLabelE, enemyLab, actionLab;
	private JLabel upArrowLab, downArrowLab, CDmgLab, EDmgLab;
	private JButton attackBut, defendBut, waitBut;

	private Character character;
	private Enemy enemy;
	private String savefile;
	private JLabel nameLab;

	public Arena(Character character, Enemy enemy, String savefile) {
		this.character = character;
		this.enemy = enemy;
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(null);

		createCharacterBars();

		createEnemyBars();

		// Enemy portrait
		enemyLab = new JLabel();
		ImageIcon img = new ImageIcon(enemy.getImg());
		enemyLab.setIcon(img);
		enemyLab.setBounds(325, 11, 150, 180);
		getContentPane().add(enemyLab);

		setupArrows();
		
		setupDmgLabs();

		// Attack button
		attackBut = new JButton("Attack");
		attackBut.setBounds(119, 626, 168, 30);
		getContentPane().add(attackBut);
		attackBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		attackBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				attackAction();
			}
		});
		attackBut.setBackground(Color.black);
		attackBut.setForeground(Color.red);

		// Defend button
		defendBut = new JButton("Defend");
		defendBut.setBounds(297, 626, 168, 30);
		getContentPane().add(defendBut);
		defendBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		defendBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				defendAction();
			}
		});
		defendBut.setBackground(Color.black);
		defendBut.setForeground(Color.red);

		// Wait button
		waitBut = new JButton("Wait");
		waitBut.setBounds(475, 626, 168, 30);
		getContentPane().add(waitBut);
		waitBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		waitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				waitAction();
			}
		});
		waitBut.setBackground(Color.black);
		waitBut.setForeground(Color.red);

		// Label to inform what enemy do
		actionLab = new JLabel("");
		actionLab.setHorizontalAlignment(SwingConstants.CENTER);
		actionLab.setBounds(556, 88, 150, 56);
		getContentPane().add(actionLab);
		actionLab.setFont(new Font("Enchanted Land", Font.PLAIN, 40));
		actionLab.setBackground(Color.black);
		actionLab.setForeground(Color.red);

		// Label showing enemy Name
		nameLab = new JLabel(enemy.getName());
		nameLab.setHorizontalAlignment(SwingConstants.CENTER);
		nameLab.setBounds(50, 88, 150, 56);
		getContentPane().add(nameLab);
		nameLab.setFont(new Font("Enchanted Land", Font.PLAIN, 40));
		nameLab.setBackground(Color.black);
		nameLab.setForeground(Color.red);

		// Setting to frame
		setTitle("Arena");
		setLocation(450, 100);
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setFocusable(true);
	}

	/**
	 * Actions after attackBut pressed
	 */
	private void attackAction() {
		upArrowLab.setVisible(true);

		String dec = enemy.makeDecision();
		actionLab.setText(dec);
		charactersAttack(dec);

		if (enemy.getHp() == 0) {
			enemyLost();
		} else {
			Timer timer = new Timer(1500, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					upArrowLab.setVisible(false);
					CDmgLab.setVisible(false);
					enemysMove("Attack", dec);
				}
			});

			timer.setRepeats(false);
			timer.start();

			secondTimer();
		}
	}

	/**
	 * Actions after defendBut pressed
	 */
	private void defendAction() {
		actionLab.setText(enemy.makeDecision());

		character.setStamina(character.getStamina() - 3);
		if (character.getStamina() < 0) {
			character.setStamina(0);
			sBar.setValue(character.getStamina());
			sLabel.setText("Stamina: " + character.getStamina() + "/" + character.getMaxStamina());
		}

		attackBut.setEnabled(false);
		defendBut.setEnabled(false);
		waitBut.setEnabled(false);

		Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enemysMove("Defend", enemy.makeDecision());
			}
		});

		timer.setRepeats(false);
		timer.start();

		secondTimer();
	}

	/**
	 * Actions after waitBut pressed
	 */
	private void waitAction() {
		actionLab.setText(enemy.makeDecision());

		character.setStamina(character.getStamina() + 4);

		if (character.getStamina() > character.getMaxStamina())
			character.setStamina(character.getMaxStamina());

		sBar.setValue(character.getStamina());
		sLabel.setText("Stamina: " + character.getStamina() + "/" + character.getMaxStamina());

		attackBut.setEnabled(false);
		defendBut.setEnabled(false);
		waitBut.setEnabled(false);

		Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enemysMove("Wait", enemy.makeDecision());
			}
		});

		timer.setRepeats(false);
		timer.start();

		secondTimer();
	}

	/**
	 * Character attack action
	 */
	private void charactersAttack(String Edecision) {
		int dmg = 0;
		if (Edecision.compareTo("Attack") == 0 || Edecision.compareTo("Wait") == 0) {
			dmg = enemy.getArmor() - character.makeAttack();
		} else {
			dmg = enemy.getArmor() + enemy.getDefence() - character.makeAttack();
		}
		if (dmg > 0)
			dmg = 0;
		
		CDmgLab.setText(String.valueOf(dmg));
		CDmgLab.setVisible(true);

		character.setStamina(character.getStamina() - 5);
		if (character.getStamina() < 0)
			character.setStamina(0);

		sBar.setValue(character.getStamina());
		sLabel.setText("Stamina: " + character.getStamina() + "/" + character.getMaxStamina());

		enemy.setHp(enemy.getHp() + dmg);
		if (enemy.getHp() < 0)
			enemy.setHp(0);
		hpBarE.setValue(enemy.getHp());
		hpLabelE.setText("HP: " + enemy.getHp() + "/100");
		attackBut.setEnabled(false);
		defendBut.setEnabled(false);
		waitBut.setEnabled(false);

	}

	/**
	 * Actions which enemy do
	 */
	private void enemysMove(String Cdecision, String Edecision) {
		if (Edecision.compareTo("Attack") == 0) {
			downArrowLab.setVisible(true);
			int dmg = 0;
			if (Cdecision.compareTo("Attack") == 0 || Cdecision.compareTo("Wait") == 0) {
				dmg = character.getArmor() - enemy.makeAttack();
			} else {
				dmg = character.getArmor() + character.getDefence() - enemy.makeAttack();
			}
			if (dmg > 0)
				dmg = 0;
			
			EDmgLab.setText(String.valueOf(dmg));
			EDmgLab.setVisible(true);

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

		sBarE.setValue(enemy.getStamina());
		sLabelE.setText("Stamina: " + enemy.getStamina() + "/" + enemy.getMaxStamina());
	}

	/**
	 * Happens if enemy has 0 hp, moves back to City window
	 */
	private void enemyLost() {
		character.setArenaLvl(character.getArenaLvl() + 1);
		character.setWealth(character.getWealth() + enemy.getAward());
		character.setStamina(character.getMaxStamina());
		enemy.setHp(100);

		try {
			SaveAndRead sr = new SaveAndRead();
			sr.save(character, "saves/" + savefile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		new City(character, savefile);
		dispose();
	}

	// happens if character has 0 hp
	private void characterLost() {
		new LostWindow(savefile);
		dispose();
	}

	/**
	 * Creates Character bars
	 */
	private void createCharacterBars() {
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(150, 700, 200, 30);
		getContentPane().add(healthBarPanel);
		healthBarPanel.setBackground(Color.black);

		hpBar = new JProgressBar(0, 100);
		hpBar.setPreferredSize(new Dimension(200, 30));
		hpBar.setBackground(Color.black);
		hpBar.setForeground(Color.red);
		healthBarPanel.add(hpBar);
		hpBar.setValue(character.getHp());

		hpLabel = new JLabel("HP: " + character.getHp() + "/100");
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabel.setBounds(22, 700, 118, 30);
		getContentPane().add(hpLabel);
		hpLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		hpLabel.setBackground(Color.black);
		hpLabel.setForeground(Color.red);

		staminaBarPanel = new JPanel();
		staminaBarPanel.setBounds(500, 700, 200, 30);
		getContentPane().add(staminaBarPanel);
		staminaBarPanel.setBackground(Color.black);

		sBar = new JProgressBar(0, character.getMaxStamina());
		sBar.setPreferredSize(new Dimension(200, 30));
		sBar.setBackground(Color.DARK_GRAY);
		sBar.setForeground(Color.green);
		staminaBarPanel.add(sBar);
		sBar.setValue(character.getStamina());

		sLabel = new JLabel("Stamina: " + character.getStamina() + "/" + character.getMaxStamina());
		sLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sLabel.setBounds(360, 700, 130, 30);
		getContentPane().add(sLabel);
		sLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		sLabel.setBackground(Color.black);
		sLabel.setForeground(Color.red);
	}

	/**
	 * Creates Enemy bars
	 */
	private void createEnemyBars() {
		healthBarPanelE = new JPanel();
		healthBarPanelE.setBounds(150, 200, 200, 30);
		getContentPane().add(healthBarPanelE);
		healthBarPanelE.setBackground(Color.black);

		hpBarE = new JProgressBar(0, 100);
		hpBarE.setPreferredSize(new Dimension(200, 30));
		hpBarE.setBackground(Color.black);
		hpBarE.setForeground(Color.red);
		healthBarPanelE.add(hpBarE);
		hpBarE.setValue(enemy.getHp());

		hpLabelE = new JLabel("HP: " + enemy.getHp() + "/100");
		hpLabelE.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabelE.setBounds(22, 200, 118, 30);
		getContentPane().add(hpLabelE);
		hpLabelE.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		hpLabelE.setBackground(Color.black);
		hpLabelE.setForeground(Color.red);

		staminaBarPanelE = new JPanel();
		staminaBarPanelE.setBounds(500, 200, 200, 30);
		getContentPane().add(staminaBarPanelE);
		staminaBarPanelE.setBackground(Color.black);

		sBarE = new JProgressBar(0, enemy.getMaxStamina());
		sBarE.setPreferredSize(new Dimension(200, 30));
		sBarE.setBackground(Color.DARK_GRAY);
		sBarE.setForeground(Color.green);
		staminaBarPanelE.add(sBarE);
		sBarE.setValue(enemy.getStamina());

		sLabelE = new JLabel("Stamina: " + enemy.getStamina() + "/" + enemy.getMaxStamina());
		sLabelE.setHorizontalAlignment(SwingConstants.CENTER);
		sLabelE.setBounds(360, 200, 130, 30);
		getContentPane().add(sLabelE);
		sLabelE.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		sLabelE.setBackground(Color.black);
		sLabelE.setForeground(Color.red);
	}

	/**
	 * Timer enabling the corresponding buttons
	 */
	private void secondTimer() {
		Timer timer2 = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (character.getStamina() >= 5)
					attackBut.setEnabled(true);
				if (character.getStamina() >= 3)
					defendBut.setEnabled(true);
				waitBut.setEnabled(true);
				actionLab.setText("");
				
				downArrowLab.setVisible(false);
				EDmgLab.setVisible(false);

				if (character.getHp() == 0) {
					characterLost();
				}
			}
		});

		timer2.setRepeats(false);
		timer2.start();
	}

	/**
	 * Creates up/down arrow Images
	 */
	private void setupArrows() {
		upArrowLab = new JLabel("");
		ImageIcon img1 = new ImageIcon("pictures/upArrow.gif");
		upArrowLab.setIcon(img1);
		upArrowLab.setBounds(150, 290, 193, 300);
		getContentPane().add(upArrowLab);
		upArrowLab.setVisible(false);
	
		downArrowLab = new JLabel("");
		ImageIcon img2 = new ImageIcon("pictures/downArrow.gif");
		downArrowLab.setIcon(img2);
		downArrowLab.setBounds(450, 290, 193, 300);
		getContentPane().add(downArrowLab);
		downArrowLab.setVisible(false);
	}
	
	/**
	 * Creates Labels with information about dealt damage
	 */
	private void setupDmgLabs() {
		CDmgLab = new JLabel("");
		CDmgLab.setHorizontalAlignment(SwingConstants.CENTER);
		CDmgLab.setBounds(50, 407, 90, 56);
		getContentPane().add(CDmgLab);
		CDmgLab.setVisible(false);
		CDmgLab.setFont(new Font("Enchanted Land", Font.PLAIN, 40));
		CDmgLab.setBackground(Color.black);
		CDmgLab.setForeground(Color.red);
		
		EDmgLab = new JLabel("");
		EDmgLab.setHorizontalAlignment(SwingConstants.CENTER);
		EDmgLab.setBounds(653, 407, 90, 56);
		getContentPane().add(EDmgLab);
		EDmgLab.setVisible(false);
		EDmgLab.setFont(new Font("Enchanted Land", Font.PLAIN, 40));
		EDmgLab.setBackground(Color.black);
		EDmgLab.setForeground(Color.red);
	}
}
