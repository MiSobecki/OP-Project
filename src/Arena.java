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
import javax.swing.JButton;

public class Arena extends JFrame {
	
	private JPanel healthBarPanel, healthBarPanelE;
	private JProgressBar hpBar, hpBarE;
	private JLabel hpLabel, hpLabelE, enemyLab;
	private JButton attackBut;
	
	private Character character;
	private Enemy enemy;

	
	public Arena(Character character, Enemy enemy) {
		this.character = character;
		this.enemy = enemy;
		initialize();
	}
	
	
	private void initialize() {
		getContentPane().setLayout(null);
		
		//Character HP bar
		healthBarPanel = new JPanel();
		healthBarPanel.setBounds(300, 700, 200, 30);
		getContentPane().add(healthBarPanel);
		
		hpBar = new JProgressBar(0, 100);
		hpBar.setPreferredSize(new Dimension(200, 30));
		hpBar.setBackground(Color.red);
		hpBar.setForeground(Color.green);
		healthBarPanel.add(hpBar);
		hpBar.setValue(character.getHp());
		
		hpLabel = new JLabel("HP: " + character.getHp() + "/100");
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabel.setBounds(200, 700, 90, 30);
		getContentPane().add(hpLabel);
		
		//Enemy HP bar
		healthBarPanelE = new JPanel();
		healthBarPanelE.setBounds(300, 100, 200, 30);
		getContentPane().add(healthBarPanelE);
		
		hpBarE = new JProgressBar(0, 100);
		hpBarE.setPreferredSize(new Dimension(200, 30));
		hpBarE.setBackground(Color.red);
		hpBarE.setForeground(Color.green);
		healthBarPanelE.add(hpBarE);
		hpBarE.setValue(enemy.getHp());
		
		hpLabelE = new JLabel("HP: " + enemy.getHp() + "/100");
		hpLabelE.setHorizontalAlignment(SwingConstants.CENTER);
		hpLabelE.setBounds(200, 100, 90, 30);
		getContentPane().add(hpLabelE);
		
		
		//Enemy portrait
		enemyLab = new JLabel(enemy.getName());
		enemyLab.setHorizontalAlignment(SwingConstants.CENTER);
		enemyLab.setBounds(300, 11, 200, 78);
		getContentPane().add(enemyLab);
		
		
		//Attack button
		attackBut = new JButton("Attack");
		attackBut.setBounds(240, 633, 89, 23);
		getContentPane().add(attackBut);
		attackBut.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	attackAction();
            }
        });
		
		
		//Setting to frame
		setTitle("Arena List");
        setLocation(450, 100);
        setResizable(false);
        setSize(800, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//actions after attackBut pressed
	private void attackAction() {
		int dmg = enemy.getArmor() - character.makeAttack();
		if(dmg > 0 ) dmg = 0;
		
		enemy.setHp(enemy.getHp() - dmg);
		if(enemy.getHp() < 0) enemy.setHp(0);
    	hpBarE.setValue(enemy.getHp());
    	hpLabelE.setText("HP: " + enemy.getHp() + "/100");
    	attackBut.setEnabled(false);
    	
    	if(enemy.getHp() == 0) {
    		character.setArenaLvl(character.getArenaLvl() + 1);
    		character.setWealth(character.getWealth() + enemy.getAward());
    		enemy.setHp(100);
        	try {
        		SaveAndRead sr = new SaveAndRead();
				sr.save(character, "Character2");
			} catch (IOException e) {
				e.printStackTrace();
			}
    		@SuppressWarnings("unused")
			City city = new City(character);
    		dispose();
    	}
    	else {
    		Timer timer = new Timer(1000, new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				int dmg = character.getArmor() - enemy.makeAttack();
    				if(dmg > 0 ) dmg = 0;
    				
    				character.setHp(character.getHp() - dmg);
    				if(character.getHp() < 0) character.setHp(0);
    				hpBar.setValue(character.getHp());
    				hpLabel.setText("HP: " + character.getHp() + "/100");
    				attackBut.setEnabled(true);
    			}});
    	
    		timer.setRepeats(false);
    		timer.start();
    		
    		if(character.getHp() == 0) {
    			dispose();
    		}
    		
    	}
	}
}
