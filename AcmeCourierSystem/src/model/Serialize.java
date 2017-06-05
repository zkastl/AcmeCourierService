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
	
	public static <T> Object LoadObject(String filename, Class<T> c) throws FileNotFoundException, IOException {
		XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                    new FileInputStream("Test.xml")), c);
		Object result = d.readObject();
		return result;
	}
}
