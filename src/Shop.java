import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 * Place where player can buy new artifacts
 * 
 * @author MiSobecki
 *
 */
public class Shop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3154264130679785063L;
	private ArrayList<ArtifactTemplate> randomList;
	private ArrayList<ArtifactTemplate> allArtifacts;

	private JLabel artLabel1, artLabel2, artLabel3, artLabel4, artLabel5, artLabel6;
	private JButton artBut1, artBut2, artBut3, artBut4, artBut5, artBut6;
	private JLabel cashLabel, armorLabel, attackLabel, defenceLabel;
	private JToolBar toolBar;
	private JButton exitBut, returnBut, menuBut, refreshBut;

	private Character character;
	private String savefile;

	public Shop(Character character, String savefile) {
		this.character = character;
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		setAllArtifacts();
		setRandomList();

		setupArtifactsToBuy();

		setupStats();

		setupRefreshBut();

		setupToolBar();

		// Setting to frame
		setTitle("Shop");
		setLocation(450, 100);
		setResizable(false);
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setFocusable(true);
	}

	/**
	 * Creates List of all available artifacts in game
	 */
	private void setAllArtifacts() {
		allArtifacts = new ArrayList<ArtifactTemplate>();
		allArtifacts.add(new PracticeShield(new Artifact()));
		allArtifacts.add(new PracticeSword(new Artifact()));
		allArtifacts.add(new IronSword(new Artifact()));
		allArtifacts.add(new LeatherArmor(new Artifact()));
		allArtifacts.add(new PlateArmor(new Artifact()));
		allArtifacts.add(new LeatherShoes(new Artifact()));
	}

	/**
	 * Load Random List of artifacts from savefile
	 */
	private void setRandomList() {
		SaveAndRead sr = new SaveAndRead();
		
		try {
			randomList = (ArrayList<ArtifactTemplate>) sr.readShop("Shop");
		} catch (Exception e) {
			createNewRandList();

			e.printStackTrace();
		}
	}

	/**
	 * Creates new Random List of artifacts
	 */
	private void createNewRandList() {
		randomList = new ArrayList<ArtifactTemplate>();
		int x;

		for (int i = 0; i < allArtifacts.size(); i++) {
			x = ThreadLocalRandom.current().nextInt(0, allArtifacts.size());
			if (!randomList.contains(allArtifacts.get(x))) {
				randomList.add(allArtifacts.get(x));
			} else
				i = i - 1;
		}
	}

	/**
	 * Actions which happer after BUY Button is pressed
	 * 
	 * @param i
	 */
	private void buyAction(int i) {
		ArtifactTemplate temp = randomList.get(i);

		switch (i) {
		case 0:
			artBut1.setEnabled(false);
			break;
		case 1:
			artBut2.setEnabled(false);
			break;
		case 2:
			artBut3.setEnabled(false);
			break;
		case 3:
			artBut4.setEnabled(false);
			break;
		case 4:
			artBut5.setEnabled(false);
			break;
		case 5:
			artBut6.setEnabled(false);
			break;
		default:
			System.out.println("Such artifact doesn't exist");
		}

		character.addArtifact(temp);
		character.setWealth(character.getWealth() - temp.getCost());
		cashLabel.setText("Cash: " + character.getWealth());

		if (temp.getType() == "Right-hand") {
			attackLabel.setText("Attack: " + character.getAttack());
		} else if (temp.getType() == "Left-hand") {
			defenceLabel.setText("Defence: " + character.getDefence());
		} else
			armorLabel.setText("Armor: " + character.getArmor());
	}
	
	/**
	 * Creates Labels and Button with artifacts to buy
	 */
	private void setupArtifactsToBuy() {
		artLabel1 = new JLabel(randomList.get(0).getName());
		artLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel1.setBounds(80, 170, 200, 30);
		getContentPane().add(artLabel1);
		artLabel1.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artLabel1.setBackground(Color.black);
		artLabel1.setForeground(Color.red);

		artBut1 = new JButton("BUY");
		artBut1.setToolTipText("Cost: " + randomList.get(0).getCost());
		artBut1.setBounds(80, 210, 200, 30);
		if (character.containArtifact(randomList.get(0).getName())
				|| character.getWealth() < randomList.get(0).getCost()) {
			artBut1.setEnabled(false);
		}
		getContentPane().add(artBut1);
		artBut1.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artBut1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(0);
			}
		});
		artBut1.setBackground(Color.black);
		artBut1.setForeground(Color.red);

		artLabel2 = new JLabel(randomList.get(1).getName());
		artLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel2.setBounds(720, 170, 200, 30);
		getContentPane().add(artLabel2);
		artLabel2.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artLabel2.setBackground(Color.black);
		artLabel2.setForeground(Color.red);

		artBut2 = new JButton("BUY");
		artBut2.setToolTipText("Cost: " + randomList.get(1).getCost());
		artBut2.setBounds(720, 210, 200, 30);
		if (character.containArtifact(randomList.get(1).getName())
				|| character.getWealth() < randomList.get(1).getCost()) {
			artBut2.setEnabled(false);
		}
		getContentPane().add(artBut2);
		artBut2.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artBut2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(1);
			}
		});
		artBut2.setBackground(Color.black);
		artBut2.setForeground(Color.red);

		artLabel3 = new JLabel(randomList.get(2).getName());
		artLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel3.setBounds(80, 420, 200, 30);
		getContentPane().add(artLabel3);
		artLabel3.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artLabel3.setBackground(Color.black);
		artLabel3.setForeground(Color.red);

		artBut3 = new JButton("BUY");
		artBut3.setToolTipText("Cost: " + randomList.get(2).getCost());
		artBut3.setBounds(80, 460, 200, 30);
		if (character.containArtifact(randomList.get(2).getName())
				|| character.getWealth() < randomList.get(2).getCost()) {
			artBut3.setEnabled(false);
		}
		getContentPane().add(artBut3);
		artBut3.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artBut3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(2);
			}
		});
		artBut3.setBackground(Color.black);
		artBut3.setForeground(Color.red);

		artLabel4 = new JLabel(randomList.get(3).getName());
		artLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel4.setBounds(720, 420, 200, 30);
		getContentPane().add(artLabel4);
		artLabel4.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artLabel4.setBackground(Color.black);
		artLabel4.setForeground(Color.red);

		artBut4 = new JButton("BUY");
		artBut4.setToolTipText("Cost: " + randomList.get(3).getCost());
		artBut4.setBounds(720, 460, 200, 30);
		if (character.containArtifact(randomList.get(3).getName())
				|| character.getWealth() < randomList.get(3).getCost()) {
			artBut4.setEnabled(false);
		}
		getContentPane().add(artBut4);
		artBut4.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artBut4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(3);
			}
		});
		artBut4.setBackground(Color.black);
		artBut4.setForeground(Color.red);

		artLabel5 = new JLabel(randomList.get(4).getName());
		artLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel5.setBounds(400, 500, 200, 30);
		getContentPane().add(artLabel5);
		artLabel5.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artLabel5.setBackground(Color.black);
		artLabel5.setForeground(Color.red);

		artBut5 = new JButton("BUY");
		artBut5.setToolTipText("Cost: " + randomList.get(4).getCost());
		artBut5.setBounds(400, 540, 200, 30);
		if (character.containArtifact(randomList.get(4).getName())
				|| character.getWealth() < randomList.get(4).getCost()) {
			artBut5.setEnabled(false);
		}
		getContentPane().add(artBut5);
		artBut5.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artBut5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(4);
			}
		});
		artBut5.setBackground(Color.black);
		artBut5.setForeground(Color.red);

		artLabel6 = new JLabel(randomList.get(5).getName());
		artLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel6.setBounds(400, 100, 200, 30);
		getContentPane().add(artLabel6);
		artLabel6.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artLabel6.setBackground(Color.black);
		artLabel6.setForeground(Color.red);

		artBut6 = new JButton("BUY");
		artBut6.setToolTipText("Cost: " + randomList.get(5).getCost());
		artBut6.setBounds(400, 141, 200, 30);
		if (character.containArtifact(randomList.get(5).getName())
				|| character.getWealth() < randomList.get(5).getCost()) {
			artBut6.setEnabled(false);
		}
		getContentPane().add(artBut6);
		artBut6.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		artBut6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(5);
			}
		});
		artBut6.setBackground(Color.black);
		artBut6.setForeground(Color.red);
	}
	
	/**
	 * Creates Labels with Character stats
	 */
	private void setupStats() {
		cashLabel = new JLabel("Cash: " + character.getWealth());
		cashLabel.setBounds(450, 378, 100, 30);
		cashLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		getContentPane().add(cashLabel);
		cashLabel.setBackground(Color.black);
		cashLabel.setForeground(Color.red);

		armorLabel = new JLabel("Armor: " + character.getArmor());
		armorLabel.setBounds(320, 10, 100, 30);
		armorLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		getContentPane().add(armorLabel);
		armorLabel.setBackground(Color.black);
		armorLabel.setForeground(Color.red);

		defenceLabel = new JLabel("Defence: " + character.getDefence());
		defenceLabel.setBounds(510, 10, 100, 30);
		defenceLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		getContentPane().add(defenceLabel);
		defenceLabel.setBackground(Color.black);
		defenceLabel.setForeground(Color.red);

		attackLabel = new JLabel("Attack: " + character.getAttack());
		attackLabel.setBounds(700, 10, 100, 30);
		attackLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		getContentPane().add(attackLabel);
		attackLabel.setBackground(Color.black);
		attackLabel.setForeground(Color.red);
	}
	
	/**
	 * Creates ToolBar
	 */
	private void setupToolBar() {
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 178, 23);
		getContentPane().add(toolBar);
		toolBar.setBackground(Color.black);
		toolBar.setForeground(Color.red);

		// Button to exit game
		exitBut = new JButton("Exit");
		toolBar.add(exitBut);
		exitBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, "saves/" + savefile);
					sr.saveShop(randomList, "Shop");
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		exitBut.setBackground(Color.black);
		exitBut.setForeground(Color.red);

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
					sr.saveShop(randomList, "Shop");
				} catch (IOException e) {
					e.printStackTrace();
				}
				new Menu();
				dispose();
			}
		});
		menuBut.setBackground(Color.black);
		menuBut.setForeground(Color.red);

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
					sr.saveShop(randomList, "Shop");
				} catch (IOException e) {
					e.printStackTrace();
				}

				new City(character, savefile);
				dispose();
			}
		});
		returnBut.setBackground(Color.black);
		returnBut.setForeground(Color.red);
	}
	
	/**
	 * Creates refresh button which refresh list of artifacts to buy
	 */
	private void setupRefreshBut() {
		refreshBut = new JButton("Refresh");
		refreshBut.setToolTipText("Cost: 500");
		refreshBut.setBounds(430, 269, 100, 51);
		getContentPane().add(refreshBut);
		refreshBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		refreshBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				createNewRandList();
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.saveShop(randomList, "Shop");
				} catch (IOException e) {
					e.printStackTrace();
				}
				getContentPane().removeAll();
				initialize();
			}
		});
		refreshBut.setBackground(Color.black);
		refreshBut.setForeground(Color.red);
		
		if (character.getWealth() < 500)
			refreshBut.setEnabled(false);
	}
}
