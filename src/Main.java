import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Main {

	public static void main(String[] args) {
		
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

		// opens menu window
		new Menu();

	}

}
