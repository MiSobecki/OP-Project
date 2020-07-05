import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window which appear after player's death
 * 
 * @author MiSobecki
 *
 */
public class LostWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2883702054085686616L;
	private JButton returnBut;
	private String savefile;

	public LostWindow(String savefile) {
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {

		deleteSave();
		setBackground();

		// Button to open shop window
		returnBut = new JButton("Return to the menu");
		getContentPane().add(returnBut);
		returnBut.setFont(new Font("Enchanted Land", Font.PLAIN, 25));
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new Menu();
				dispose();
			}
		});
		returnBut.setBackground(Color.black);
		returnBut.setForeground(Color.red);

		// Setting to frame
		setTitle("Game Lost");
		setLocation(450, 100);
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Sets Background of the window
	 */
	private void setBackground() {
		Image img = Toolkit.getDefaultToolkit().createImage("pictures/lostGame.jpg");
		this.setContentPane(new JPanel() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
			}
		});
	}

	/**
	 * Deletes savefile which player died on
	 */
	private void deleteSave() {
		SaveAndRead sr = new SaveAndRead();
		ArrayList<String> al;
		try {
			al = sr.readSaves("Saves");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			al = new ArrayList<String>();
		}

		al.remove(savefile);

		try {
			sr.saveSaves(al, "Saves");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File("saves/" + savefile + ".bin");
		if (file.delete())
			System.out.println("Save successfully deleted");

	}
}
