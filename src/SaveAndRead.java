import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
