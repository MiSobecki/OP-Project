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

	public void save(Character character, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(savefile + ".bin"));
		outputStream.writeObject(character);
		System.out.println("saved");
		outputStream.close();
	}

	public Character read(String savefile) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(savefile + ".bin"));
		Character temp = (Character) inputStream.readObject();
		inputStream.close();
		return temp;
	}

	public void saveShop(ArrayList<ArtifactTemplate> temp, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(savefile + ".bin"));
		outputStream.writeObject(temp);
		System.out.println("saved");
		outputStream.close();
	}

	public ArrayList<ArtifactTemplate> readShop(String savefile) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(savefile + ".bin"));
		@SuppressWarnings("unchecked")
		ArrayList<ArtifactTemplate> temp = (ArrayList<ArtifactTemplate>) inputStream.readObject();
		inputStream.close();
		return temp;
	}
	
	public void saveSaves(ArrayList<String> temp, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(savefile + ".bin"));
		outputStream.writeObject(temp);
		System.out.println("saved");
		outputStream.close();
	}
	
	public ArrayList<String> readSaves(String savefile) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(savefile + ".bin"));
		@SuppressWarnings("unchecked")
		ArrayList<String> temp = (ArrayList<String>) inputStream.readObject();
		inputStream.close();
		return temp;
	}
	
	public String readHistory(String path) {
		String line, text;
		
		text = "<html>";
		
		try {
			File file = new File("stories/" + path);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine()) != null) {
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
