import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class ArenaList extends JFrame {

	private JList<Enemy> list;
	private DefaultListModel<Enemy> defaultList;
	private JButton fightBut, exitBut, menuBut, returnBut;
	private JToolBar toolBar;
	private JScrollPane scroll;

	public ArenaList(Character character, String savefile) {
		initialize(character, savefile);
	}

	private void initialize(Character character, String savefile) {
		getContentPane().setLayout(null);

		// ToolBar
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 150, 23);
		getContentPane().add(toolBar);

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
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});

		// Button to return to the menu
		menuBut = new JButton("Menu");
		menuBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		toolBar.add(menuBut);
		menuBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(character, "saves/" + savefile);
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

				@SuppressWarnings("unused")
				City city = new City(character, savefile);
				dispose();
			}
		});

		// List of enemies
		createModel(new EnemiesList(character));

		scroll = new JScrollPane();
		scroll.setBounds(100, 50, 400, 400);
		getContentPane().add(scroll);
		list = new JList<Enemy>();
		scroll.setViewportView(list);
		list.setModel(defaultList);
		list.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Button to start fighting enemy
		fightBut = new JButton("FIGHT");
		fightBut.setBounds(250, 460, 100, 50);
		fightBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		getContentPane().add(fightBut);
		fightBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Enemy temp = list.getSelectedValue();
				if (!temp.isLocked()) {
					@SuppressWarnings("unused")
					Arena arena = new Arena(character, temp, savefile);
					dispose();
				}
			}
		});

		// Setting to frame
		setTitle("Arena List");
		setLocation(450, 100);
		setResizable(false);
		setSize(600, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// create defaultListModel
	private void createModel(EnemiesList enemiesList) {
		defaultList = new DefaultListModel<Enemy>();
		for (Enemy a : enemiesList) {
			if (a.isLocked()) {
				a.setDescription("<html><font color = gray>" + a.getDescription() + "</font></html>");
			}
			defaultList.addElement(a);
		}
	}
}
