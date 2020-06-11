import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class ArenaList extends JFrame {

	private JList<Enemy> list;
	private DefaultListModel<Enemy> defaultList;
	private JButton fightBut, exitBut, menuBut, returnBut;
	private JToolBar toolBar;

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

		list = new JList<Enemy>();
		list.setModel(defaultList);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBounds(100, 50, 400, 400);
		getContentPane().add(list);

		// Button to start fighting enemy
		fightBut = new JButton("FIGHT");
		fightBut.setBounds(250, 460, 100, 50);
		getContentPane().add(fightBut);
		fightBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Enemy temp = list.getSelectedValue();
				@SuppressWarnings("unused")
				Arena arena = new Arena(character, temp, savefile);
				dispose();
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

	// create defaultModelList
	private void createModel(EnemiesList enemiesList) {
		defaultList = new DefaultListModel<Enemy>();
		for (Enemy a : enemiesList) {
			defaultList.addElement(a);
		}
	}
}
