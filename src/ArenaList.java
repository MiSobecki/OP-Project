import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

/**
 * List to choose enemy which player want to fight
 * 
 * @author MiSobecki
 *
 */
public class ArenaList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8316110784225205041L;
	private JList<Enemy> list;
	private DefaultListModel<Enemy> defaultList;
	private JButton fightBut, exitBut, menuBut, returnBut;
	private JToolBar toolBar;
	private JScrollPane scroll;

	private Character character;
	private String savefile;

	public ArenaList(Character character, String savefile) {
		this.character = character;
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(null);

		createToolBar();

		createModel(new EnemiesList(character));

		setupScrolledList();

		// Button to start fighting enemy
		fightBut = new JButton("FIGHT");
		fightBut.setBounds(250, 460, 100, 50);
		fightBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		getContentPane().add(fightBut);
		fightBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Enemy temp = list.getSelectedValue();
				if (!temp.isLocked()) {
					new Arena(character, temp, savefile);
					dispose();
				}
			}
		});
		fightBut.setBackground(Color.black);
		fightBut.setForeground(Color.red);

		// Setting to frame
		setTitle("Arena List");
		setLocation(450, 100);
		setResizable(false);
		setSize(600, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setFocusable(true);
	}

	/**
	 * Creates defaultListModel for JList
	 * 
	 * @param enemiesList
	 */
	private void createModel(EnemiesList enemiesList) {
		defaultList = new DefaultListModel<Enemy>();
		for (Enemy a : enemiesList) {
			if (a.isLocked()) {
				a.setDescription("<html><font color = gray>" + a.getDescription() + "</font></html>");
			}
			defaultList.addElement(a);
		}
	}

	/**
	 * Creates ToolBar
	 */
	private void createToolBar() {
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 172, 35);
		getContentPane().add(toolBar);
		toolBar.setBackground(Color.black);
		toolBar.setForeground(Color.red);

		//Button to exit game
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
		exitBut.setBackground(Color.black);
		exitBut.setForeground(Color.red);

		//Button to return to the menu
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

				new Menu();
				dispose();
			}
		});
		menuBut.setBackground(Color.black);
		menuBut.setForeground(Color.red);

		//Button to return to the previous window
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

				new City(character, savefile);
				dispose();
			}
		});
		returnBut.setBackground(Color.black);
		returnBut.setForeground(Color.red);
	}

	/**
	 * Creates ScrolledList for JList
	 */
	private void setupScrolledList() {
		scroll = new JScrollPane();
		scroll.setBounds(100, 50, 400, 400);
		getContentPane().add(scroll);
		list = new JList<Enemy>();
		scroll.setViewportView(list);
		list.setModel(defaultList);
		list.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(Color.black);
		list.setForeground(Color.red);
	}
}
