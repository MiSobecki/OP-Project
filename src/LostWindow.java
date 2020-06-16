import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LostWindow extends JFrame {

	String savefile;

	public LostWindow(String savefile) {
		this.savefile = savefile;
		initialize();
	}

	private void initialize() {
		
		deleteSave();

		// Setting to frame
		setTitle("Game Lost");
		setLocation(450, 100);
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground();
	}

	private void setBackground() {
		Image img = Toolkit.getDefaultToolkit().getImage("pictures/lostGame.jpg");
		this.setContentPane(new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
			}
		});
	}

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

		File file = new File("saves/" + savefile);
		if (file.delete())
			System.out.println("Save successfully deleted");
	
	}
}
