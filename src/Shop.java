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

public class Shop extends JFrame {

	private ArrayList<ArtifactTemplate> randomList;
	private ArrayList<ArtifactTemplate> allArtifacts;

	private JLabel artLabel1, artLabel2, artLabel3, artLabel4, artLabel5, artLabel6;
	private JButton artBut1, artBut2, artBut3, artBut4, artBut5, artBut6;
	private JLabel cashLabel, armorLabel, attackLabel, defenceLabel;
	private JToolBar toolBar;
	private JButton exitBut, returnBut, menuBut, refreshBut;

	private Character character;

	public Shop(Character character) {
		this.character = character;
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		setAllArtifacts();
		setRandomList();

		// Setting of available artifacts to buy
		artLabel1 = new JLabel(randomList.get(0).getName());
		artLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel1.setBounds(80, 170, 100, 30);
		getContentPane().add(artLabel1);
		artBut1 = new JButton("BUY");
		artBut1.setBounds(80, 210, 100, 30);
		if (character.containArtifact(randomList.get(0).getName())
				|| character.getWealth() < randomList.get(0).getCost()) {
			artBut1.setEnabled(false);
		}
		getContentPane().add(artBut1);
		artBut1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(0);
			}
		});

		artLabel2 = new JLabel(randomList.get(1).getName());
		artLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel2.setBounds(520, 170, 100, 30);
		getContentPane().add(artLabel2);
		artBut2 = new JButton("BUY");
		artBut2.setBounds(520, 210, 100, 30);
		if (character.containArtifact(randomList.get(1).getName())
				|| character.getWealth() < randomList.get(1).getCost()) {
			artBut2.setEnabled(false);
		}
		getContentPane().add(artBut2);
		artBut2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(1);
			}
		});

		artLabel3 = new JLabel(randomList.get(2).getName());
		artLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel3.setBounds(80, 420, 100, 30);
		getContentPane().add(artLabel3);
		artBut3 = new JButton("BUY");
		artBut3.setBounds(80, 460, 100, 30);
		if (character.containArtifact(randomList.get(2).getName())
				|| character.getWealth() < randomList.get(2).getCost()) {
			artBut3.setEnabled(false);
		}
		getContentPane().add(artBut3);
		artBut3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(2);
			}
		});

		artLabel4 = new JLabel(randomList.get(3).getName());
		artLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel4.setBounds(520, 420, 100, 30);
		getContentPane().add(artLabel4);
		artBut4 = new JButton("BUY");
		artBut4.setBounds(520, 460, 100, 30);
		if (character.containArtifact(randomList.get(3).getName())
				|| character.getWealth() < randomList.get(3).getCost()) {
			artBut4.setEnabled(false);
		}
		getContentPane().add(artBut4);
		artBut4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(3);
			}
		});

		artLabel5 = new JLabel(randomList.get(4).getName());
		artLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel5.setBounds(300, 500, 100, 30);
		getContentPane().add(artLabel5);
		artBut5 = new JButton("BUY");
		artBut5.setBounds(300, 540, 100, 30);
		if (character.containArtifact(randomList.get(4).getName())
				|| character.getWealth() < randomList.get(4).getCost()) {
			artBut5.setEnabled(false);
		}
		getContentPane().add(artBut5);
		artBut5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(4);
			}
		});

		artLabel6 = new JLabel(randomList.get(5).getName());
		artLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		artLabel6.setBounds(300, 100, 100, 30);
		getContentPane().add(artLabel6);
		artBut6 = new JButton("BUY");
		artBut6.setBounds(300, 140, 100, 30);
		if (character.containArtifact(randomList.get(5).getName())
				|| character.getWealth() < randomList.get(5).getCost()) {
			artBut6.setEnabled(false);
		}
		getContentPane().add(artBut6);
		artBut6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				buyAction(5);
			}
		});

		// Labels informing about character's statistics
		cashLabel = new JLabel("Cash: " + character.getWealth());
		cashLabel.setBounds(300, 380, 100, 30);
		cashLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(cashLabel);

		armorLabel = new JLabel("Armor: " + character.getArmor());
		armorLabel.setBounds(170, 10, 100, 30);
		armorLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(armorLabel);

		defenceLabel = new JLabel("Defence: " + character.getDefence());
		defenceLabel.setBounds(300, 10, 100, 30);
		defenceLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(defenceLabel);

		attackLabel = new JLabel("Attack: " + character.getAttack());
		attackLabel.setBounds(430, 10, 100, 30);
		attackLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(attackLabel);

		// Button to refresh list of available artifacts
		refreshBut = new JButton("Refresh");
		refreshBut.setBounds(300, 270, 100, 51);
		getContentPane().add(refreshBut);
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
					sr.save(character, "Character2");
					sr.saveShop(randomList, "Shop");
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
					sr.save(character, "Character2");
					sr.saveShop(randomList, "Shop");
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
					sr.save(character, "Character2");
					sr.saveShop(randomList, "Shop");
				} catch (IOException e) {
					e.printStackTrace();
				}

				@SuppressWarnings("unused")
				City city = new City(character);
				dispose();
			}
		});

		// Setting to frame
		setTitle("Shop");
		setLocation(450, 100);
		setResizable(false);
		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setAllArtifacts() {
		allArtifacts = new ArrayList<ArtifactTemplate>();
		allArtifacts.add(new PracticeShield(new Artifact()));
		allArtifacts.add(new PracticeSword(new Artifact()));
		allArtifacts.add(new IronSword(new Artifact()));
		allArtifacts.add(new LeatherArmor(new Artifact()));
		allArtifacts.add(new PlateArmor(new Artifact()));
		allArtifacts.add(new LeatherShoes(new Artifact()));
	}

	private void setRandomList() {
		SaveAndRead sr = new SaveAndRead();
		try {
			randomList = (ArrayList<ArtifactTemplate>) sr.readShop("Shop");
		} catch (Exception e) {
			createNewRandList();

			e.printStackTrace();
		}
	}

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

	private void buyAction(int i) {
		ArtifactTemplate temp = randomList.get(i);

		switch (i) {
		case 1:
			artBut1.setEnabled(false);
			break;
		case 2:
			artBut2.setEnabled(false);
			break;
		case 3:
			artBut3.setEnabled(false);
			break;
		case 4:
			artBut4.setEnabled(false);
			break;
		case 5:
			artBut5.setEnabled(false);
			break;
		case 6:
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
}
