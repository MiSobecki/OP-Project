import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class LoadGame extends JFrame {

	private JLabel label;
	private JList<String> list;
	private JButton selectBut, returnBut;
	private DefaultListModel<String> model;
	private JScrollPane scroll;

	private ArrayList<String> al;

	public LoadGame() {
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		label = new JLabel("Select game save to load");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		label.setBounds(125, 20, 250, 40);
		getContentPane().add(label);

		createModel();

		scroll = new JScrollPane();
		scroll.setBounds(100, 100, 300, 380);
		getContentPane().add(scroll);
		list = new JList<String>();
		scroll.setViewportView(list);
		list.setModel(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		list.setLayoutOrientation(JList.VERTICAL);

		selectBut = new JButton("Load save");
		selectBut.setBounds(293, 502, 107, 40);
		getContentPane().add(selectBut);
		selectBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		selectBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				selectSave();
			}
		});

		returnBut = new JButton("Return to the menu");
		returnBut.setBounds(100, 502, 142, 40);
		getContentPane().add(returnBut);
		returnBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Menu();
				dispose();
			}
		});

		// Frame settings
		setLocation(700, 300);
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
			new City(character, temp);
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

			new Menu();

			e.printStackTrace();
		}

		dispose();
	}
}
