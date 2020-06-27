import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Main {

	public static void main(String[] args) {
		
		changeFonts();
		
		playMusic();

		// opens menu window
		new Menu();

	}
	
	/**
	 * Adds new Font
	 */
	private static void changeFonts() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/EnchantedLand.otf"));
	        font = font.deriveFont(Font.PLAIN, 20);
	        GraphicsEnvironment ge =
	            GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(font);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UIManager.put("ToolTip.font", new FontUIResource("Enchanted Land", Font.TRUETYPE_FONT, 20));
	}
	
	/**
	 * Plays music
	 */
	private static void playMusic() {
		File musicPath = new File("music/Dark-Things.wav");
		
		if (musicPath.exists()) {
			try {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
