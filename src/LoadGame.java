import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class LoadGame extends JFrame {

	private JLabel label;
	private JList<String> list;
	private JButton selectBut, returnBut;
	private DefaultListModel<String> model;

	private ArrayList<String> al;

	public LoadGame() {
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		label = new JLabel("Select game save to load");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(125, 20, 250, 40);
		getContentPane().add(label);

		createModel();

		list = new JList<String>();
		list.setModel(model);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBounds(100, 100, 300, 380);
		getContentPane().add(list);

		selectBut = new JButton("Load save");
		selectBut.setBounds(293, 502, 107, 40);
		getContentPane().add(selectBut);
		selectBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				selectSave();
			}
		});

		returnBut = new JButton("Return to the menu");
		returnBut.setBounds(100, 502, 142, 40);
		getContentPane().add(returnBut);
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				@SuppressWarnings("unused")
				Menu menu = new Menu();
				dispose();
			}
		});

		// Frame settings
		setLocation(800, 400);
		setSize(500, 600);
		setResizable(false);
		setTitle("Load Game");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createModel() {
		SaveAndRead sr = new SaveAndRead();
		model = new DefaultListModel<String>();

		try {
			al = sr.readSaves("Saves");
		} catch (Exception e) {
			al = new ArrayList<String>();
			e.printStackTrace();
		}

		for (String s : al) {
			model.addElement(s);
		}
	}

	private void selectSave() {
		String temp = list.getSelectedValue();
		SaveAndRead sr = new SaveAndRead();

		try {
			Character character = (Character) sr.read("saves/" + temp);
			@SuppressWarnings("unused")
			City city = new City(character, temp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ups, save doesn't exist, Sorry!\n" + "Deleting corrupted save and" + "Moving to main menu...",
					"Loading Error", JOptionPane.ERROR_MESSAGE);

			al.remove(temp);
			try {
				sr.saveSaves(al, "Saves");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			@SuppressWarnings("unused")
			Menu menu = new Menu();

			e.printStackTrace();
		}

		dispose();
	}
}
