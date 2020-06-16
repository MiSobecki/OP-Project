import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.MatteBorder;

public class Inventory extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel headLab, chestLab, legsLab, handsLab, rightLab, leftLab;
	private JList<ArtifactTemplate> headList, chestList, legsList, handsList, rightList, leftList;
	private JScrollPane scrollPane1, scrollPane2, scrollPane3, scrollPane4, scrollPane5, scrollPane6;
	private DefaultListModel<ArtifactTemplate> model1, model2, model3, model4, model5, model6;
	private JToolBar toolBar;
	private JButton exitBut, menuBut, returnBut;
	private JLabel armorLabel, hpLabel, attackLabel, defenceLabel;

	private Character character;
	private MouseMotionAdapter mma;
	private String savefile;

	public Inventory(Character character, String savefile) {
		this.character = character;
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		createModels();

		createJListToolTips();

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
		toolBar.add(menuBut);
		menuBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
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

		// Button to return to the previous window
		returnBut = new JButton("Return");
		toolBar.add(returnBut);
		returnBut.setFont(new Font("Enchanted Land", Font.PLAIN, 20));
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Character temp = setCharacter();

				try {
					SaveAndRead sr = new SaveAndRead();
					sr.save(temp, "saves/" + savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				new City(temp, savefile);
				dispose();
			}
		});

		// Below are functions about graphical lookout for character

		// HEAD PART

		headList = new JList<ArtifactTemplate>();
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(570, 11, 200, 200);
		getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(headList);
		headList.setDragEnabled(true);
		headList.setTransferHandler(new ListTransferHandler());
		headList.setModel(model1);
		headList.setLayoutOrientation(JList.VERTICAL);
		headList.addMouseMotionListener(mma);
		headList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		headList.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		headLab = new JLabel("Head");
		if (character.getHead().getType().compareTo("None") != 0) {
			headLab.setText(character.getHead().getName());
			headLab.setToolTipText("<html>" + character.getHead().getType() + "<br>"
					+ character.getHead().getDescription() + "</html>");
		}
		headLab.setBounds(400, 30, 160, 48);
		headLab.setHorizontalAlignment(SwingConstants.CENTER);
		headLab.setBorder(new MatteBorder(4, 4, 4, 4, Color.red));
		headLab.setTransferHandler(new ListTransferHandler());
		getContentPane().add(headLab);
		headLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// CHEST PART
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(570, 221, 200, 200);
		getContentPane().add(scrollPane2);
		chestList = new JList<ArtifactTemplate>();
		scrollPane2.setViewportView(chestList);
		chestList.setDragEnabled(true);
		chestList.setTransferHandler(new ListTransferHandler());
		chestList.setModel(model2);
		chestList.setLayoutOrientation(JList.VERTICAL);
		chestList.addMouseMotionListener(mma);
		chestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		chestList.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		chestLab = new JLabel("Chest");
		if (character.getChest().getType().compareTo("None") != 0) {
			chestLab.setText(character.getChest().getName());
			chestLab.setToolTipText("<html>" + character.getChest().getType() + "<br>"
					+ character.getChest().getDescription() + "</html>");
		}
		chestLab.setHorizontalAlignment(SwingConstants.CENTER);
		chestLab.setBounds(400, 240, 160, 48);
		getContentPane().add(chestLab);
		chestLab.setBorder(new MatteBorder(4, 4, 4, 4, Color.red));
		chestLab.setTransferHandler(new ListTransferHandler());
		chestLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// LEGS PART
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(570, 431, 200, 200);
		getContentPane().add(scrollPane3);
		legsList = new JList<ArtifactTemplate>();
		scrollPane3.setViewportView(legsList);
		legsList.setDragEnabled(true);
		legsList.setTransferHandler(new ListTransferHandler());
		legsList.setModel(model3);
		legsList.setLayoutOrientation(JList.VERTICAL);
		legsList.addMouseMotionListener(mma);
		legsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		legsList.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		legsLab = new JLabel("Legs");
		if (character.getLegs().getType().compareTo("None") != 0) {
			legsLab.setText(character.getLegs().getName());
			legsLab.setToolTipText("<html>" + character.getLegs().getType() + "<br>"
					+ character.getLegs().getDescription() + "</html>");
		}
		legsLab.setHorizontalAlignment(SwingConstants.CENTER);
		legsLab.setBounds(400, 450, 160, 48);
		getContentPane().add(legsLab);
		legsLab.setBorder(new MatteBorder(4, 4, 4, 4, Color.red));
		legsLab.setTransferHandler(new ListTransferHandler());
		legsLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// HANDS PART
		scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(190, 11, 200, 200);
		getContentPane().add(scrollPane4);
		handsList = new JList<ArtifactTemplate>();
		scrollPane4.setViewportView(handsList);
		handsList.setDragEnabled(true);
		handsList.setTransferHandler(new ListTransferHandler());
		handsList.setModel(model4);
		handsList.setLayoutOrientation(JList.VERTICAL);
		handsList.addMouseMotionListener(mma);
		handsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		handsList.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		handsLab = new JLabel("Hands");
		if (character.getHands().getType().compareTo("None") != 0) {
			handsLab.setText(character.getHands().getName());
			handsLab.setToolTipText("<html>" + character.getHands().getType() + "<br>"
					+ character.getHands().getDescription() + "</html>");
		}
		handsLab.setHorizontalAlignment(SwingConstants.CENTER);
		handsLab.setBounds(20, 30, 160, 48);
		getContentPane().add(handsLab);
		handsLab.setBorder(new MatteBorder(4, 4, 4, 4, Color.red));
		handsLab.setTransferHandler(new ListTransferHandler());
		handsLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// RIGHT-HAND PART
		scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(190, 221, 200, 200);
		getContentPane().add(scrollPane5);
		rightList = new JList<ArtifactTemplate>();
		scrollPane5.setViewportView(rightList);
		rightList.setDragEnabled(true);
		rightList.setTransferHandler(new ListTransferHandler());
		rightList.setModel(model5);
		rightList.setLayoutOrientation(JList.VERTICAL);
		rightList.addMouseMotionListener(mma);
		rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rightList.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		rightLab = new JLabel("Right Hand");
		if (character.getRightHand().getType().compareTo("None") != 0) {
			rightLab.setText(character.getRightHand().getName());
			rightLab.setToolTipText("<html>" + character.getRightHand().getType() + "<br>"
					+ character.getRightHand().getDescription() + "</html>");
		}
		rightLab.setHorizontalAlignment(SwingConstants.CENTER);
		rightLab.setBounds(20, 240, 160, 48);
		getContentPane().add(rightLab);
		rightLab.setBorder(new MatteBorder(4, 4, 4, 4, Color.red));
		rightLab.setTransferHandler(new ListTransferHandler());
		rightLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// LEFT-HAND PART
		scrollPane6 = new JScrollPane();
		scrollPane6.setBounds(190, 431, 200, 200);
		getContentPane().add(scrollPane6);
		leftList = new JList<ArtifactTemplate>();
		scrollPane6.setViewportView(leftList);
		leftList.setDragEnabled(true);
		leftList.setTransferHandler(new ListTransferHandler());
		leftList.setModel(model6);
		leftList.setLayoutOrientation(JList.VERTICAL);
		leftList.addMouseMotionListener(mma);
		leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftList.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		leftLab = new JLabel("Left hand");
		if (character.getLeftHand().getType().compareTo("None") != 0) {
			leftLab.setText(character.getLeftHand().getName());
			leftLab.setToolTipText("<html>" + character.getLeftHand().getType() + "<br>"
					+ character.getLeftHand().getDescription() + "</html>");
		}
		leftLab.setHorizontalAlignment(SwingConstants.CENTER);
		leftLab.setBounds(20, 450, 160, 48);
		getContentPane().add(leftLab);
		leftLab.setBorder(new MatteBorder(4, 4, 4, 4, Color.red));
		leftLab.setTransferHandler(new ListTransferHandler());
		leftLab.setFont(new Font("Enchanted Land", Font.PLAIN, 25));

		// Statistics
		armorLabel = new JLabel("Armor: " + character.getArmor());
		armorLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		armorLabel.setBounds(820, 30, 103, 34);
		getContentPane().add(armorLabel);

		attackLabel = new JLabel("Attack: " + character.getAttack());
		attackLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		attackLabel.setBounds(820, 100, 103, 34);
		getContentPane().add(attackLabel);

		hpLabel = new JLabel("Hp: " + character.getHp() + "/100");
		hpLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		hpLabel.setBounds(820, 170, 103, 34);
		getContentPane().add(hpLabel);

		defenceLabel = new JLabel("Defence: " + character.getDefence());
		defenceLabel.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		defenceLabel.setBounds(820, 240, 103, 34);
		getContentPane().add(defenceLabel);

		// Setting to frame
		setTitle("Inventory");
		setLocation(450, 100);
		setResizable(false);
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// Setting character's items on body
	private Character setCharacter() {
		character.setHead(character.searchArtifactByName(headLab.getText()));
		character.setChest(character.searchArtifactByName(chestLab.getText()));
		character.setLegs(character.searchArtifactByName(legsLab.getText()));
		character.setHands(character.searchArtifactByName(handsLab.getText()));
		character.setRightHand(character.searchArtifactByName(rightLab.getText()));
		character.setLeftHand(character.searchArtifactByName(leftLab.getText()));

		return character;
	}

	// Creating models for JLists
	private void createModels() throws IllegalArgumentException {
		model1 = new DefaultListModel<ArtifactTemplate>();
		model2 = new DefaultListModel<ArtifactTemplate>();
		model3 = new DefaultListModel<ArtifactTemplate>();
		model4 = new DefaultListModel<ArtifactTemplate>();
		model5 = new DefaultListModel<ArtifactTemplate>();
		model6 = new DefaultListModel<ArtifactTemplate>();

		for (ArtifactTemplate a : character.getArtifacts()) {

			try {
				switch (a.getType()) {
				case "Head":
					model1.addElement(a);
					break;
				case "Chest":
					model2.addElement(a);
					break;
				case "Legs":
					model3.addElement(a);
					break;
				case "Hands":
					model4.addElement(a);
					break;
				case "Right-hand":
					model5.addElement(a);
					break;
				case "Left-hand":
					model6.addElement(a);
					break;
				default:
					throw new IllegalArgumentException("There is no such a type of an item");
				}
			} catch (IllegalArgumentException exc) {
				// System.out.println("There is no such a type of an item");
				exc.printStackTrace();
			}

		}

	}

	// Function to create JList ToolTips
	private void createJListToolTips() {
		mma = new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				@SuppressWarnings("unchecked")
				JList<ArtifactTemplate> l = (JList<ArtifactTemplate>) e.getSource();
				ListModel<ArtifactTemplate> m = l.getModel();
				int index = l.locationToIndex(e.getPoint());
				if (index > -1) {
					l.setToolTipText(m.getElementAt(index).getDescription());
				}
			}
		};
	}

	// Functions to drag and drop from JList to JLabel imported and modified from
	// https://stackoverflow.com/questions/13855184/drag-and-drop-custom-object-from-jList-into-jlabel
	@SuppressWarnings("serial")
	public class ListTransferHandler extends TransferHandler {

		@Override
		public boolean canImport(TransferSupport support) {
			return (support.getComponent() instanceof JLabel)
					&& support.isDataFlavorSupported(ArtifactTransferable.LIST_ITEM_DATA_FLAVOR);
		}

		@Override
		public boolean importData(TransferSupport support) {
			boolean accept = false;
			if (canImport(support)) {
				try {
					Transferable t = support.getTransferable();
					Object value = t.getTransferData(ArtifactTransferable.LIST_ITEM_DATA_FLAVOR);
					if (value instanceof ArtifactTemplate) {
						Component component = support.getComponent();
						if (component instanceof JLabel) {
							((JLabel) component).setText(((ArtifactTemplate) value).getName());
							((JLabel) component).setToolTipText("<html>" + ((ArtifactTemplate) value).getType() + "<br>"
									+ ((ArtifactTemplate) value).getDescription() + "</html>");

							if (((ArtifactTemplate) value).getType().compareTo("Right-hand") == 0) {
								character
										.setAttack(Character.getBasicAttack() + ((ArtifactTemplate) value).getAmount());
								attackLabel.setText("Attack: " + character.getAttack());
							} else if (((ArtifactTemplate) value).getType().compareTo("Left-hand") == 0) {
								character.setDefence(
										Character.getBasicDefence() + ((ArtifactTemplate) value).getAmount());
								defenceLabel.setText("Defence: " + character.getDefence());
							} else {
								character = setCharacter();
								character.setArmor(character.getHead().getAmount() + character.getChest().getAmount()
										+ character.getLegs().getAmount() + character.getHands().getAmount());
								armorLabel.setText("Armor: " + character.getArmor());
							}
							accept = true;
						}
					}
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
			return accept;
		}

		@Override
		public int getSourceActions(JComponent c) {
			return DnDConstants.ACTION_COPY_OR_MOVE;
		}

		@Override
		protected Transferable createTransferable(JComponent c) {
			Transferable t = null;
			if (c instanceof JList) {
				@SuppressWarnings("unchecked")
				JList<ArtifactTemplate> list = (JList<ArtifactTemplate>) c;
				Object value = list.getSelectedValue();
				if (value instanceof ArtifactTemplate) {
					ArtifactTemplate li = (ArtifactTemplate) value;
					t = new ArtifactTransferable(li);
				}
			}
			return t;
		}

		@Override
		protected void exportDone(JComponent source, Transferable data, int action) {
			System.out.println("ExportDone");
			// Here you need to decide how to handle the completion of the transfer,
			// should you remove the item from the list or not...
		}
	}

	public static class ArtifactTransferable implements Transferable {

		public static final DataFlavor LIST_ITEM_DATA_FLAVOR = new DataFlavor(ArtifactTemplate.class, "java/Artifact");
		private ArtifactTemplate Artifact;

		public ArtifactTransferable(ArtifactTemplate Artifact) {
			this.Artifact = Artifact;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { LIST_ITEM_DATA_FLAVOR };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return flavor.equals(LIST_ITEM_DATA_FLAVOR);
		}

		@Override
		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

			return Artifact;

		}
	}
}