import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveAndRead {

	/**
	 * Saves Character to savefile
	 * 
	 * @param character
	 * @param savefile
	 * @throws IOException
	 */
	public void save(Character character, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(savefile + ".bin"));
		outputStream.writeObject(character);
		System.out.println("saved");
		outputStream.close();
	}

	/**
	 * Reads Character from savefile
	 * 
	 * @param savefile
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Character read(String savefile) throws IOException, ClassNotFoundException {
		Character temp;
		if (new File(savefile + ".bin").exists()) {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(savefile + ".bin"));
			temp = (Character) inputStream.readObject();
			inputStream.close();
		}
		else temp = new Character();
		return temp;
	}

	/**
	 * Saves Shop List to savefile
	 * 
	 * @param temp
	 * @param savefile
	 * @throws IOException
	 */
	public void saveShop(ArrayList<ArtifactTemplate> temp, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(savefile + ".bin"));
		outputStream.writeObject(temp);
		System.out.println("saved");
		outputStream.close();
	}

	/**
	 * Reads Shop List from savefile
	 * 
	 * @param savefile
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ArtifactTemplate> readShop(String savefile) throws IOException, ClassNotFoundException {
		ArrayList<ArtifactTemplate> temp;
		if (new File(savefile + ".bin").exists()) {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(savefile + ".bin"));
			temp = (ArrayList<ArtifactTemplate>) inputStream.readObject();
			inputStream.close();
		}
		else temp = new ArrayList<ArtifactTemplate>();
		return temp;
	}

	/**
	 * Saves Character savefiles List to savefile
	 * 
	 * @param temp
	 * @param savefile
	 * @throws IOException
	 */
	public void saveSaves(ArrayList<String> temp, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(savefile + ".bin"));
		outputStream.writeObject(temp);
		System.out.println("saved");
		outputStream.close();
	}

	/**
	 * Reads Character savefiles List to savefile
	 * 
	 * @param savefile
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> readSaves(String savefile) throws IOException, ClassNotFoundException {
		ArrayList<String> temp;
		if (new File(savefile + ".bin").exists()) {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(savefile + ".bin"));
			temp = (ArrayList<String>) inputStream.readObject();
			inputStream.close();
		}
		else temp = new ArrayList<String>();
		return temp;
	}

	/**
	 * Reads history from text file
	 * 
	 * @param path
	 * @return
	 */
	public String readHistory(String path) {
		String line, text;

		text = "<html>";

		try {
			File file = new File("stories/" + path);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				text += line + "<br>";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		text += "</html>";

		return text;
	}

}
