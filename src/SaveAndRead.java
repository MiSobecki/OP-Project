import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveAndRead {

	
	public void save(Character character, String savefile) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream( savefile + ".bin"));
		outputStream.writeObject(character);
		System.out.println("saved");
		outputStream.close();
	}
	
	public Character read(String savefile) throws IOException, ClassNotFoundException{
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream( savefile + ".bin"));
		Character temp = (Character)inputStream.readObject();
		inputStream.close();
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArtifactTemplate> readShop(String savefile) throws IOException, ClassNotFoundException{
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream( savefile + ".bin"));
		ArrayList<ArtifactTemplate> temp = (ArrayList<ArtifactTemplate>)inputStream.readObject();
		inputStream.close();
		return temp;
	}
}
