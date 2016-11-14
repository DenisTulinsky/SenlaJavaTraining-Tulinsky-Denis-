package com.senla.training.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.senla.training.interfaces.IStorage;
import com.senla.training.properties.Property;
import com.senla.training.storage.Storage;

/**
 * Class for serialization/deserialization of Storage Filepath taken from
 * Resources/prop.configuration
 * 
 * @author Denis
 *
 */
public class SerializationUtility {

	private static final String PATH = Property.getProps().getSerializationPath();
	private final org.apache.log4j.Logger log = Logger.getLogger(SerializationUtility.class);

	/**
	 * Writes serialized storage to file
	 * 
	 * 
	 */
	public void serialize(IStorage storage) {
		try {

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));

			oos.writeObject(storage);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
	}

	/**
	 * Reads serialized storage from file. Deserializes. Returns Storage or null
	 * 
	 * 
	 */
	public Storage deserialize() {
		Storage storage = null;
		try {

			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(PATH));
			storage = (Storage) oin.readObject();
			oin.close();

		} catch (Exception e) {
			log.error(e.getMessage());

		}

		return storage;

	}
}
