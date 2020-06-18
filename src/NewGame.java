import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

public class NewGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1116968410048841596L;
	private JTextField textField;
	private JLabel infoLabel;
	private JButton confirmBut, returnBut;

	public NewGame() {
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		infoLabel = new JLabel("Name your new save");
		infoLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(137, 20, 178, 30);
		getContentPane().add(infoLabel);

		confirmBut = new JButton("Confirm");
		confirmBut.setBounds(317, 134, 107, 37);
		confirmBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		getContentPane().add(confirmBut);
		confirmBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				confirmAction();
			}
		});

		textField = new JTextField();
		textField.setBounds(33, 134, 241, 37);
		textField.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		getContentPane().add(textField);
		textField.setColumns(10);

		returnBut = new JButton("Return to the menu");
		returnBut.setBounds(33, 210, 136, 37);
		returnBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		getContentPane().add(returnBut);
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Menu();
				dispose();
			}
		});

		// Frame settings
		setLocation(800, 400);
		setSize(450, 300);
		setResizable(false);
		setTitle("New Game");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void confirmAction() {
		String temp = textField.getText();
		ArrayList<String> list;
		SaveAndRead sr = new SaveAndRead();

		try {
			list = sr.readSaves("Saves");
		} catch (Exception e) {
			list = new ArrayList<String>();
			e.printStackTrace();
		}

		if (list.contains(temp)) {
			int o = JOptionPane.showConfirmDialog(null,
					"This name of the save already exists.\n" + "Do you want to overwrite it?", "Save overwite",
					JOptionPane.YES_NO_OPTION);
			if (o == 0) {
				new City(createNewCharacter(), temp);
			} else {
				new Menu();
			}
			dispose();
		} else {
			list.add(temp);

			try {
				sr.saveSaves(list, "Saves");
				sr.save(createNewCharacter(), "saves/" + temp);
			} catch (IOException e) {
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Creating new save was successful");

			 new City(createNewCharacter(), temp);
			dispose();
		}
	}

	private Character createNewCharacter() {
		Character character = Character.builder().hp(100).attack(3).wealth(0).arenaLvl(0).armor(0).defence(3)
				.artifacts(new ArrayList<ArtifactTemplate>()).addArrtifact(new PracticeSword(new Artifact()))
				.addArrtifact(new PracticeShield(new Artifact())).stamina(30).maxStamina(30).build();
		return character;
	}
}
