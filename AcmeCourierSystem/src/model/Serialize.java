package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Serialize {
	
	public static void SaveObject(String filename, Object o) throws FileNotFoundException, IOException {
		XMLEncoder e = new XMLEncoder(
				new BufferedOutputStream(
						new FileOutputStream(filename)));
		e.writeObject(o);
		e.close();
	}
	
	public static Object LoadObject(String filename) throws FileNotFoundException, IOException {
		XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                    new FileInputStream(filename)));
		
		Object result = d.readObject();
		
		d.close();
		return result;
	}
}
